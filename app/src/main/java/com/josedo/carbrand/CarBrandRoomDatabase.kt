package com.josedo.carbrand

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(CarBrand::class), version = 1, exportSchema = false)
abstract class CarBrandRoomDatabase: RoomDatabase() {
    abstract fun carBrandDAO(): CarBrandDAO

    companion object {
        private const val DATABASE_NAME = "brands_models_table"

        @Volatile
        private var INSTANCE: CarBrandRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CarBrandRoomDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarBrandRoomDatabase::class.java,
                    DATABASE_NAME
                ).addCallback(
                    BrandDatabaseCallBack(
                        scope,
                        context
                    )
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class BrandDatabaseCallBack(
        private val scope: CoroutineScope, private val context: Context
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.carBrandDAO(), context)
                }
            }
        }

        suspend fun populateDatabase(carBrandDetailsDAO: CarBrandDAO, context: Context) {
            carBrandDetailsDAO.deleteAll()

            val brand1: CarBrand =
                CarBrand("Seat", "Spain", 1950)
            carBrandDetailsDAO.insert(brand1)

            val brand2: CarBrand =
                CarBrand("Ford", "USA", 1903)
            carBrandDetailsDAO.insert(brand2)

        }
    }
}