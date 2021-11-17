package com.santis.minharua.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "categorias")
data class Categoria(
    @PrimaryKey (autoGenerate = true) val cat_id: Int? = null,
    @ColumnInfo (name = "cat_nome") val nomeCat: String?,
    @ColumnInfo (name = "cat_cor") val corCat: String?,
    @ColumnInfo (name = "cat_icone") val iconeCat: String?,
)
