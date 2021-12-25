package org.bubbasmith.vocabtivator.integration

import kotlinx.coroutines.reactive.awaitFirstOrDefault
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.bubbasmith.vocabtivator.integration.dao.SearchStatisticsEntity
import org.bubbasmith.vocabtivator.integration.dao.SearchStatisticsRepository
import org.bubbasmith.vocabtivator.integration.dao.SentenceEntity
import org.bubbasmith.vocabtivator.integration.dao.SentenceRepository
import org.bubbasmith.vocabtivator.integration.external.ExternalSentencesSearch
import org.bubbasmith.vocabtivator.model.Sentence
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class SentenceServiceImpl(
        private val externalSentencesSearch: ExternalSentencesSearch,
        private val sentenceRepository: SentenceRepository,
        private val searchStatisticsRepository: SearchStatisticsRepository) : SentenceService {

    private val log = LoggerFactory.getLogger(SentenceServiceImpl::class.java)

    override suspend fun getSentenceForWord(word: String): Sentence {
        val randomSentence = sentenceRepository.findRandomForWord(word).awaitFirstOrNull()
        return if (randomSentence != null) {
            this.incWordUsageCount(word)
            Sentence(randomSentence.text, randomSentence.placeholders)
        } else {
            log.info("Sentence for the word '${word}' not found, searching in external resources...")
            val sentences = externalSentencesSearch.findSentencesForWord(word)
            val sentencesEntities = sentences.map { SentenceEntity(null, it.text, word, it.placeholders) }
            sentenceRepository.saveAll(sentencesEntities).awaitSingle()
            incWordUsageCount(word)
            sentences.random()
        }
    }

    private suspend fun incWordUsageCount(word: String): SearchStatisticsEntity {
        val wordStat = searchStatisticsRepository.findByVocabWord(word)
                .awaitFirstOrDefault(SearchStatisticsEntity(null, word, ArrayList()))
        return searchStatisticsRepository.save(
                SearchStatisticsEntity(wordStat.id, wordStat.vocabWord, wordStat.searchDates + listOf(Date()))
        ).awaitSingle()
    }
}
