package co.yml.ychat.providers

import co.yml.ychat.domain.model.EditsParams

interface EditsProvider {

    suspend fun requestEdits(params: EditsParams): List<String>
}
