package co.yml.ychat.domain.usecases

import co.yml.ychat.domain.model.AudioParams
import co.yml.ychat.domain.model.FileBytes
import co.yml.ychat.providers.AudioProvider

internal class AudioUseCase(private val provider: AudioProvider) {

    suspend fun requestAudioTranscription(
        filename: String,
        audioFile: FileBytes,
        params: AudioParams
    )  = provider.requestAudioTranscription(filename, audioFile, params)

    suspend fun requestAudioTranslations(
        filename: String,
        audioFile: FileBytes,
        params: AudioParams
    )  = provider.requestAudioTranslations(filename, audioFile, params)

}
