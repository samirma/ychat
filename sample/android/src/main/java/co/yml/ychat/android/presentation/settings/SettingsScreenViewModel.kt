package co.yml.ychat.android.presentation.settings

import androidx.lifecycle.ViewModel
import co.yml.ychat.android.presentation.models.viewmodel.ModelsViewModel
import co.yml.ychat.domain.usecases.RecoverProviderUserCase
import co.yml.ychat.domain.usecases.SetProviderUserCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class SettingsScreenViewModel(
    val setProvider: SetProviderUserCase,
    val recoverProvider: RecoverProviderUserCase,
) : ViewModel() {

    private val _state = MutableStateFlow<ModelsViewModel.State>(ModelsViewModel.State.Loading)
    val state: StateFlow<ModelsViewModel.State> = _state.asStateFlow()

    sealed class State {
        object Loading : State()
        data class Success(val providers: List<Provider>) : State()
        object Error : State()
    }

    data class Provider(
        val name: String,
    )

}
