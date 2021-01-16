package com.oratakashi.youtube.presentation.magic.mapper

import com.oratakashi.youtube.domain.magic.Map
import com.oratakashi.youtube.domain.model.favorite.FavoriteModel
import com.oratakashi.youtube.presentation.model.main.Items
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.javaField

fun generate(
    data : Items,
    source: KClass<out Items>,
    target: KClass<FavoriteModel>
) : FavoriteModel {
    return FavoriteModel().apply {
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