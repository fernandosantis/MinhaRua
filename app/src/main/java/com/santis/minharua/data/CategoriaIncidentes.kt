package com.santis.minharua.data

import androidx.room.Embedded
import androidx.room.Relation

class CategoriaIncidentes(
    @Embedded val categoria: Categoria,
    @Relation(
        parentColumn = "cat_id",
        entityColumn = "id_cat"
    )
    val incidentes: List<Incidente>
)
