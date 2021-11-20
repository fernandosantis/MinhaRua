package com.santis.minharua.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.io.*

// Ocultar KeyBoard
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

/*
Pegar Logradouro e Endereco
Tipo = L ( Logradouro )
Tipo = E ( Endereço )
*/
fun parteFrase(texto: String, tipo: String): String {
    var pos = 0
    val esp: Char = " ".single()
    for (i in texto) {
        pos++
        if (i == esp) break
    }
    return when (tipo) {
        "L" -> texto.substring(0, pos)

        else -> texto.substring(pos, texto.length)
    }
}

// Leitor de API
fun ConvertStreamString(inputStream: InputStream): String {
    val reader = BufferedReader(inputStream.reader())
    val content = StringBuilder()
    var line = reader.readLine()
    reader.use { reader ->
        while (line != null) {
            content.append(line)
            line = reader.readLine()
        }
    }
    return content.toString()
}
