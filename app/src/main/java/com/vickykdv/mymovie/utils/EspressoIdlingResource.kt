package com.vickykdv.mymovie.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource



object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private const val TESTING = "TESTING"
    val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)
    val espressoTest = CountingIdlingResource(TESTING)


    fun increment() {
        espressoTestIdlingResource.increment()
    }



    fun decrement() {
        espressoTestIdlingResource.decrement()
    }

    fun getEspressoIdlingResource(): IdlingResource {
        return espressoTestIdlingResource
    }
}