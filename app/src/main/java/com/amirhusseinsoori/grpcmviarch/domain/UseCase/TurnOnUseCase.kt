package com.amirhusseinsoori.grpcmviarch.domain.UseCase

import com.amirhusseinsoori.grpcmviarch.domain.UseCase.base.UseCase
import com.amirhusseinsoori.grpcmviarch.domain.exception.GrpcWrapper
import com.amirhusseinsoori.grpcmviarch.domain.repository.GrpcRepository
import com.arad.domain.entity.TurnOn
import io.grpc.domain.mizannodes.SettingReply
import javax.inject.Inject

class TurnOnUseCase @Inject constructor(val grpcRepository: GrpcRepository):
    UseCase<TurnOn, GrpcWrapper<SettingReply>>() {
    override suspend fun execute(params: TurnOn?): GrpcWrapper<SettingReply> {
        return grpcRepository.apiTurnOn(params!!)
    }


}