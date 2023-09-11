package com.example.taskmanager.ui.notifications.adapter

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ItemTaskBinding
import com.example.taskmanager.model.Cinema
import com.example.taskmanager.model.Task

class CinemaAdapter(
) : RecyclerView.Adapter<CinemaAdapter.CinemaViewHolder>() {

    private val list = arrayListOf<Cinema>()

    fun addCinema(tasks: List<Cinema>) {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder {
        return CinemaViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class CinemaViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cinema: Cinema) = with(binding) {
            tvTitle.text = cinema.name
            tvDesc.text = cinema.director

        }
    }
}