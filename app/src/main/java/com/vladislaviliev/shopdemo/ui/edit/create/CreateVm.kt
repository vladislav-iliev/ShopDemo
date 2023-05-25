package com.vladislaviliev.shopdemo.ui.edit.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladislaviliev.shopdemo.db.Item
import com.vladislaviliev.shopdemo.db.ItemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class CreateVm(private val dao: ItemDao) : ViewModel() {
    fun create(item: Item) {
        viewModelScope.launch(Dispatchers.IO) { dao.insert(item) }
    }
}