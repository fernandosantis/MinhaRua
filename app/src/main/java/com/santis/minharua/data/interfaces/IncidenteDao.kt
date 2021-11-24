package com.santis.minharua.data.interfaces

import androidx.room.*
import com.santis.minharua.data.model.Incidente

@Dao
interface IncidenteDao {

    @Update
    suspend fun atualizarIncidente(incidente: Incidente)

    @Query("SELECT * FROM incidentes WHERE inc_id = :id LIMIT 1")
    fun buscaIncidentePorId(id: Int): Incidente

    @Delete
    fun excluirIncidente(incidente: Incidente)

    @Query("DELETE FROM incidentes WHERE inc_id = :id")
    fun excluirIncidenteporID(id: Int)

    @Insert
    suspend fun inserirIncidente(incidente: Incidente)

    @Query("SELECT * FROM incidentes ORDER BY inc_id")
    suspend fun todosIncidentes(): List<Incidente>
}
