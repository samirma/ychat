package co.yml.ychat.data.storage

import co.yml.ychat.providers.Provider

class ProviderStorage(private val providers: Map<String, Provider>) {

    internal var selectedProvider = providers.keys.firstOrNull()
        get() = field
        set(value) {
            field = value
        }

    fun getProvider() = providers[selectedProvider]

    fun getProvidersList(): Map<String, Provider> = providers.toMutableMap()

}
