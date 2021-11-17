package com.santis.minharua.implementation

import android.app.Application
import com.santis.minharua.R
import com.santis.minharua.data.Categoria
import com.santis.minharua.data.MinhaRuaDatabase

class Categoria_Implem : Application() {
    fun Main() {
        val db = MinhaRuaDatabase.abrirBanco(this)
        db.categoriaDao().inserirCategoria(Categoria(null, "Calçadas", R.color.teal_800.toString(), R.drawable.ic_calcada.toString()))
        db.categoriaDao().inserirCategoria(Categoria(null, "Ponto de Ônibus", R.color.cyan_800.toString(), R.drawable.ic_ponto_onibus.toString()))
        db.categoriaDao().inserirCategoria(Categoria(null, "Iluminação Pública", R.color.yellow_600.toString(), R.drawable.ic_iluminacao.toString()))
        db.categoriaDao().inserirCategoria(Categoria(null, "Vazamento de Água", R.color.light_blue_A200.toString(), R.drawable.ic_agua.toString()))
        db.categoriaDao().inserirCategoria(Categoria(null, "Vazamento de Esgoto", R.color.brown_500.toString().toString(), R.drawable.ic_esgoto.toString()))
        db.categoriaDao().inserirCategoria(Categoria(null, "Vazamento de Gás", R.color.amber_900.toString(), R.drawable.ic_gas.toString()))

        db.categoriaDao().inserirCategoria(Categoria(null, "Ruas", R.color.blue_grey_900.toString(), R.drawable.ic_ruas.toString()))
        db.categoriaDao().inserirCategoria(Categoria(null, "Bueiros Entupidos", R.color.brown_600.toString(), R.drawable.ic_bueiro.toString()))
        db.categoriaDao().inserirCategoria(Categoria(null, "Buracos", R.color.brown_800.toString(), R.drawable.ic_buraco.toString()))
        db.categoriaDao().inserirCategoria(Categoria(null, "Placas de Ruas / Identificação", R.color.indigo_900.toString(), R.drawable.ic_placas_ruas.toString()))
        db.categoriaDao().inserirCategoria(Categoria(null, "Sinais de Trânsito", R.color.pink_700.toString(), R.drawable.ic_sinais_transito.toString()))
        db.categoriaDao().inserirCategoria(Categoria(null, "Semáforos", R.color.red_700.toString(), R.drawable.ic_semaforo.toString()))

        db.categoriaDao().inserirCategoria(Categoria(null, "Árvores", R.color.light_green_700.toString(), R.drawable.ic_arvore.toString()))
        db.categoriaDao().inserirCategoria(Categoria(null, "Parques e Praças", R.color.green_900.toString(), R.drawable.ic_parque.toString()))
        db.categoriaDao().inserirCategoria(Categoria(null, "Banheiros Públicos", R.color.cyan_A400.toString(), R.drawable.ic_banheiro.toString()))
        db.categoriaDao().inserirCategoria(Categoria(null, "Pixação - Graffiti", R.color.purple_700.toString(), R.drawable.ic_pixacao.toString()))

        db.categoriaDao().inserirCategoria(Categoria(null, "Limpeza de Ruas", R.color.blue_grey_600.toString(), R.drawable.ic_limpeza_rua.toString()))
        db.categoriaDao().inserirCategoria(Categoria(null, "Papeis Jogados", R.color.grey_700.toString(), R.drawable.ic_papel_chao.toString()))
        db.categoriaDao().inserirCategoria(Categoria(null, "Lixo Jogado", R.color.deep_orange_600.toString(), R.drawable.ic_lixo.toString()))
        db.categoriaDao().inserirCategoria(Categoria(null, "Sujeira de Pet", R.color.brown_600.toString(), R.drawable.ic_sujeira_pet.toString()))

        db.categoriaDao().inserirCategoria(Categoria(null, "Vagas e Estacionamento", R.color.deep_purple_900.toString(), R.drawable.ic_vagas.toString()))
        db.categoriaDao().inserirCategoria(Categoria(null, "Veículos Abandonados", R.color.blue_900.toString(), R.drawable.ic_carro.toString()))

        db.categoriaDao().inserirCategoria(Categoria(null, "Outros", R.color.red_A400.toString(), R.drawable.ic_outros.toString()))
    }
}
