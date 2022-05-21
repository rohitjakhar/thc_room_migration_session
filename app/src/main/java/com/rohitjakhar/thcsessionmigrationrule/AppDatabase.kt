package com.rohitjakhar.thcsessionmigrationrule

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration

@Database(
    entities = [UserEntity::class, NumberEntity::class, RandomNumberEntity::class],
    version = 5,
    autoMigrations = [
        AutoMigration(from = 2, to = 3, spec = AppDatabase.RenameColoumn::class),
        AutoMigration(from = 3, to = 4, spec = AppDatabase.RenameTableName::class),
        AutoMigration(from = 4, to = 5)
    ],
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {
    @RenameColumn(tableName = "Name", fromColumnName = "name", toColumnName = "user name")
    class RenameColoumn : AutoMigrationSpec

    @RenameTable(fromTableName = "Name", toTableName = "UserTable")
    class RenameTableName : AutoMigrationSpec

    /*
        //TODO: Other Spec
        @DeleteTable("tableName")
        @DeleteColumn(tableName = "tableName", columnName = "columnName")

        */
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
