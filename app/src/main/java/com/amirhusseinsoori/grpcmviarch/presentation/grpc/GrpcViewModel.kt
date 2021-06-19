package com.amirhusseinsoori.grpcmviarch.presentation.grpc

import androidx.lifecycle.viewModelScope
import com.amirhusseinsoori.grpcmviarch.domain.UseCase.TurnOnUseCase
import com.arad.domain.entity.TurnOn
import com.ysfcyln.mviplayground.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.grpc.StatusRuntimeException
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GrpcViewModel @Inject constructor(var turnOnUseCase: TurnOnUseCase) :
    BaseViewModel<GrpcContract.Event, GrpcContract.State, GrpcContract.Effect>() {


    /**
     * Create initial State of Views
     */
    override fun createInitialState(): GrpcContract.State {
        return GrpcContract.State(
            GrpcContract.SendRequestState.Idle
        )
    }

    /**
     * Handle each event
     */
    override fun handleEvent(event: GrpcContract.Event) {
        when (event) {
            is GrpcContract.Event.OnShowResult -> {
                sendRequest(event.turnOn)
            }
            else -> Unit
        }
    }



    private fun sendRequest(turnOn: TurnOn?) {
        viewModelScope.launch {
            try {
                val settingReply = turnOnUseCase.execute(turnOn)
                setState { copy(state = GrpcContract.SendRequestState.Success(settingReply = settingReply)) }
                setEffect { GrpcContract.Effect.ShowMessage("Success") }


            } catch (exception: StatusRuntimeException) {
                setEffect { GrpcContract.Effect.ShowMessage(exception.message!!) }
            }
        }
    }
}