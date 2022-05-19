package com.rohitjakhar.thcsessionmigrationrule

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addName(nameEntity: NameEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNumber(numberEntity: NumberEntity)
}
