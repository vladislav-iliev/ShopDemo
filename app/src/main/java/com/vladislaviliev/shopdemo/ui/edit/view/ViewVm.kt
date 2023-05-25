package com.vladislaviliev.shopdemo.ui.edit.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladislaviliev.shopdemo.db.Item
import com.vladislaviliev.shopdemo.db.ItemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class ViewVm(private val dao: ItemDao) : ViewModel() {

    private val _item = MutableLiveData<Item>()
    val item: LiveData<Item> = _item

    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    fun load(id: Int) {
        viewModelScope.launch {
            _loading.value = true
            val fetchedItem = withContext(Dispatchers.IO) { dao.getById(id) }
            _item.value = fetchedItem
            _loading.value = false
        }
    }

    fun update(item: Item) {
        viewModelScope.launch(Dispatchers.IO) { dao.update(item) }
    }

    fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) { dao.delete(id) }
    }
}