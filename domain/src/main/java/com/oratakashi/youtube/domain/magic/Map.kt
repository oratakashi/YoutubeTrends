package com.oratakashi.youtube.domain.magic

import androidx.annotation.Keep

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Keep
annotation class Map(val to: String)