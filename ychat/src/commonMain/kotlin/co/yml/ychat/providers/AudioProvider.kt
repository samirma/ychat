package co.yml.ychat.providers

import co.yml.ychat.domain.model.AudioParams
import co.yml.ychat.domain.model.FileBytes

interface AudioProvider {

    suspend fun requestAudioTranscription(
        filename: String,
        audioFile: FileBytes,
        params: AudioParams
    ): String

    suspend fun requestAudioTranslations(
        filename: String,
        audioFile: FileBytes,
        params: AudioParams
    ): String
}
