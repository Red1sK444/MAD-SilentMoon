package com.r3d1r4ph.silentmoon.fragments.home

import android.widget.ImageView

data class RecommendationRecyclerItem(
    val image: Int,
    val title: String,
    val type: String,
    val duration: String,
    val primary: Int,
    val secondary: Int,
    val dot: Int
)
