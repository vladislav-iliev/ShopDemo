package com.vladislaviliev.shopdemo.ui

import androidx.lifecycle.*
import java.util.*

class CartVm : ViewModel() {

    private val _cart = MutableLiveData<MutableMap<Int, Int>>(mutableMapOf()) // id -> quantity
    val cart: LiveData<Map<Int, Int>> = _cart.map { Collections.unmodifiableMap(it) }

    fun addToCart(id: Int) {
        val m = _cart.value!!
        m[id] = m[id]?.plus(1) ?: 1
        _cart.value = m
    }

    fun removeFromCart(id: Int) {
        val m = _cart.value!!
        m[id] ?: return
        m[id] = m[id]!!.minus(1)
        if (m[id] == 0) m.remove(id)
        _cart.value = m
    }
}