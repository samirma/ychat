package co.yml.ychat.domain.model

data class CompletionModel(
    val id: String,
    val model: String,
    val choices: List<ChoiceModel>,
    val usage: UsageModel,
)
