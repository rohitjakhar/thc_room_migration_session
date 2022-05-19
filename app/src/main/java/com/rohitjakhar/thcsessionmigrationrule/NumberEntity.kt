package com.rohitjakhar.thcsessionmigrationrule

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Number")
data class NumberEntity(
    @PrimaryKey
    @ColumnInfo(name = "number")
    val number: Long
)
