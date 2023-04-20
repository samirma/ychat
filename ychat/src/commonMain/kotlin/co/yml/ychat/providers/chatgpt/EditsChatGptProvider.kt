package co.yml.ychat.providers.chatgpt

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.domain.mapper.toEditsModel
import co.yml.ychat.domain.mapper.toEditsParamsDto
import co.yml.ychat.domain.model.EditsParams
import co.yml.ychat.providers.EditsProvider

internal class EditsChatGptProvider(private val chatGptApi: ChatGptApi) : EditsProvider {

    override suspend fun  requestEdits(params: EditsParams): List<String> {
        val requestDto = params.toEditsParamsDto()
        val response = chatGptApi.edits(requestDto)
        return response.getBodyOrThrow().toEditsModel()
    }
}
