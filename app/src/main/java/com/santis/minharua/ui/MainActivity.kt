package com.santis.minharua.ui

import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.santis.minharua.R
import com.santis.minharua.data.CategoriaIncidentes
import com.santis.minharua.data.MinhaRuaDatabase
import com.santis.minharua.databinding.ActivityMainBinding
import com.santis.minharua.model.CatIncidentesAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var catincidentesAdapter: CatIncidentesAdapter
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val builder: StrictMode.VmPolicy.Builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(activityMainBinding.root)
        // Implementacao.implementaCategorias(this)
        // Testes.populaTestes(this)
        updateUI()
    }
    private fun updateUI() {
        val db = MinhaRuaDatabase.abrirBanco(this)
        var incidentesLista: List<CategoriaIncidentes>

        val recyclerview = findViewById<RecyclerView>(R.id.rv_incidentes)
        recyclerview.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch {
            incidentesLista = db.catincidenteDao().getCategoriaeIncidentes()
            catincidentesAdapter = CatIncidentesAdapter(incidentesLista)
            recyclerview.adapter = catincidentesAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }
}
