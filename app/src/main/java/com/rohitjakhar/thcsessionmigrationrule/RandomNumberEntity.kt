package com.rohitjakhar.thcsessionmigrationrule

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Random Number")
data class RandomNumberEntity(
    @PrimaryKey
    val number: Int
)
