package com.santis.minharua.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Categoria::class, Incidente::class], version = 1)
abstract class MinhaRuaDatabase : RoomDatabase() {
    abstract fun categoriaDao(): CategoriaDao
    abstract fun incidenteDao(): IncidenteDao
    abstract fun catincidenteDao(): CategoriaIncidentesDao

    companion object {
        @Volatile
        private var INSTANCE: MinhaRuaDatabase? = null

        fun abrirBanco(context: Context): MinhaRuaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MinhaRuaDatabase::class.java,
                    "minharua.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
