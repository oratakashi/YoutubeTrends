package com.oratakashi.youtube.presentation.magic

import com.oratakashi.youtube.domain.model.main.ItemModel
import com.oratakashi.youtube.presentation.magic.mapper.*
import com.oratakashi.youtube.presentation.model.main.Items

fun ItemModel.toItems() = generate(this, this::class, Items::class)