package co.yml.ychat.domain.usecases

import co.yml.ychat.data.storage.ProviderStorage
import co.yml.ychat.providers.AudioProvider

class RecoverProviderUserCase(private val provider: ProviderStorage) {

    operator fun < T : Any> invoke() = provider.getProvider() ?: error("No provider available")

}