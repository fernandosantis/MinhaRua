package com.santis.minharua.data

import androidx.room.*

@Dao
interface IncidenteDao {
    @Insert
    suspend fun inserirIncidente(categoria: Incidente)

    @Update
    suspend fun atualizarIncidente(categoria: Incidente)

    @Delete
    suspend fun excluirIncidente(categoria: Incidente)

    @Query("SELECT * FROM incidentes ORDER BY inc_id")
    suspend fun todosIncidentes(): List<Incidente>

    @Query("SELECT * FROM incidentes WHERE inc_id = :id")
    suspend fun buscaIncidentePorId(id: Int): List<Incidente>
}
