package com.rohitjakhar.thcsessionmigrationrule

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [NameEntity::class], version = 1, exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myDap(): MyDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "testapp.db"
        )
            .build()
    }
}
