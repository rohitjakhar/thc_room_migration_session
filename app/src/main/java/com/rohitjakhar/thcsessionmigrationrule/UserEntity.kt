package com.rohitjakhar.thcsessionmigrationrule

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserTable")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "user name")
    val name: String,
)