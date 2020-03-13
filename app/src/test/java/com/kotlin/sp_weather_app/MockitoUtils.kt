package com.kotlin.spweather_app

import org.mockito.Mockito

inline fun <reified T> mock(observable: Any?): T = Mockito.mock(T::class.java)