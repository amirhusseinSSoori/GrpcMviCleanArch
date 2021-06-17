package com.amirhusseinsoori.grpcmviarch.data.repository

import com.amirhusseinsoori.grpcmviarch.data.source.NetworkSource
import com.amirhusseinsoori.grpcmviarch.domain.exception.GrpcWrapper
import com.amirhusseinsoori.grpcmviarch.domain.repository.GrpcRepository
import com.arad.domain.entity.TurnOn
import io.grpc.domain.mizannodes.SettingReply
import javax.inject.Inject

class GrpcRepositoryImp @Inject constructor(var api:NetworkSource) :GrpcRepository {
    override suspend fun apiTurnOn(turnOn: TurnOn): GrpcWrapper<SettingReply> {
        return api.requestTurnOnWithSafe(turnOn)
    }
}