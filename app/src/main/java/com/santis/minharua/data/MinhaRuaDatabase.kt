package com.santis.minharua.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.santis.minharua.data.interfaces.CategoriaDao
import com.santis.minharua.data.interfaces.CategoriaIncidentesDao
import com.santis.minharua.data.interfaces.IncidenteDao
import com.santis.minharua.data.model.Categoria
import com.santis.minharua.data.model.Incidente

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
                ).createFromAsset("database/minharua_db.db").build()
                INSTANCE = instance
                instance
            }
        }
    }
}
