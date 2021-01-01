package com.oratakashi.youtube.domain.model.main

import com.oratakashi.youtube.domain.magic.Map

data class MediumModel(
    @Map("urlMedium")
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0
)