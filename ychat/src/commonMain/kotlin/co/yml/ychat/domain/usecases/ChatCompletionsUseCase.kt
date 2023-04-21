package co.yml.ychat.domain.usecases

import co.yml.ychat.domain.model.ChatCompletionsParams
import co.yml.ychat.providers.ChatCompletionProvider

internal class ChatCompletionsUseCase(private val provider: ChatCompletionProvider) {

    suspend fun requestChatCompletions(params: ChatCompletionsParams) =
        provider.requestChatCompletions(params)
}
