package co.yml.ychat.providers.chatgpt

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.domain.mapper.toModel
import co.yml.ychat.domain.model.AIModel
import co.yml.ychat.providers.RetrieveModelProvider

internal class RetrieveModelChatGptProvider(private val chatGptApi: ChatGptApi):
    RetrieveModelProvider {

    override suspend fun  getModel(id: String): AIModel {
        val response = chatGptApi.model(id)
        return response.getBodyOrThrow().toModel()
    }
}
