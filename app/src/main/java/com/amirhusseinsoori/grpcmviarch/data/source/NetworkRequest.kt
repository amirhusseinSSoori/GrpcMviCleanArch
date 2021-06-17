package com.amirhusseinsoori.grpcmviarch.data.source

import com.amirhusseinsoori.grpcmviarch.domain.exception.GrpcWrapper
import com.amirhusseinsoori.grpcmviarch.domain.exception.SafeGrpc
import com.arad.domain.entity.TurnOn
import io.grpc.domain.mizannodes.MizanNodesGrpc
import io.grpc.domain.mizannodes.SettingReply
import io.grpc.domain.mizannodes.TurnOnRequest
import javax.inject.Inject

class NetworkSource @Inject constructor(
    val api: MizanNodesGrpc.MizanNodesBlockingStub

): SafeGrpc() {

    suspend fun requestTurnOnWithSafe(turnOn: TurnOn): GrpcWrapper<SettingReply> {
        val (androidId, PowerOnTime) = turnOn
        return safeGrpc { api.setTurnOn(
            TurnOnRequest.newBuilder().setAndroidId(androidId).setPowerOnTime(PowerOnTime).build()
        ) }

    }

}