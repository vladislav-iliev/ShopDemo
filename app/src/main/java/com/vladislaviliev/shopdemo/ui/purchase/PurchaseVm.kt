package com.vladislaviliev.shopdemo.ui.purchase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladislaviliev.shopdemo.db.ItemDao
import com.vladislaviliev.shopdemo.db.ItemShort
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class PurchaseVm(private val dao: ItemDao) : ViewModel() {

    private val _shorts = MutableLiveData<List<ItemShort>>()
    val shorts: LiveData<List<ItemShort>> = _shorts

    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    fun getShorts(cart: Map<Int, Int>) {
        viewModelScope.launch {
            _loading.value = true
            val newList = mutableListOf<ItemShort>()
            val newItemsSet = withContext(Dispatchers.IO) { dao.getShorts(cart.keys) }
            newItemsSet.forEach { i ->
                repeat(cart[i.id]!!) { newList.add(i) }
            }
            _shorts.value = newList
            _loading.value = false
        }
    }
}