package com.vladislaviliev.shopdemo.db

import android.net.Uri
import java.math.BigDecimal

data class ItemShort(
    val id: Int,
    val name: String,
    val category: Category,
    val shortDescription: String,
    val price: BigDecimal,
    val imgUri: Uri
)