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
) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {


    private val list = arrayListOf(
        OnBoarding(
            title = "Plan a Trip",
            description = "Be ready to depart anytime, anywhere",
            image = "https://rimage.gnst.jp/livejapan.com/public/article/detail/a/00/00/a0000276/img/basic/a0000276_main.jpg?20170427165412"
        ),
        OnBoarding(
            title = "Book the Flight",
            description = "Pay in seconds",
            image = "https://live.staticflickr.com/5301/5639492325_3f373fbac9_b.jpg"
        ),
        OnBoarding(
            title = "Enjoy your Trip",
            description = "Bringing you joy ",
            image = "https://www.xinhuanet.com/world/2016-09/08/129273462_14732907165901n.jpg"
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
            Glide.with(ivBoard).load(onBoarding.image).into(ivBoard)
            if (adapterPosition == list.lastIndex) {
                binding.btnStart.isVisible = true
            }
            binding.btnStart.setOnClickListener {
                onClick()
            }
        }
    }
}