package com.oratakashi.youtube.data.magic

import com.oratakashi.youtube.data.model.main.Medium
import com.oratakashi.youtube.domain.model.main.MediumModel
import kotlin.reflect.KClass

fun Medium.toDomain() : MediumModel {
    val output = MediumModel()
    val clazz = this::class
    val objTarget : KClass<MediumModel> = MediumModel::class

    return output
}