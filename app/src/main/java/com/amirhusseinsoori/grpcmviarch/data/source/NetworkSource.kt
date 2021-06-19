package com.amirhusseinsoori.grpcmviarch.data.source

import com.arad.domain.entity.TurnOn
import io.grpc.domain.request.RequestNodesGrpc
import io.grpc.domain.request.SettingReply
import io.grpc.domain.request.TurnOnRequest

import javax.inject.Inject
class NetworkSource @Inject constructor(
    val api: RequestNodesGrpc.RequestNodesBlockingStub

) {

    suspend fun requestTurnOnWithSafe(turnOn: TurnOn): SettingReply {
        val (androidId, PowerOnTime) = turnOn
        return api.setTurnOn(
            TurnOnRequest.newBuilder().setAndroidId(androidId).setPowerOnTime(PowerOnTime).build()
        )

    }

}