package co.yml.ychat.providers

import co.yml.ychat.domain.model.AIModel

interface ListModelsProvider {

    suspend fun getListModels(): List<AIModel>
}
