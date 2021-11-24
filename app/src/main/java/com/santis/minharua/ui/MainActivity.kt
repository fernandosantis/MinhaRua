package com.santis.minharua.ui

import android.os.Bundle
import android.os.StrictMode
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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

        // Menu

        setSupportActionBar(binding.bottomAppBar)
        binding.bottomAppBar.setNavigationOnClickListener { finish() }

        updateUI()
    }

    private fun updateUI() {

        val db = MinhaRuaDatabase.abrirBanco(this)
        var incidentesLista: List<CategoriaIncidentes>

        val recyclerview = binding.rvIncidentes
        recyclerview.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch {
            incidentesLista =
                db.catincidenteDao().getCategoriaeIncidentes(MinhaRua.cep?.cep.toString())
            catincidentesAdapter = CatIncidentesAdapter(incidentesLista)
            recyclerview.adapter = catincidentesAdapter
            if (incidentesLista.isEmpty()) {
                recyclerview.visibility = View.GONE
                binding.noLayIncidente.visibility = View.VISIBLE
            } else {
                binding.noLayIncidente.visibility = View.GONE
                recyclerview.visibility = View.VISIBLE
            }
        }

        // Preenche Placa de Rua
        binding.lblLogradouro.text = MinhaRua.cep?.logradouro
        binding.lblEndereco.text = MinhaRua.cep?.endereco
        binding.lblNumero.text = MinhaRua.cep?.complemento
        binding.lblBairro.text = MinhaRua.cep?.bairro
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }
}
