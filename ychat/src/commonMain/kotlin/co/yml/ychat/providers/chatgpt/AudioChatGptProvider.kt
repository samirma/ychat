package co.yml.ychat.providers.chatgpt

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.domain.mapper.toAudioParamsDto
import co.yml.ychat.domain.model.AudioParams
import co.yml.ychat.domain.model.FileBytes
import co.yml.ychat.providers.AudioProvider

internal class AudioChatGptProvider(private val chatGptApi: ChatGptApi): AudioProvider {

    override suspend fun  requestAudioTranscription(
        filename: String,
        audioFile: FileBytes,
        params: AudioParams
    ): String {
        val requestDto = params.toAudioParamsDto(filename, audioFile)
        val response = chatGptApi.audioTranscriptions(requestDto)
        return response.getBodyOrThrow().text
    }

    override suspend fun  requestAudioTranslations(
        filename: String,
        audioFile: FileBytes,
        params: AudioParams
    ): String {
        val requestDto = params.toAudioParamsDto(filename, audioFile)
        val response = chatGptApi.audioTranslations(requestDto)
        return response.getBodyOrThrow().text
    }
}
