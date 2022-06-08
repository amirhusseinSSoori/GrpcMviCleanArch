package com.amirhusseinsoori.grpcmviarch.domain.UseCase

import com.amirhusseinsoori.grpcmviarch.domain.UseCase.base.UseCase

import com.amirhusseinsoori.grpcmviarch.domain.repository.GrpcRepository
import com.arad.domain.entity.TurnOn
import kotlinx.coroutines.flow.Flow
import requestTest.SettingReply

import javax.inject.Inject

class TurnOnUseCase @Inject constructor(val grpcRepository: GrpcRepository):
    UseCase<TurnOn, Flow<Result<SettingReply>>>() {
    override suspend fun execute(params: TurnOn?): Flow<Result<SettingReply>> {
        return grpcRepository.apiTurnOn(params!!)
    }


}