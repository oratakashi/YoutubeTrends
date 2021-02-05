package com.oratakashi.youtube.presentation.magic.mapper

import com.oratakashi.youtube.domain.magic.Map
import com.oratakashi.youtube.domain.model.main.ItemModel
import com.oratakashi.youtube.presentation.model.main.Items
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.javaField

fun generate(
    data: ItemModel,
    source: KClass<out ItemModel>,
    target: KClass<Items>
): Items {
    return Items().apply {
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
        if (data.snippet != null) {
            data.snippet!!::class.declaredMemberProperties.forEach {
                if (it.findAnnotation<Map>() != null) {
                    val value = it.findAnnotation<Map>()

                    if (value != null) {
                        if (value.to.isNotEmpty()) {
                            try {
                                val sourceProperty = it.javaField
                                sourceProperty?.isAccessible = true

                                val targetProperty = target.java.getDeclaredField(value.to)
                                targetProperty.isAccessible = true
                                targetProperty.set(this, sourceProperty?.get(data.snippet))
                            } catch (e: NoSuchFieldException) {
                                e.printStackTrace()
                            } catch (e: IllegalArgumentException) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }
            if (data.snippet?.thumnails != null) {
                if (data.snippet?.thumnails?.medium != null)
                    data.snippet!!.thumnails?.medium!!::class.declaredMemberProperties.forEach {
                        if (it.findAnnotation<Map>() != null) {
                            val value = it.findAnnotation<Map>()

                            if (value != null) {
                                if (value.to.isNotEmpty()) {
                                    try {
                                        val sourceProperty = it.javaField
                                        sourceProperty?.isAccessible = true

                                        val targetProperty = target.java.getDeclaredField(value.to)
                                        targetProperty.isAccessible = true
                                        targetProperty.set(
                                            this,
                                            sourceProperty?.get(data.snippet?.thumnails?.medium)
                                        )
                                    } catch (e: NoSuchFieldException) {
                                        e.printStackTrace()
                                    } catch (e: IllegalArgumentException) {
                                        e.printStackTrace()
                                    }
                                }
                            }
                        }
                    }
                if (data.snippet?.thumnails?.standard != null)
                    data.snippet!!.thumnails?.standard!!::class.declaredMemberProperties.forEach {
                        if (it.findAnnotation<Map>() != null) {
                            val value = it.findAnnotation<Map>()

                            if (value != null) {
                                if (value.to.isNotEmpty()) {
                                    try {
                                        val sourceProperty = it.javaField
                                        sourceProperty?.isAccessible = true

                                        val targetProperty = target.java.getDeclaredField(value.to)
                                        targetProperty.isAccessible = true
                                        targetProperty.set(
                                            this,
                                            sourceProperty?.get(data.snippet?.thumnails?.standard)
                                        )
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
        if (data.statistics != null) data.statistics!!::class.declaredMemberProperties.forEach {
            if (it.findAnnotation<Map>() != null) {
                val value = it.findAnnotation<Map>()

                if (value != null) {
                    if (value.to.isNotEmpty()) {
                        try {
                            val sourceProperty = it.javaField
                            sourceProperty?.isAccessible = true

                            val targetProperty = target.java.getDeclaredField(value.to)
                            targetProperty.isAccessible = true
                            targetProperty.set(this, sourceProperty?.get(data.statistics))
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