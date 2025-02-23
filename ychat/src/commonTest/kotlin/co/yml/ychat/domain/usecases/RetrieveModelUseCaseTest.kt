package co.yml.ychat.domain.usecases

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.data.dto.ModelDto
import co.yml.ychat.data.exception.ChatGptException
import co.yml.ychat.data.infrastructure.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking

class RetrieveModelUseCaseTest {

    private lateinit var useCase: RetrieveModelUseCase

    private val chatGptApiMock = mockk<ChatGptApi>()

    @BeforeTest
    fun setup() {
        useCase = RetrieveModelUseCase(chatGptApiMock)
    }

    @Test
    fun `on getModel when request succeed then should return formatted result`() {
        // arrange
        val expectedId = "modelId"
        val modelListDto = buildModelDto(expectedId)
        val apiResult = ApiResult(body = modelListDto)
        coEvery { chatGptApiMock.model(expectedId) } returns apiResult

        // act
        val result = runBlocking { useCase.getModel(expectedId) }

        // assert
        assertEquals(expectedId, result.id)
    }

    @Test
    fun `on getModel when not request succeed then should throw an exception`() {
        // arrange
        val apiResult = ApiResult<ModelDto>(exception = ChatGptException())
        coEvery { chatGptApiMock.model("1") } returns apiResult

        // act
        val result = runCatching { runBlocking { useCase.getModel("1") } }

        // assert
        assertEquals(true, result.exceptionOrNull() is ChatGptException)
    }

    private fun buildModelDto(id: String): ModelDto {
        return ModelDto(id = id, ownedBy = "", emptyList())
    }
}
