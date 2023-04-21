package co.yml.ychat.domain.usecases

import co.yml.ychat.providers.ListModelsProvider

internal class ListModelsUseCase(private val provider: ListModelsProvider) {

    suspend fun getListModels() = provider.getListModels()
}
