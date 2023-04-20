package co.yml.ychat.providers.chatgpt

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.domain.mapper.toImageGenerated
import co.yml.ychat.domain.mapper.toImageGenerationsParamsDto
import co.yml.ychat.domain.model.ImageGenerationsParams
import co.yml.ychat.providers.ImageGenerationsProvider

internal class ImageGenerationsChatGptProvider(private val chatGptApi: ChatGptApi) :
    ImageGenerationsProvider {

    override suspend fun  requestImageGenerations(params: ImageGenerationsParams): List<String> {
        val requestDto = params.toImageGenerationsParamsDto()
        val response = chatGptApi.imageGenerations(requestDto)
        return response.getBodyOrThrow().toImageGenerated()
    }
}
