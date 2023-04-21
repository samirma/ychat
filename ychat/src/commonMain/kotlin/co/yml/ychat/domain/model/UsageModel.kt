package co.yml.ychat.domain.model

data class UsageModel(
    val promptToken: Int,
    val completionTokens: Int,
    val totalTokens: Int,
)
