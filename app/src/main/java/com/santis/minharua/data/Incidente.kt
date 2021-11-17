package com.santis.minharua.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "incidentes")
data class Incidente(
    @PrimaryKey (autoGenerate = true) val inc_id: Int,
    @ColumnInfo(name = "inc_titulo") val tituloInc: String?,
    @ColumnInfo(name = "inc_descricao") val descricaoInc: String?,
    @ColumnInfo(name = "inc_imagem") val imagemInc: String?
)
