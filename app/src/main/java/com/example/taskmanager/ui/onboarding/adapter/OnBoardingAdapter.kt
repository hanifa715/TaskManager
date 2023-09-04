package com.example.taskmanager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ItemOnBoardingBinding
import com.example.taskmanager.model.OnBoarding

class OnBoardingAdapter(
    private val onClick: () -> Unit,
) : Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val list = arrayListOf(
        OnBoarding(
            title = "Track your tasks progress",
            description = "Never forget important things",
            anim = R.raw.animation_llw3trlq
        ),
        OnBoarding(
            title = "Your personal task manager",
            description = "Free up some time for yourself",
            anim = R.raw.animation_llw468yt
        ),
        OnBoarding(
            title = "Complete tasks easily",
            description = "Leave no job behind",
            anim = R.raw.animation_llw46opi
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnBoardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnBoardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoarding: OnBoarding) = with(binding) {
            tvTitle.text = onBoarding.title
            tvDesc.text = onBoarding.description
            onBoarding.anim?.let {
                binding.animBoard.setAnimation(onBoarding.anim)
                binding.animBoard.playAnimation()
            }

            binding.btnStart.isVisible = adapterPosition == list.lastIndex
            binding.btnSkip.isVisible = adapterPosition != list.lastIndex

            binding.btnStart.setOnClickListener {
                onClick()
            }
            binding.btnSkip.setOnClickListener {
                onClick()
            }
        }
    }
}