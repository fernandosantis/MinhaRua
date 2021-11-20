package com.santis.minharua.data.interfaces

import androidx.room.*
import com.santis.minharua.data.model.Categoria

@Dao
interface CategoriaDao {
    @Insert
    suspend fun inserirCategoria(categoria: Categoria)

    @Update
    suspend fun atualizarCategoria(categoria: Categoria)

    @Delete
    suspend fun excluirCategoria(categoria: Categoria)

    @Query("SELECT * FROM categorias ORDER BY cat_nome")
    suspend fun todasCategorias(): List<Categoria>

    @Query("SELECT * FROM categorias WHERE cat_id = :id LIMIT 1")
    suspend fun buscaCatPorId(id: Int): Categoria

    @Query("SELECT * FROM categorias WHERE cat_nome LIKE :nome LIMIT 1")
    suspend fun buscaCatPorNome(nome: String): Categoria
}
