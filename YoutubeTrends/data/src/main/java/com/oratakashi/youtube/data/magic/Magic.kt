package com.oratakashi.youtube.data.magic

import com.oratakashi.youtube.data.magic.mapper.generate
import com.oratakashi.youtube.data.model.main.Medium
import com.oratakashi.youtube.data.model.main.Standard
import com.oratakashi.youtube.data.model.main.Statistics
import com.oratakashi.youtube.domain.model.main.MediumModel
import com.oratakashi.youtube.domain.model.main.StandardModel
import com.oratakashi.youtube.domain.model.main.StatisticsModel
import kotlin.reflect.KClass

fun Medium.toMediumModel() : MediumModel = generate(this, this::class, MediumModel::class)
fun Standard.toStandardModel() : StandardModel = generate(this, this::class, StandardModel::class)
fun Statistics.toStatisticsModel() : StatisticsModel = generate(this, this::class, StatisticsModel::class)