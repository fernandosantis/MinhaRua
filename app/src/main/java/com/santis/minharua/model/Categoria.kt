package com.santis.minharua.model

import java.io.Serializable

class Categoria(
    var id: Int? = null,
    var nome: String = "",
    var cor: String = "",
    var icon: String = ""
) : Serializable
