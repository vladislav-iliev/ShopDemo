package com.vladislaviliev.shopdemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.vladislaviliev.shopdemo.App
import com.vladislaviliev.shopdemo.ui.purchase.PurchaseVm
import com.vladislaviliev.shopdemo.ui.edit.create.CreateVm
import com.vladislaviliev.shopdemo.ui.edit.view.ViewVm
import com.vladislaviliev.shopdemo.ui.home.HomeVm

class DaoVmFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val dao = (extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App).dbDao
        return when (modelClass) {
            CreateVm::class.java -> CreateVm(dao) as T
            HomeVm::class.java -> HomeVm(dao) as T
            ViewVm::class.java -> ViewVm(dao) as T
            else -> PurchaseVm(dao) as T
        }
    }
}