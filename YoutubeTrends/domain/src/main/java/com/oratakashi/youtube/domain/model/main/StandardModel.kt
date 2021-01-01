package com.oratakashi.youtube.domain.model.main

import com.oratakashi.youtube.domain.magic.Map

data class StandardModel(
    @Map("urlStandard")
    val url: String,
    val width: Int,
    val height: Int
)