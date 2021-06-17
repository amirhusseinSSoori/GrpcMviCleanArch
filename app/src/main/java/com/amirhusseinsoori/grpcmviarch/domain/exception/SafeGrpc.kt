package com.amirhusseinsoori.grpcmviarch.domain.exception

import io.grpc.StatusRuntimeException

abstract class SafeGrpc {

    suspend fun <T> safeGrpc (call: suspend () -> T): GrpcWrapper<T> {
        return apiTry {


            call.invoke() }


    }

    private suspend fun <T>  apiTry(call: suspend () -> T): GrpcWrapper<T> {
        return try {
            GrpcWrapper.Success(call.invoke())
        } catch (ex: StatusRuntimeException) {
            GrpcWrapper.GrpcError(message = "${ex.message}", error = "${ex.message}")
        }catch (ex:Throwable){
            GrpcWrapper.UnknownError(message = "${ex.message}")
        }
    }
}