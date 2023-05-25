package com.vladislaviliev.shopdemo.db

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.time.LocalDate

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val name: String,
    val category: Category,
    val shortDescription: String,
    val longDescription: String,
    val price: BigDecimal,
    val date: LocalDate,
    val imgUri: Uri
)