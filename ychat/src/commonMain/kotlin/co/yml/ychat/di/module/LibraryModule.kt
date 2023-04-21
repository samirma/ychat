package co.yml.ychat.di.module

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.data.api.impl.ChatGptApiImpl
import co.yml.ychat.data.infrastructure.ApiExecutor
import co.yml.ychat.data.storage.ChatLogStorage
import co.yml.ychat.data.storage.ProviderStorage
import co.yml.ychat.di.provider.NetworkProvider
import co.yml.ychat.domain.usecases.AudioUseCase
import co.yml.ychat.domain.usecases.ChatCompletionsUseCase
import co.yml.ychat.domain.usecases.CompletionUseCase
import co.yml.ychat.domain.usecases.EditsUseCase
import co.yml.ychat.domain.usecases.ImageGenerationsUseCase
import co.yml.ychat.domain.usecases.ListModelsUseCase
import co.yml.ychat.domain.usecases.RecoverProviderUserCase
import co.yml.ychat.domain.usecases.RetrieveModelUseCase
import co.yml.ychat.domain.usecases.SetProviderUserCase
import co.yml.ychat.entrypoint.features.AudioTranscriptions
import co.yml.ychat.entrypoint.features.AudioTranslations
import co.yml.ychat.entrypoint.features.ChatCompletions
import co.yml.ychat.entrypoint.features.Completion
import co.yml.ychat.entrypoint.features.Edits
import co.yml.ychat.entrypoint.features.ImageGenerations
import co.yml.ychat.entrypoint.features.ListModels
import co.yml.ychat.entrypoint.features.RetrieveModel
import co.yml.ychat.entrypoint.impl.AudioTranscriptionsImpl
import co.yml.ychat.entrypoint.impl.AudioTranslationsImpl
import co.yml.ychat.entrypoint.impl.ChatCompletionsImpl
import co.yml.ychat.entrypoint.impl.CompletionImpl
import co.yml.ychat.entrypoint.impl.EditsImpl
import co.yml.ychat.entrypoint.impl.ImageGenerationsImpl
import co.yml.ychat.entrypoint.impl.ListModelsImpl
import co.yml.ychat.entrypoint.impl.RetrieveModelImpl
import co.yml.ychat.providers.CHAT_GPT
import co.yml.ychat.providers.Provider
import co.yml.ychat.providers.chatgpt.AudioChatGptProvider
import co.yml.ychat.providers.chatgpt.ChatCompletionsChatGptProvider
import co.yml.ychat.providers.chatgpt.CompletionChatGptProvider
import co.yml.ychat.providers.chatgpt.EditsChatGptProvider
import co.yml.ychat.providers.chatgpt.ImageGenerationsChatGptProvider
import co.yml.ychat.providers.chatgpt.ListModelsChatGptProvider
import co.yml.ychat.providers.chatgpt.RetrieveModelChatGptProvider
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

internal class LibraryModule(private val apiKey: String) {

    fun modules(): List<Module> =
        entrypointModule + domainModule + dataModule + providersModule + platformModule()

    private val entrypointModule = module {
        factory<ListModels> { ListModelsImpl(Dispatchers.Default, get()) }
        factory<RetrieveModel> { RetrieveModelImpl(Dispatchers.Default, get()) }
        factory<Completion> { CompletionImpl(Dispatchers.Default, get()) }
        factory<ChatCompletions> { ChatCompletionsImpl(Dispatchers.Default, get()) }
        factory<ImageGenerations> { ImageGenerationsImpl(Dispatchers.Default, get()) }
        factory<Edits> { EditsImpl(Dispatchers.Default, get()) }
        factory<AudioTranscriptions> { AudioTranscriptionsImpl(Dispatchers.Default, get()) }
        factory<AudioTranslations> { AudioTranslationsImpl(Dispatchers.Default, get()) }
    }

    private val domainModule = module {
        factory { ListModelsUseCase(get()) }
        factory { RetrieveModelUseCase(get()) }
        factory { CompletionUseCase(get()) }
        factory { ChatCompletionsUseCase(get()) }
        factory { ImageGenerationsUseCase(get()) }
        factory { EditsUseCase(get()) }
        factory { AudioUseCase(get()) }

        factory { RecoverProviderUserCase(get()) }
        factory { SetProviderUserCase(get()) }
    }

    private val providersModule = module {
        factory {
            mapOf(
                CHAT_GPT to Provider(
                    editsProvider = EditsChatGptProvider(get()),
                    chatCompletionProvider = ChatCompletionsChatGptProvider(get()),
                    retrieveModelProvider = RetrieveModelChatGptProvider(get()),
                    listModelsProvider = ListModelsChatGptProvider(get()),
                    imageGenerationsProvider = ImageGenerationsChatGptProvider(get()),
                    audioProvider = AudioChatGptProvider(get()),
                    completionProvider = CompletionChatGptProvider(get(), get()),
                )
            )
        }

    }

    private val dataModule = module {
        single { NetworkProvider.provideHttpClient(get(), apiKey) }
        single { ChatLogStorage() }
        single { ProviderStorage(get()) }
        factory { ApiExecutor(get()) }
        factory<ChatGptApi> { ChatGptApiImpl(get()) }
    }
}

internal expect fun platformModule(): Module
