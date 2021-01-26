package com.oratakashi.youtube.domain.magic

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Map(val to: String)