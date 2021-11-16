package com.santis.minharua.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.santis.minharua.model.Categoria
import com.santis.minharua.model.Ocorrencia

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME = "minharua.db"
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE_OCORRENCIAS =
            "CREATE TABLE ocorrencias (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, descricao TEXT, foto TEXT, location TEXT, cat_id INTEGER)"
        val CREATE_TABLE_CATEGORIAS =
            "CREATE TABLE categorias (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, cor TEXT, icon TEXT)"

        p0?.execSQL(CREATE_TABLE_OCORRENCIAS)
        p0?.execSQL(CREATE_TABLE_CATEGORIAS)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        /* if (p1<2) // migrando da versão 1 para 2
         {
             val sql = "ALTER TABLE $TABLE_NAME ADD COLUMN $FONE2 TEXT"
             p0?.execSL(sql)
         }
         if (p1<3) // migrando da versão 2 para 3
         {
             val sql = "ALTER TABLE $TABLE_NAME ADD COLUMN $ENDERECO TEXT"
             p0?.execSQL(sql)
         }*/
    }

    // Para Inserir as Categorias
    fun categoriaInsert(categoria: Categoria): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("id", categoria.id)
        values.put("nome", categoria.nome)
        values.put("cor", categoria.cor)
        values.put("icon", categoria.icon)
        val result = db.insert("categorias", null, values)
        db.close()
        return result
    }

    fun listarCategorias(): ArrayList<Categoria> {
        val categoriaList = ArrayList<Categoria>()
        val query = "SELECT * FROM categorias ORDER BY nome"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val cat = Categoria(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
            )
            categoriaList.add(cat)
        }
        cursor.close()
        db.close()
        return categoriaList
    }

    fun categoriaById(catId: Int = -1): Categoria? {
        return listarCategorias().find { it.id == catId }
    }

    // Ocorrências

    fun inserirOcorrencia(ocorrencia: Ocorrencia): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("id", ocorrencia.id)
        values.put("titulo", ocorrencia.titulo)
        values.put("descricao", ocorrencia.descricao)
        values.put("foto", ocorrencia.foto)
        values.put("location", ocorrencia.location)
        values.put("cat_id", ocorrencia.cat_id)
        val result = db.insert("ocorrencias", null, values)
        db.close()
        return result
    }

    fun atualizarOcorrencia(ocorrencia: Ocorrencia): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("id", ocorrencia.id)
        values.put("titulo", ocorrencia.titulo)
        values.put("descricao", ocorrencia.descricao)
        values.put("foto", ocorrencia.foto)
        values.put("location", ocorrencia.location)
        values.put("cat_id", ocorrencia.cat_id)
        val result = db.update("ocorrencias", values, "id=?", arrayOf(ocorrencia.id.toString()))
        db.close()
        return result
    }

    fun apagarOcorrencia(ocorrencia: Ocorrencia): Int {
        val db = this.writableDatabase
        val result = db.delete("ocorrencias", "id=?", arrayOf(ocorrencia.id.toString()))
        db.close()
        return result
    }

    fun listarOcorrencias(tipo: String = "", valor: String = ""): ArrayList<Ocorrencia> {
        val strwhere = when (tipo) {
            "id" -> "ORDER BY $tipo}"
            "cat_id" -> "WHERE $tipo=${valor.toInt()}"
            "location" -> "WHERE $tipo=$valor"
            else -> ""
        }
        val ocorrenciaList = ArrayList<Ocorrencia>()
        val query = "SELECT * FROM ocorrencias $strwhere"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val ocorr = Ocorrencia(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(5)
            )
            ocorrenciaList.add(ocorr)
        }
        cursor.close()
        db.close()
        return ocorrenciaList
    }
}
