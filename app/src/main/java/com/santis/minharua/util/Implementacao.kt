package com.santis.minharua.util

import android.content.Context
import com.santis.minharua.R
import com.santis.minharua.data.Categoria
import com.santis.minharua.data.MinhaRuaDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Implementacao {
    companion object {
        fun implementaCategorias(contexto: Context) {
            val db = MinhaRuaDatabase.abrirBanco(contexto)
            GlobalScope.launch {
                with(db) {
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Calçadas",
                            R.color.teal_800,
                            R.drawable.ic_calcada
                        )
                    )
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Ponto de Ônibus",
                            R.color.cyan_800,
                            R.drawable.ic_ponto_onibus
                        )
                    )
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Iluminação Pública",
                            R.color.yellow_600,
                            R.drawable.ic_iluminacao
                        )
                    )
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Vazamento de Água",
                            R.color.light_blue_A200,
                            R.drawable.ic_agua
                        )
                    )
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Vazamento de Esgoto",
                            R.color.brown_500,
                            R.drawable.ic_esgoto
                        )
                    )
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Vazamento de Gás",
                            R.color.amber_900,
                            R.drawable.ic_gas
                        )
                    )

                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Ruas",
                            R.color.blue_grey_900.toInt(),
                            R.drawable.ic_ruas
                        )
                    )
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Bueiros Entupidos",
                            R.color.brown_600,
                            R.drawable.ic_bueiro
                        )
                    )
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Buracos",
                            R.color.brown_800,
                            R.drawable.ic_buraco
                        )
                    )
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Placas de Ruas / Identificação",
                            R.color.indigo_900,
                            R.drawable.ic_placas_ruas
                        )
                    )
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Sinais de Trânsito",
                            R.color.pink_700,
                            R.drawable.ic_sinais_transito
                        )
                    )
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Semáforos",
                            R.color.red_700,
                            R.drawable.ic_semaforo
                        )
                    )

                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Árvores",
                            R.color.light_green_700,
                            R.drawable.ic_arvore
                        )
                    )
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Parques e Praças",
                            R.color.green_900,
                            R.drawable.ic_parque
                        )
                    )
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Banheiros Públicos",
                            R.color.cyan_A400,
                            R.drawable.ic_banheiro
                        )
                    )
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Pixação - Graffiti",
                            R.color.purple_700,
                            R.drawable.ic_pixacao
                        )
                    )

                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Limpeza de Ruas",
                            R.color.blue_grey_600,
                            R.drawable.ic_limpeza_rua
                        )
                    )
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Papeis Jogados",
                            R.color.grey_700,
                            R.drawable.ic_papel_chao
                        )
                    )
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Lixo Jogado",
                            R.color.deep_orange_600,
                            R.drawable.ic_lixo
                        )
                    )
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Sujeira de Pet",
                            R.color.brown_600,
                            R.drawable.ic_sujeira_pet
                        )
                    )

                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Vagas e Estacionamento",
                            R.color.deep_purple_900,
                            R.drawable.ic_vagas
                        )
                    )
                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Veículos Abandonados",
                            R.color.blue_900,
                            R.drawable.ic_carro
                        )
                    )

                    categoriaDao().inserirCategoria(
                        Categoria(
                            null,
                            "Outros",
                            R.color.red_A400,
                            R.drawable.ic_outros
                        )
                    )
                }
            }
        }
    }
}
