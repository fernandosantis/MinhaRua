package com.santis.minharua.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.santis.minharua.R
import java.io.Serializable

@Entity(tableName = "categorias")
data class Categoria(
    @PrimaryKey(autoGenerate = true) val cat_id: Int? = null,
    @ColumnInfo(name = "cat_nome") val nomeCat: String?,
    @ColumnInfo(name = "cat_cor") val corCat: Int = R.color.primaryDarkColor,
    @ColumnInfo(name = "cat_icone") val iconeCat: Int = R.drawable.ic_outros,
) : Serializable
