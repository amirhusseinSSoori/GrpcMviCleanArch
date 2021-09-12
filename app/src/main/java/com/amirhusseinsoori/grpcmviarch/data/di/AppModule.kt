package com.amirhusseinsoori.grpcmviarch.data.di

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.provider.SyncStateContract
import com.amirhusseinsoori.grpcmviarch.common.Constance.BASE_URL
import com.amirhusseinsoori.grpcmviarch.common.Constance.PORT
import com.amirhusseinsoori.grpcmviarch.data.network.TimeoutInterceptor
import com.amirhusseinsoori.grpcmviarch.data.repository.GrpcRepositoryImp
import com.amirhusseinsoori.grpcmviarch.data.source.NetworkSource
import com.amirhusseinsoori.grpcmviarch.domain.repository.GrpcRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.domain.request.RequestNodesGrpc

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun setBlockingStub(
        timeout: TimeoutInterceptor,
        channel: ManagedChannel
    ): RequestNodesGrpc.RequestNodesBlockingStub {
        return RequestNodesGrpc.newBlockingStub(
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
            .executor(Executors.newSingleThreadExecutor())
            .usePlaintext()
            .build()
    }

    @SuppressLint("HardwareIds")
    @Provides
    fun provideAndroidId(@ApplicationContext context: Context): String {
        return Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }

    @Singleton
    @Provides
    fun provideAlarmRepository(grpc: NetworkSource): GrpcRepository = GrpcRepositoryImp(grpc)

}