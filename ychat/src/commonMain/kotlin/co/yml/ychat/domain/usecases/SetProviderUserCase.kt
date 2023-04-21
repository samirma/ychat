package co.yml.ychat.domain.usecases

import co.yml.ychat.data.storage.ProviderStorage

class SetProviderUserCase(private val storage: ProviderStorage) {

    operator fun invoke(providerName: String) {
        if (!storage.getProvidersList().containsKey(providerName)) error("Invalid provider")
        storage.selectedProvider = providerName
    }

}