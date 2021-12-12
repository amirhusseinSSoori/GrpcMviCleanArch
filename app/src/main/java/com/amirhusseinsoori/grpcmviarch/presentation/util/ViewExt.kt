package com.amirhusseinsoori.grpcmviarch.presentation.util

import android.view.View
import android.view.animation.Animation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow


fun View.startAnimation(animation: Animation, onEnd:() -> Unit){
    animation.setAnimationListener(object :Animation.AnimationListener{
        override fun onAnimationStart(animation: Animation?) = Unit
        override fun onAnimationEnd(animation: Animation?) {
               onEnd()
        }

        override fun onAnimationRepeat(animation: Animation?) = Unit



    })
    this.startAnimation(animation)

}
fun View.clicks(): Flow<Unit> = callbackFlow {
    setOnClickListener {
        trySend(Unit).isSuccess
    }
    awaitClose { setOnClickListener(null) }
}


@FlowPreview
@ExperimentalCoroutinesApi
fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> = flow {
    var lastEmissionTime = 0L
    collect { upstream ->
        val currentTime = System.currentTimeMillis()
        val mayEmit = currentTime - lastEmissionTime > windowDuration
        if (mayEmit)
        {
            lastEmissionTime = currentTime
            emit(upstream)
        }
    }
}

