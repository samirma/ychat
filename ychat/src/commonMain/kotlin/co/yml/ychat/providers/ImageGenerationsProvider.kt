package co.yml.ychat.providers

import co.yml.ychat.domain.model.ImageGenerationsParams

interface ImageGenerationsProvider {

    suspend fun requestImageGenerations(params: ImageGenerationsParams): List<String>
}
