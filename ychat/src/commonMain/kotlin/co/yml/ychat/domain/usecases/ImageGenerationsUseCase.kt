package co.yml.ychat.domain.usecases

import co.yml.ychat.domain.model.ImageGenerationsParams
import co.yml.ychat.providers.ImageGenerationsProvider

internal class ImageGenerationsUseCase(private val provider: ImageGenerationsProvider) {

    suspend fun requestImageGenerations(params: ImageGenerationsParams) =
        provider.requestImageGenerations(params)
}
