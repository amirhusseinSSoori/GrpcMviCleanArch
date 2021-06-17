package com.amirhusseinsoori.grpcmviarch.presentation.grpc

sealed class MainIntent {

    object FetchUser : MainIntent()

}