package co.yml.ychat.providers.chatgpt

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.domain.mapper.toChatCompletionParamsDto
import co.yml.ychat.domain.mapper.toChatMessages
import co.yml.ychat.domain.model.ChatCompletionsParams
import co.yml.ychat.domain.model.ChatMessage
import co.yml.ychat.providers.ChatCompletionProvider

internal class ChatCompletionsChatGptProvider(private val chatGptApi: ChatGptApi) :
    ChatCompletionProvider {

    override suspend fun  requestChatCompletions(params: ChatCompletionsParams): List<ChatMessage> {
        val requestDto = params.toChatCompletionParamsDto()
        val response = chatGptApi.chatCompletions(requestDto)
        return response.getBodyOrThrow().toChatMessages()
    }
}
