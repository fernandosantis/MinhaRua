package com.santis.minharua.ui

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.santis.minharua.MinhaRua
import com.santis.minharua.R
import com.santis.minharua.data.MinhaRuaDatabase
import com.santis.minharua.databinding.ActivityAddIncidenteBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

class AddIncidente : AppCompatActivity() {


    private val binding by lazy { ActivityAddIncidenteBinding.inflate(layoutInflater) }
    lateinit var categoriaLista: List<String>
    lateinit var categoriaAdapter: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Menu
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { finish() }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Preencher Categorias
        updateUI()

        binding.cmdFoto.setOnClickListener {
            Toast.makeText(applicationContext,"Implantar Fazer Foto",Toast.LENGTH_LONG).show()
        }
    }



    private fun updateUI() {

        val autoCompleteTextView: AutoCompleteTextView = binding.cboCatAutocomplete
        val db = MinhaRuaDatabase.abrirBanco(applicationContext)

        runBlocking {
            GlobalScope.launch { categoriaLista = db.categoriaDao().todosNomesCategorias() }.join()
            categoriaAdapter = ArrayAdapter(applicationContext, R.layout.categoria_item, categoriaLista)
            autoCompleteTextView.setAdapter(categoriaAdapter)
        }

        // Preenche Localização
        binding.lblLocal.text = "${MinhaRua.cep?.logradouro} ${MinhaRua.cep?.endereco}"
        binding.lblBairro.text = MinhaRua.cep?.bairro
        binding.lblCep.text = MinhaRua.cep?.cep
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_incidente, menu)
        return true
    }
}
