package com.amirhusseinsoori.grpcmviarch.presentation.grpc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirhusseinsoori.grpcmviarch.domain.UseCase.TurnOnUseCase
import com.arad.domain.entity.TurnOn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.grpc.StatusRuntimeException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class GrpcViewModel @Inject constructor(
    var turnOnUseCase: TurnOnUseCase,
) : ViewModel() {
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState>
        get() = _state





    fun handleIntent(turnOn: TurnOn) {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchUser -> fetchUser(turnOn)
                }
            }
        }
    }

    private fun fetchUser(turnOn: TurnOn) {
        viewModelScope.launch {
            _state.value = try {
                MainState.Success(turnOnUseCase.execute(turnOn))
            } catch (e: StatusRuntimeException) {
                MainState.Error(e.localizedMessage)
            }
        }
    }
}