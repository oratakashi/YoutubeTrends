package com.oratakashi.youtube.presentation.magic

import com.oratakashi.youtube.domain.model.favorite.FavoriteModel
import com.oratakashi.youtube.domain.model.main.ItemModel
import com.oratakashi.youtube.presentation.magic.mapper.generate
import com.oratakashi.youtube.presentation.model.main.Items

/**
 * Main Magic to Convert from Domain Model to Presentation Model
 */

fun ItemModel.toItems() = generate(this, this::class, Items::class)

fun Items.toFavoriteModel() = generate(this, this::class, FavoriteModel::class)

fun FavoriteModel.toItems() = generate(this, this::class, Items::class)

fun List<FavoriteModel>.toItems() = generate(this)