package com.vladislaviliev.shopdemo.ui.home

import androidx.lifecycle.*
import com.vladislaviliev.shopdemo.db.ItemDao
import com.vladislaviliev.shopdemo.db.ItemShort
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class HomeVm(private val dao: ItemDao) : ViewModel() {

    private val _allShorts = MutableLiveData<List<ItemShort>>()
    val allShorts: LiveData<List<ItemShort>> = _allShorts

    fun getAllShorts() {
        viewModelScope.launch {
            val res = withContext(Dispatchers.IO) { dao.getAllShorts() }
            _allShorts.value = res
        }
    }
}