package com.oratakashi.youtube.data.magic.mapper

import android.util.Log
import com.oratakashi.youtube.data.magic.toFavoriteModel
import com.oratakashi.youtube.data.model.fav.Favorite
import com.oratakashi.youtube.domain.magic.Map
import com.oratakashi.youtube.domain.model.favorite.FavoriteModel
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.javaField

fun generate(
    data: FavoriteModel,
    source: KClass<out FavoriteModel>,
    target: KClass<Favorite>
): Favorite {
    return Favorite().apply {
        source.declaredMemberProperties.forEach {
            if (it.findAnnotation<Map>() != null) {
                val value = it.findAnnotation<Map>()

                if (value != null) {
                    if (value.to.isNotEmpty()) {
                        try {
                            val sourceProperty = it.javaField
                            sourceProperty?.isAccessible = true

                            Log.d(
                                "debug",
                                "Converting field : ${target.java.getDeclaredField(value.to)}"
                            )

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
    data: Favorite,
    source: KClass<out Favorite>,
    target: KClass<FavoriteModel>
): FavoriteModel {
    return FavoriteModel().apply {
        source.declaredMemberProperties.forEach {
            if (it.findAnnotation<Map>() != null) {
                val value = it.findAnnotation<Map>()

                if (value != null) {
                    if (value.to.isNotEmpty()) {
                        try {
                            val sourceProperty = it.javaField
                            sourceProperty?.isAccessible = true

                            Log.d(
                                "debug",
                                "Converting field : ${target.java.getDeclaredField(value.to)}"
                            )

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
    data: List<Favorite>
): List<FavoriteModel> {
    val output: MutableList<FavoriteModel> = ArrayList()

    data.forEach {
        output.add(it.toFavoriteModel())
    }

    return output
}