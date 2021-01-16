package com.oratakashi.youtube.data.magic

import com.oratakashi.youtube.data.magic.mapper.generate
import com.oratakashi.youtube.data.model.fav.Favorite
import com.oratakashi.youtube.data.model.main.*
import com.oratakashi.youtube.domain.model.favorite.FavoriteModel
import com.oratakashi.youtube.domain.model.main.*

/**
 * Main Magic to Convert from Data Model to Domain Model
 */
fun Medium.toMediumModel(): MediumModel =
    generate(this, this::class, MediumModel::class)

fun Standard.toStandardModel(): StandardModel =
    generate(this, this::class, StandardModel::class)

fun Statistics.toStatisticsModel(): StatisticsModel =
    generate(this, this::class, StatisticsModel::class)

fun Snippet.toSnippetModel(): SnippetModel =
    generate(this, this::class, SnippetModel::class)

fun Items.toItemModel(): ItemModel =
    generate(this, this::class, ItemModel::class)

fun ResponseMain.toItemModels(): List<ItemModel> =
    generate(this)

fun FavoriteModel.toFavorite(): Favorite =
    generate(this, this::class, Favorite::class)

fun Favorite.toFavoriteModel(): FavoriteModel =
    generate(this, this::class, FavoriteModel::class)