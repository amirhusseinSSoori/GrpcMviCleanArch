package com.amirhusseinsoori.grpcmviarch.presentation.grpc

import com.arad.domain.entity.TurnOn
import com.amirhusseinsoori.grpcmviarch.presentation.base.UiEffect
import com.amirhusseinsoori.grpcmviarch.presentation.base.UiEvent
import com.amirhusseinsoori.grpcmviarch.presentation.base.UiState
import io.grpc.domain.request.SettingReply


class GrpcContract {

    sealed class Event : UiEvent {
        class OnShowResult(var turnOn: TurnOn) : Event()
        object HandelError : Event()
    }

    data class State(
        val state: SendRequestState
    ) : UiState

    sealed class SendRequestState {
        object Idle : SendRequestState()
        data class Success(val settingReply: SettingReply) : SendRequestState()

    }

    sealed class Effect : UiEffect {
        data class ShowMessage(val message: String) : Effect()
    }

}