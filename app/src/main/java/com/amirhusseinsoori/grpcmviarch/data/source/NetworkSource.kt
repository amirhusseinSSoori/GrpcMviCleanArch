package com.amirhusseinsoori.grpcmviarch.data.source

import com.arad.domain.entity.TurnOn
import io.grpc.domain.mizannodes.MizanNodesGrpc
import io.grpc.domain.mizannodes.SettingReply
import io.grpc.domain.mizannodes.TurnOnRequest


import javax.inject.Inject
class NetworkSource @Inject constructor(
    val api: MizanNodesGrpc.MizanNodesBlockingStub

) {

    suspend fun requestTurnOnWithSafe(turnOn: TurnOn): SettingReply {
        val (androidId, PowerOnTime) = turnOn
        return api.setTurnOn(
            TurnOnRequest.newBuilder().setAndroidId(androidId).setPowerOnTime(PowerOnTime).build()
        )

    }

}