package co.yml.ychat.domain.mapper

import co.yml.ychat.data.dto.ImageGenerationsDto
import co.yml.ychat.data.dto.ImageGenerationsParamsDto
import co.yml.ychat.domain.model.ImageGenerationsParams

internal fun ImageGenerationsDto.toImageGenerated(): List<String> {
    return this.data.map {
        it.url
    }
}

internal fun ImageGenerationsParams.toImageGenerationsParamsDto(): ImageGenerationsParamsDto {
    return ImageGenerationsParamsDto(
        prompt = this.prompt,
        results = this.results,
        size = this.size,
        responseFormat = this.responseFormat,
        user = this.user
    )
}
