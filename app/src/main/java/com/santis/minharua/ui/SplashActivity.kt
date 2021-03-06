package com.santis.minharua.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.santis.minharua.MinhaRua
import com.santis.minharua.data.model.CEP
import com.santis.minharua.databinding.ActivitySplashBinding
import com.santis.minharua.util.Anime
import com.santis.minharua.util.ConvertStreamString
import com.santis.minharua.util.hideKeyboard
import com.santis.minharua.util.parteFrase
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class SplashActivity : AppCompatActivity() {

    // Define Variaveis
    // Loading - ProgressBar
    val anime = Anime()

    // ViewBinding
    lateinit var binding: ActivitySplashBinding

    // SharePreferences
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Checa CEP em SharedPreferences e Devine Objeto CEP
        sharedPreferences = getSharedPreferences("cep_key", Context.MODE_PRIVATE)
        binding.txtCep.setText(carregaCep())
        MinhaRua.cep = null
        checaCep(false)

        // OnClickListener
        binding.cmdOk.setOnClickListener {
            salvarCep(binding.txtCep.text.toString())
            checaCep(true)
            it.hideKeyboard()
        }
    }

    // Verifica Cep digitado, define Objeto CEP e SharedPreferences
    private fun checaCep(checaerro: Boolean) {
        // Checa CEP e Vai para MainActivity
        binding.tilCep.error = null
        val strCep = binding.txtCep.getParsedText().toString()
        if (strCep.length != 8) {
            if (checaerro == true) {
                binding.tilCep.error = binding.tilCep.helperText
                Toast.makeText(
                    this,
                    "Cep inv??lido ou Imcompleto!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            val url = "https://viacep.com.br/ws/" + binding.txtCep.getParsedText()
                .toString() + "/json/"
            anime.tradeView(binding.pgCep, binding.lblResp)
            MyAsyncTask().execute(url)
            if (MinhaRua.cep == null) {
                if (checaerro == true) {
                    binding.tilCep.error = binding.tilCep.helperText
                    Toast.makeText(
                        this,
                        "Cep inv??lido ou Imcompleto!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    // Classe Interna para Verfica????o Assincrona em BackGround de CEP via API
    inner class MyAsyncTask : AsyncTask<String, String, String>() {
        override fun onPreExecute() {
        }

        override fun doInBackground(vararg params: String?): String {
            try {
                val url = URL(params[0])
                val urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 7000
                var instring = ConvertStreamString(urlConnection.inputStream)
                publishProgress(instring)
            } catch (ex: Exception) {
                Log.d("Erro: ", ex.toString())
            }
            return ""
        }

        // Cria Progressbar enquando faz consulta do CEP
        override fun onProgressUpdate(vararg params: String?) {
            try {
                var json = JSONObject(params[0])
                val cep = json.getString("cep")
                val logradouro = parteFrase(json.getString("logradouro"), "L")
                val endereco = parteFrase(json.getString("logradouro"), "E")
                val bairro = json.getString("bairro")
                val cidade = json.getString("localidade")
                val estado = json.getString("uf")
                val complemento = json.getString("complemento")
                anime.tradeView(binding.pgCep, binding.lblResp)
                MinhaRua.cep = CEP(cep, logradouro, endereco, bairro, cidade, estado, complemento)
                salvarCep(cep)
            } catch (ex: Exception) {
                Log.d("Erro: ", ex.toString())
            }
        }
    }

    // Funcoes para SharedPreferences

    fun salvarCep(texto: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("cep_key", texto)
        editor.apply()
    }
    fun carregaCep(): String {
        return sharedPreferences.getString("cep_key", "") ?: ""
    }
}
