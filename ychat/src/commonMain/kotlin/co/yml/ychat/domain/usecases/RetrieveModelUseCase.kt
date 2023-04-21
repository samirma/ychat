package co.yml.ychat.domain.usecases

import co.yml.ychat.providers.RetrieveModelProvider

internal class RetrieveModelUseCase(private val provider: RetrieveModelProvider) {

    suspend fun getModel(id: String) = provider.getModel(id)
}
