package com.oratakashi.youtube.data.magic.mapper

import android.util.Log
import com.oratakashi.youtube.data.magic.*
import com.oratakashi.youtube.data.model.main.*
import com.oratakashi.youtube.domain.magic.Map
import com.oratakashi.youtube.domain.model.main.*
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.javaField

fun generate(
    data: Medium,
    source: KClass<out Medium>,
    target: KClass<MediumModel>
): MediumModel {
    return MediumModel().apply {
        source.declaredMemberProperties.forEach {
            if (it.findAnnotation<Map>() != null) {
                val value = it.findAnnotation<Map>()

                if (value != null) {
                    if (value.to.isNotEmpty()) {
                        try {
                            val sourceProperty = it.javaField
                            sourceProperty?.isAccessible = true

                            val targetProperty = target.java.getDeclaredField(value.to)
                            targetProperty.isAccessible = true
                            targetProperty.set(this, sourceProperty?.get(data))
                        } catch (e: NoSuchFieldException) {
                            e.printStackTrace()
                        } catch (e: IllegalArgumentException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }
}

fun generate(
    data: Standard,
    source: KClass<out Standard>,
    target: KClass<StandardModel>
): StandardModel {
    return StandardModel().apply {
        source.declaredMemberProperties.forEach {
            if (it.findAnnotation<Map>() != null) {
                val value = it.findAnnotation<Map>()

                if (value != null) {
                    if (value.to.isNotEmpty()) {
                        try {
                            val sourceProperty = it.javaField
                            sourceProperty?.isAccessible = true

                            val targetProperty = target.java.getDeclaredField(value.to)
                            targetProperty.isAccessible = true
                            targetProperty.set(this, sourceProperty?.get(data))
                        } catch (e: NoSuchFieldException) {
                            e.printStackTrace()
                        } catch (e: IllegalArgumentException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }
}

fun generate(
    data: Statistics,
    source: KClass<out Statistics>,
    target: KClass<StatisticsModel>
): StatisticsModel {
    return StatisticsModel().apply {
        source.declaredMemberProperties.forEach {
            if (it.findAnnotation<Map>() != null) {
                val value = it.findAnnotation<Map>()

                if (value != null) {
                    if (value.to.isNotEmpty()) {
                        try {
                            val sourceProperty = it.javaField
                            sourceProperty?.isAccessible = true

                            val targetProperty = target.java.getDeclaredField(value.to)
                            targetProperty.isAccessible = true
                            targetProperty.set(this, sourceProperty?.get(data))
                        } catch (e: NoSuchFieldException) {
                            e.printStackTrace()
                        } catch (e: IllegalArgumentException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }
}

fun generate(
    data: Snippet,
    source: KClass<out Snippet>,
    target: KClass<SnippetModel>
): SnippetModel {
    return SnippetModel().apply {
        source.declaredMemberProperties.forEach {
            if (it.findAnnotation<Map>() != null) {
                val value = it.findAnnotation<Map>()

                if (value != null) {
                    if (value.to.isNotEmpty()) {
                        try {
                            val sourceProperty = it.javaField
                            sourceProperty?.isAccessible = true

                            val targetProperty = target.java.getDeclaredField(value.to)
                            targetProperty.isAccessible = true
                            targetProperty.set(this, sourceProperty?.get(data))
                        } catch (e: NoSuchFieldException) {
                            e.printStackTrace()
                        } catch (e: IllegalArgumentException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }

        var medium: MediumModel? = null
        var standard: StandardModel? = null

        if (data.thumbnails.medium != null) medium = data.thumbnails.medium.toMediumModel()
        if (data.thumbnails.standard != null) standard = data.thumbnails.standard.toStandardModel()

        thumnails = ThumnailsModel(
            medium,
            standard
        )
    }
}

fun generate(
    data: Items,
    source: KClass<out Items>,
    target: KClass<ItemModel>
): ItemModel {
    return ItemModel().apply {
        source.declaredMemberProperties.forEach {
            if (it.findAnnotation<Map>() != null) {
                val value = it.findAnnotation<Map>()

                if (value != null) {
                    if (value.to.isNotEmpty()) {
                        try {
                            val sourceProperty = it.javaField
                            sourceProperty?.isAccessible = true

                            val targetProperty = target.java.getDeclaredField(value.to)
                            targetProperty.isAccessible = true
                            targetProperty.set(this, sourceProperty?.get(data))
                        } catch (e: NoSuchFieldException) {
                            e.printStackTrace()
                        } catch (e: IllegalArgumentException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }

        statistics = data.statistics.toStatisticsModel()
        snippet = data.snippet.toSnippetModel()
    }
}

fun generate(data: ResponseMain): List<ItemModel> {
    val output: MutableList<ItemModel> = ArrayList()

    data.items.forEach {
        output.add(it.toItemModel())
    }

    return output.toList()
}