package co.yml.ychat.providers

import co.yml.ychat.domain.model.AIModel

interface RetrieveModelProvider {

    suspend fun getModel(id: String): AIModel
}
