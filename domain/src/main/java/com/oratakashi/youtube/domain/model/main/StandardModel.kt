package com.oratakashi.youtube.domain.model.main

import com.oratakashi.youtube.domain.magic.Map

data class StandardModel(
    @Map("urlStandard")
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0
)