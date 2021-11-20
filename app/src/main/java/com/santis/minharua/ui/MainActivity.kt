package com.santis.minharua.ui

import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nightonke.boommenu.BoomButtons.HamButton
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
        // Implementacao.implementaCategorias(this)
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
            incidentesLista = db.catincidenteDao().getCategoriaeIncidentes()
            catincidentesAdapter = CatIncidentesAdapter(incidentesLista)
            recyclerview.adapter = catincidentesAdapter
        }
        val bmb = binding.cmdNovoIncidente
        for (i in 0 until bmb.getPiecePlaceEnum().pieceNumber()) {
            val builder = HamButton.Builder()
                .normalImageRes(com.santis.minharua.R.drawable.ic_carro)
                .normalTextRes(16)
                .subNormalTextRes(12)
            bmb.addBuilder(builder)
        }
    }

    override fun onResume() {
        super.onResume()
        // updateUI()
    }
}
