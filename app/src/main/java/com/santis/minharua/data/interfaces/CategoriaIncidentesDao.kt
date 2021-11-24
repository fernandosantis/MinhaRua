package com.santis.minharua.data.interfaces

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.santis.minharua.data.model.CategoriaIncidentes

@Dao
interface CategoriaIncidentesDao {
    @Transaction
    @Query("SELECT * FROM incidentes INNER JOIN categorias ON cat_id=id_cat WHERE inc_cep LIKE '%' || :ceps || '%'")
    fun getCategoriaeIncidentes(ceps: String): MutableList<CategoriaIncidentes>
}
