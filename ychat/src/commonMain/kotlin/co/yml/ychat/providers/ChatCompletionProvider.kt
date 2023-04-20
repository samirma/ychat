package co.yml.ychat.providers

import co.yml.ychat.domain.model.ChatCompletionsParams
import co.yml.ychat.domain.model.ChatMessage

interface ChatCompletionProvider {

    suspend fun requestChatCompletions(params: ChatCompletionsParams): List<ChatMessage>
}
