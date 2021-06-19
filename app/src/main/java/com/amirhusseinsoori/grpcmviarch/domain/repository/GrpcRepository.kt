package com.amirhusseinsoori.grpcmviarch.domain.repository




import com.arad.domain.entity.TurnOn
import io.grpc.domain.mizannodes.SettingReply


interface GrpcRepository {
    suspend fun apiTurnOn(turnOn: TurnOn): SettingReply
}