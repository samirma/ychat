package co.yml.ychat.providers

import co.yml.ychat.domain.model.CompletionModel
import co.yml.ychat.domain.model.CompletionParams

interface CompletionProvider {

    suspend fun completion(completionParams: CompletionParams): CompletionModel
}
