package co.yml.ychat.providers

const val CHAT_GPT = "CHAT GPT"

data class Provider(
    val editsProvider: EditsProvider? = null,
    val chatCompletionProvider: ChatCompletionProvider? = null,
    val retrieveModelProvider: RetrieveModelProvider? = null,
    val listModelsProvider: ListModelsProvider? = null,
    val imageGenerationsProvider: ImageGenerationsProvider? = null,
    val audioProvider: AudioProvider? = null,
    val completionProvider: CompletionProvider? = null,
)