package com.amirhusseinsoori.grpcmviarch.presentation.intro

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.amirhusseinsoori.grpcmviarch.R
import com.amirhusseinsoori.grpcmviarch.databinding.FragmentGrpcBinding
import  com.amirhusseinsoori.grpcmviarch.presentation.util.startAnimation
import com.amirhusseinsoori.grpcmviarch.databinding.FragmentIntroBinding
import com.amirhusseinsoori.grpcmviarch.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class IntroFragment : BaseFragment<FragmentIntroBinding>(FragmentIntroBinding::inflate) {

    override fun sideEffect(){

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.explosion_anim).apply {
                duration = 1500
                interpolator = AccelerateDecelerateInterpolator()
            }


        binding.viewIntroFExplosion.isVisible = true
        binding.viewIntroFExplosion.startAnimation(animation) {
            binding.frameIntroFLogo.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
            binding.viewIntroFExplosion.isVisible = false
        }

        lifecycleScope.launchWhenStarted {
            navigation()
        }
    }

    private suspend fun navigation() {
        delay(1500)
        findNavController().navigate(R.id.action_introFragment_to_grpcFragment)
    }


}