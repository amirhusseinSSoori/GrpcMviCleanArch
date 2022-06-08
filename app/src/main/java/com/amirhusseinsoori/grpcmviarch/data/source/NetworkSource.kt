package com.amirhusseinsoori.grpcmviarch.data.source

import com.arad.domain.entity.TurnOn
import requestTest.RequestNodesGrpc
import requestTest.SettingReply
import requestTest.TurnOnRequest

import javax.inject.Inject
class NetworkSource @Inject constructor(
    val api: RequestNodesGrpc.RequestNodesBlockingStub

) {
    fun requestTurnOnWithSafe(turnOn: TurnOn): SettingReply {
        val (androidId, PowerOnTime) = turnOn
        return api.setTurnOn(
            TurnOnRequest.newBuilder().setAndroidId(androidId).setPowerOnTime(PowerOnTime).build()
        )

    }

}