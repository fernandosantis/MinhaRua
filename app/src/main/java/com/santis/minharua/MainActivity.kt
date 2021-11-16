package com.santis.minharua

import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.santis.minharua.data.DatabaseHelper
import com.santis.minharua.data.OcorrenciaAdapter
import com.santis.minharua.databinding.ActivityMainBinding
import com.santis.minharua.model.Categoria
import com.santis.minharua.model.Ocorrencia

class MainActivity : AppCompatActivity() {

    val db = DatabaseHelper(this)
    var ocorrenciaList = ArrayList<Ocorrencia>()
    lateinit var ocorrenciaAdapter: OcorrenciaAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val builder: StrictMode.VmPolicy.Builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)
        // iniciarteste()
        updateUI()
    }

    fun iniciarteste() {
        db.categoriaInsert(Categoria(null, "Veículos abandonados", "#FF03DAC5", Uri.parse("android.resource://$packageName/${R.drawable.ic_trash}").toString()))
        db.categoriaInsert(Categoria(null, "Ponto de ônibus", "#FF3700B3", Uri.parse("android.resource://$packageName/${R.drawable.ic_edit}").toString()))
        db.categoriaInsert(Categoria(null, "Papeis Jogados", "#FF6200EE", Uri.parse("android.resource://$packageName/${R.drawable.ic_color}").toString()))
        db.categoriaInsert(Categoria(null, "Buracos", "#FF018786", Uri.parse("android.resource://$packageName/${R.drawable.ic_add_map}").toString()))
        for (a in 1..4) {
            db.inserirOcorrencia(Ocorrencia(a, "Ocorrencia $a", "Esta é a descrição completa do problema $a. Esta é a descrição completa do problema $a", Uri.parse("android.resource://$packageName/${R.drawable.ic_city}").toString(), "13660-03$a", a))
        }
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    fun updateUI() {
        ocorrenciaList = db.listarOcorrencias()
        ocorrenciaAdapter = OcorrenciaAdapter(ocorrenciaList)

        val recyclerview = findViewById<RecyclerView>(R.id.rv_ocorrencias)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = ocorrenciaAdapter
    }
}
