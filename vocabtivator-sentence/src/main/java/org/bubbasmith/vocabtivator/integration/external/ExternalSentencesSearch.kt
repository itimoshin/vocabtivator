package org.bubbasmith.vocabtivator.integration.external

import org.bubbasmith.vocabtivator.model.Sentence

interface ExternalSentencesSearch {
    suspend fun findSentencesForWord(word: String): List<Sentence>
}