package com.amirhusseinsoori.grpcmviarch.presentation.grpc

import io.grpc.domain.request.SettingReply


sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class Success(val settingReply: io.grpc.domain.request.SettingReply) : MainState()
    data class Error(val error: String?) : MainState()

}