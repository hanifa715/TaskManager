package com.example.taskmanager.model

import java.io.Serializable

data class OnBoarding(
    val anim: Int? = null,
    val title: String? = null,
    val description: String? = null
) : Serializable