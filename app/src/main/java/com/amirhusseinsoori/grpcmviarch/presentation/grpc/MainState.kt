package com.amirhusseinsoori.grpcmviarch.presentation.grpc
import io.grpc.domain.mizannodes.SettingReply

sealed class MainState {

    object Idle : MainState()
    data class Success(val user: SettingReply) : MainState()
    data class Error(val error: String?) : MainState()

}