package com.santis.minharua.ui

import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.santis.minharua.MinhaRua
import com.santis.minharua.data.CatIncidentesAdapter
import com.santis.minharua.data.MinhaRuaDatabase
import com.santis.minharua.data.model.CategoriaIncidentes
import com.santis.minharua.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var catincidentesAdapter: CatIncidentesAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        // Contornar Restrições para Compartilhar Cards
        val builder: StrictMode.VmPolicy.Builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)
        // Testes.populaTestes(this)

        updateUI()
    }

    private fun updateUI() {

        // Preenche Placa de Rua
        binding.lblLogradouro.text = MinhaRua.cep?.logradouro
        binding.lblEndereco.text = MinhaRua.cep?.endereco
        binding.lblNumero.text = MinhaRua.cep?.complemento
        binding.lblBairro.text = MinhaRua.cep?.bairro

        val db = MinhaRuaDatabase.abrirBanco(this)
        var incidentesLista: List<CategoriaIncidentes>

        val recyclerview = findViewById<RecyclerView>(com.santis.minharua.R.id.rv_incidentes)
        recyclerview.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch {
            incidentesLista =
                db.catincidenteDao().getCategoriaeIncidentes(MinhaRua.cep?.cep.toString())
            catincidentesAdapter = CatIncidentesAdapter(incidentesLista)
            recyclerview.adapter = catincidentesAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }
}
