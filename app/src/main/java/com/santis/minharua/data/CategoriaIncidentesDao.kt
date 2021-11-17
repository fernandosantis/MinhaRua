package com.santis.minharua.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CategoriaIncidentesDao {
    @Transaction
    @Query("SELECT * FROM incidentes INNER JOIN categorias ON cat_id=id_cat ORDER BY inc_id")
    fun getCategoriaeIncidentes(): List<CategoriaIncidentes>
}
