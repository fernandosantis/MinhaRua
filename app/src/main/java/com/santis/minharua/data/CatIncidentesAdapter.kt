package com.santis.minharua.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.santis.minharua.R
import com.santis.minharua.data.model.CategoriaIncidentes
import com.santis.minharua.util.Image

class CatIncidentesAdapter(val incidenteList: List<CategoriaIncidentes>) : RecyclerView.Adapter<CatIncidentesAdapter.IncidenteViewHolder>() {

    var listenerShare: (View) -> Unit = {}

    inner class IncidenteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val layIndicente = view.findViewById<ConstraintLayout>(R.id.lay_incidente)

        // Cabeçalho
        val layCabecalho = view.findViewById<ConstraintLayout>(R.id.lay_cabecalho)
        val imgIcone = view.findViewById<ImageView>(R.id.img_icone)
        val lblTitulo = view.findViewById<TextView>(R.id.lbl_titulo)
        val lblCategoria = view.findViewById<TextView>(R.id.lbl_categoria)
        val lblResumo = view.findViewById<TextView>(R.id.lbl_resumo)
        val cmdExpandir = view.findViewById<ImageView>(R.id.cmd_expandir)

        // CardView
        val cvCartao = view.findViewById<CardView>(R.id.cv_cartao)
        val imgImagem = view.findViewById<ImageView>(R.id.img_imagem)
        val lblDescricao = view.findViewById<TextView>(R.id.lbl_resumo)
        val cmdEditar = view.findViewById<ImageView>(R.id.cmd_editar)
        val cmdApagar = view.findViewById<ImageView>(R.id.cmd_apagar)
        val cmdCompartilhar = view.findViewById<ImageView>(R.id.cmd_compartilhar)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IncidenteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_incidente, parent, false)
        return IncidenteViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncidenteViewHolder, position: Int) {
        with(holder) {
            // Cabeçalho
            layCabecalho.visibility = View.VISIBLE
            layCabecalho.setBackgroundResource(incidenteList[position].categoria.corCat)
            imgIcone.setImageResource(incidenteList[position].categoria.iconeCat)
            lblTitulo.text = incidenteList[position].incidentes[0].tituloInc
            lblCategoria.text = incidenteList[position].categoria.nomeCat
            lblResumo.text = incidenteList[position].incidentes[0].descricaoInc
            lblResumo.visibility = View.VISIBLE
            cmdExpandir.setImageResource(R.drawable.ic_down)
            cmdExpandir.tag = "off"

            // CardView
            cvCartao.visibility = View.GONE
            imgImagem.setImageResource(incidenteList[position].incidentes[0].imagemInc)
            lblDescricao.text = incidenteList[position].incidentes[0].descricaoInc

            // Botão Expandir
            cmdExpandir.setOnClickListener(
                View.OnClickListener {
                    if (cmdExpandir.tag == "off") {
                        // lblResumo.visibility = View.INVISIBLE
                        lblResumo.animate().alpha(0.0f)
                        cvCartao.visibility = View.VISIBLE
                        cmdExpandir.setImageResource(R.drawable.ic_up)
                        cmdExpandir.tag = "on"
                    } else {
                        // lblResumo.visibility = View.VISIBLE
                        lblResumo.animate().alpha(1.0f)
                        cvCartao.visibility = View.GONE
                        cmdExpandir.setImageResource(R.drawable.ic_down)
                        cmdExpandir.tag = "off"
                    }
                }
            )

            // Botão Compartilhar
            cmdCompartilhar.setOnClickListener {
                listenerShare = { incidente ->
                    Image.share(it.context, incidente)
                }

                listenerShare(layIndicente)
            }
        }
    }

    override fun getItemCount(): Int {
        return incidenteList.size
    }
}
