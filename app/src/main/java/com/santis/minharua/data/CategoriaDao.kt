package com.santis.minharua.data

import androidx.room.*

@Dao
interface CategoriaDao {
    @Insert
    fun inserirCategoria(categoria: Categoria)

    @Update
    fun atualizarCategoria(categoria: Categoria)

    @Delete
    fun excluirCategoria(categoria: Categoria)

    @Query("SELECT * FROM categorias ORDER BY cat_nome")
    fun todasCategorias(): List<Categoria>

    @Query("SELECT * FROM categorias WHERE cat_id = :id")
    fun buscaCatPorId(id: Int): List<Categoria>

    @Query("SELECT * FROM categorias WHERE cat_nome LIKE :nome")
    fun buscaCatPorNome(nome: String): List<Categoria>
}
