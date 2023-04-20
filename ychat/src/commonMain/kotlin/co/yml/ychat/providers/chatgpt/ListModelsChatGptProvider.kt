package co.yml.ychat.providers.chatgpt

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.domain.mapper.toModel
import co.yml.ychat.domain.model.AIModel
import co.yml.ychat.providers.ListModelsProvider

internal class ListModelsChatGptProvider(private val chatGptApi: ChatGptApi) : ListModelsProvider {

    override suspend fun  getListModels(): List<AIModel> {
        val response = chatGptApi.models()
        return response.getBodyOrThrow().models.toModel()
    }
}
