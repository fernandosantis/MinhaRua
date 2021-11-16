package com.santis.minharua.model

import java.io.Serializable

class Ocorrencia(
    var id: Int? = null,
    var titulo: String = "",
    var descricao: String = "",
    var foto: String = "",
    var location: String = "",
    var cat_id: Int? = null
) : Serializable
