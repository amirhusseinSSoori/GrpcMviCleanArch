package com.amirhusseinsoori.grpcmviarch.data.di

import android.provider.SyncStateContract
import com.amirhusseinsoori.grpcmviarch.common.Constance.BASE_URL
import com.amirhusseinsoori.grpcmviarch.common.Constance.PORT
import com.amirhusseinsoori.grpcmviarch.data.network.TimeoutInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.domain.mizannodes.MizanNodesGrpc
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun setBlockingStub(
        timeout: TimeoutInterceptor,
        channel: ManagedChannel
    ): MizanNodesGrpc.MizanNodesBlockingStub {
        return MizanNodesGrpc.newBlockingStub(
            channel
        ).withInterceptors(timeout)
    }

    @Singleton
    @Provides
    fun provideChannel(): ManagedChannel {
        return ManagedChannelBuilder.forAddress(
            BASE_URL,
            PORT
        )
            .usePlaintext()
            .build()
    }

}