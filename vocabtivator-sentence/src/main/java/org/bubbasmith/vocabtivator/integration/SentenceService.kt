package org.bubbasmith.vocabtivator.integration

import org.bubbasmith.vocabtivator.model.Sentence

interface SentenceService {

    suspend fun getSentenceForWord(word: String): Sentence

}