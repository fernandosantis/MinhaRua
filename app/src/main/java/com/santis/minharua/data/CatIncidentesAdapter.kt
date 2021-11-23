package com.santis.minharua.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.santis.minharua.MinhaRua
import com.santis.minharua.R
import com.santis.minharua.data.model.CategoriaIncidentes
import com.santis.minharua.util.Image

class CatIncidentesAdapter(val incidenteList: List<CategoriaIncidentes>) : RecyclerView.Adapter<CatIncidentesAdapter.IncidenteViewHolder>() {

    var listenerShare: (View) -> Unit = {}

    inner class IncidenteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // Cabeçalho
        val imgCatCor = view.findViewById<ImageView>(R.id.img_cat_cor)
        val imgCatIcon = view.findViewById<ImageView>(R.id.img_cat_icon)
        val imgIcone = view.findViewById<ImageView>(R.id.img_icone)
        val lblTitulo = view.findViewById<TextView>(R.id.lbl_titulo)
        val lblResumo = view.findViewById<TextView>(R.id.lbl_resumo)
        val cmdExpandir = view.findViewById<ImageView>(R.id.cmd_expandir)

        // CardView
        val cvCartao = view.findViewById<CardView>(R.id.cv_cartao)
        val imgImagem = view.findViewById<ImageView>(R.id.img_imagem)
        val lblDescricao = view.findViewById<TextView>(R.id.lbl_descricao)
        val lblEndereco = view.findViewById<TextView>(R.id.lbl_endereco)
        val lblBairro = view.findViewById<TextView>(R.id.lbl_bairro)
        val lblCategoria = view.findViewById<TextView>(R.id.lbl_categoria)

        // Menu        
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
            imgCatCor.setColorFilter(ContextCompat.getColor(imgCatCor.context, incidenteList[position].categoria.corCat), android.graphics.PorterDuff.Mode.SRC_IN);
            imgCatIcon.setImageResource(incidenteList[position].categoria.iconeCat)
            imgIcone.setImageResource(incidenteList[position].incidentes[0].imagemInc)
            lblTitulo.text = incidenteList[position].incidentes[0].tituloInc
            lblResumo.text = incidenteList[position].incidentes[0].descricaoInc
            lblResumo.visibility = View.VISIBLE

            cmdExpandir.setImageResource(R.drawable.ic_down)
            cmdExpandir.tag = "off"

            // CardView
            cvCartao.visibility = View.GONE
            imgImagem.setImageResource(incidenteList[position].incidentes[0].imagemInc)
            lblDescricao.text = incidenteList[position].incidentes[0].descricaoInc
            lblEndereco.text = "${MinhaRua.cep?.logradouro} ${MinhaRua.cep?.endereco}"
            lblBairro.text = MinhaRua.cep?.bairro

            lblCategoria.text = incidenteList[position].categoria.nomeCat

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

                listenerShare(cvCartao)
            }
        }
    }

    override fun getItemCount(): Int {
        return incidenteList.size
    }
}
