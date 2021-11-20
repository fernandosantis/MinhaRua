package com.santis.minharua.data.model

import java.io.Serializable

data class CEP(
    val cep: String,
    val logradouro: String,
    val endereco: String,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val complemento: String
) : Serializable
