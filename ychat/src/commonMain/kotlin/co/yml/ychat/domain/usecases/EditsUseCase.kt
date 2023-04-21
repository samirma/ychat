package co.yml.ychat.domain.usecases

import co.yml.ychat.domain.model.EditsParams
import co.yml.ychat.providers.EditsProvider

internal class EditsUseCase(private val provider: EditsProvider) {

    suspend fun requestEdits(params: EditsParams) = provider.requestEdits(params)

}
