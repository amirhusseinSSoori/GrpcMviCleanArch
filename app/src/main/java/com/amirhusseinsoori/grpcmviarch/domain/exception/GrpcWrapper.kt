package com.amirhusseinsoori.grpcmviarch.domain.exception

sealed class GrpcWrapper<T>(
    val data: T? = null,
    val message: String? = null,
    val error: String? = null,
){
    class Empty<T> : GrpcWrapper<T>()
    class Loading<T>(data: T? = null) : GrpcWrapper<T>(data)
    class Success <T>(data: T): GrpcWrapper<T>(data )
    class Error<T>( data: T? = null,message: String) : GrpcWrapper<T>(data, message)
    class GrpcError<T> (message: String, error: String): GrpcWrapper<T>(null,message,error)
    class UnknownError<T>(message: String): GrpcWrapper<T>(null,null,message)

}