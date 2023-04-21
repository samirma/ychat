package co.yml.ychat.domain.usecases

import co.yml.ychat.domain.model.CompletionParams
import co.yml.ychat.providers.CompletionProvider

internal class CompletionUseCase(
    private val provider: CompletionProvider
) {

    suspend fun completion(completionParams: CompletionParams) =
        provider.completion(completionParams)

}
