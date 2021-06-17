package com.amirhusseinsoori.grpcmviarch.presentation.grpc

import com.arad.domain.entity.TurnOn

sealed class MainIntent {

    class FetchTurnOn(var turnOn: TurnOn) : MainIntent()

}