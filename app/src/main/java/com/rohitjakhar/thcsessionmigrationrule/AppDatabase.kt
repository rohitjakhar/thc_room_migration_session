package com.rohitjakhar.thcsessionmigrationrule

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration

@Database(
    entities = [NameEntity::class, NumberEntity::class],
    version = 3,
    autoMigrations = [
        AutoMigration(from = 2, to = 3, spec = AppDatabase.RenameUserTable::class)
    ],
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {
    @RenameColumn(tableName = "Name", fromColumnName = "name", toColumnName = "user name")
    class RenameUserTable : AutoMigrationSpec

    abstract fun myDap(): MyDao

    companion object {
        private val MIGRATION_1_2 = Migration(1, 2) {
            it.execSQL("CREATE TABLE IF NOT EXISTS `Number` (`number` INTEGER NOT NULL, PRIMARY KEY(`number`))")
        }

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
            .addMigrations(MIGRATION_1_2)
            .build()
    }
}
