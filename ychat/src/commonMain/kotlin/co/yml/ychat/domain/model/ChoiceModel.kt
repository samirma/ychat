package co.yml.ychat.domain.model

data class ChoiceModel(
    val text: String,
    val index: Int,
    val logProbs: Int?,
    val finishReason: String,
)
