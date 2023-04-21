package co.yml.ychat.domain.usecases

import co.yml.ychat.domain.model.AudioParams
import co.yml.ychat.domain.model.FileBytes
import co.yml.ychat.providers.Provider

internal class AudioUseCase(private val provider: Provider) {

    suspend fun requestAudioTranscription(
        filename: String,
        audioFile: FileBytes,
        params: AudioParams
    ) = provider.audioProvider?.requestAudioTranscription(filename, audioFile, params)

    suspend fun requestAudioTranslations(
        filename: String,
        audioFile: FileBytes,
        params: AudioParams
    ) = provider.audioProvider?.requestAudioTranslations(filename, audioFile, params)

}
