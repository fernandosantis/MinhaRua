package com.santis.minharua.util

import android.content.Context
import com.santis.minharua.R
import com.santis.minharua.data.Incidente
import com.santis.minharua.data.MinhaRuaDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Testes {
    companion object {
        fun populaTestes(contexto: Context) {
            val db = MinhaRuaDatabase.abrirBanco(contexto)
            GlobalScope.launch {
                for (x in 1..23) {
                    db.incidenteDao().inserirIncidente(
                        Incidente(null, "Problema $x", "Aqui no minha rua está ocorrendo um grave problema do tipo $x que não tem mais jeito.", R.drawable.ic_city, x)
                    )
                }
            }
        }
    }
}
