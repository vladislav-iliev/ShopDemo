package com.vladislaviliev.shopdemo.db

import androidx.room.*

@Dao
interface ItemDao {
    @Query("SELECT * FROM item WHERE id = :id")
    suspend fun getById(id: Int): Item

    @Query("SELECT id, name, category, shortDescription, price, imgUri FROM item")
    suspend fun getAllShorts(): List<ItemShort>

    @Query("SELECT id, name, category, shortDescription, price, imgUri FROM item WHERE id IN (:ids)")
    suspend fun getShorts(ids: Set<Int>): List<ItemShort>

    @Insert
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Query("DELETE FROM item WHERE id = :id")
    suspend fun delete(id: Int)
}