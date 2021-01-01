package com.oratakashi.youtube.data.magic.mapper

import com.oratakashi.youtube.data.model.main.Medium
import com.oratakashi.youtube.data.model.main.Standard
import com.oratakashi.youtube.data.model.main.Statistics
import com.oratakashi.youtube.domain.magic.Map
import com.oratakashi.youtube.domain.model.main.MediumModel
import com.oratakashi.youtube.domain.model.main.StandardModel
import com.oratakashi.youtube.domain.model.main.StatisticsModel
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.javaField

fun generate(data : Medium, source : KClass<out Medium>, target : KClass<MediumModel>) : MediumModel {
    val output = MediumModel()

    source.declaredMemberProperties.forEach {
        if(it.findAnnotation<Map>() != null){
            val value = it.findAnnotation<Map>()

            if(value!= null) {
                if(value.to.isNotEmpty()){
                    try {
                        val sourceProperty = it.javaField
                        sourceProperty?.isAccessible = true

                        val targetProperty = target.java.getDeclaredField(value.to)
                        targetProperty.isAccessible = true
                        targetProperty.set(output, sourceProperty?.get(data))
                    }catch (e : NoSuchFieldException){
                        e.printStackTrace()
                    }catch (e : IllegalArgumentException){
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    return output
}

fun generate(data : Standard, source : KClass<out Standard>, target : KClass<StandardModel>) : StandardModel{
    val output = StandardModel()

    source.declaredMemberProperties.forEach {
        if(it.findAnnotation<Map>() != null){
            val value = it.findAnnotation<Map>()

            if(value!= null) {
                if(value.to.isNotEmpty()){
                    try {
                        val sourceProperty = it.javaField
                        sourceProperty?.isAccessible = true

                        val targetProperty = target.java.getDeclaredField(value.to)
                        targetProperty.isAccessible = true
                        targetProperty.set(output, sourceProperty?.get(data))
                    }catch (e : NoSuchFieldException){
                        e.printStackTrace()
                    }catch (e : IllegalArgumentException){
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    return output
}

fun generate(data : Statistics, source: KClass<out Statistics>, target: KClass<StatisticsModel>) : StatisticsModel {
    val output = StatisticsModel()

    source.declaredMemberProperties.forEach {
        if(it.findAnnotation<Map>() != null){
            val value = it.findAnnotation<Map>()

            if(value!= null) {
                if(value.to.isNotEmpty()){
                    try {
                        val sourceProperty = it.javaField
                        sourceProperty?.isAccessible = true

                        val targetProperty = target.java.getDeclaredField(value.to)
                        targetProperty.isAccessible = true
                        targetProperty.set(output, sourceProperty?.get(data))
                    }catch (e : NoSuchFieldException){
                        e.printStackTrace()
                    }catch (e : IllegalArgumentException){
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    return output
}