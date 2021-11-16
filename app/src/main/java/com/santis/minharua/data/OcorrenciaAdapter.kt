package com.santis.minharua.data

import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.santis.minharua.R
import com.santis.minharua.model.Ocorrencia
import com.santis.minharua.util.Image

class OcorrenciaAdapter(val OcorrenciaList: ArrayList<Ocorrencia>) :
    RecyclerView.Adapter<OcorrenciaAdapter.OcorrenciaViewHolder>(),
    Filterable {

    var listenerShare: (View) -> Unit = {}

    var OcorrenciaListFilterable = ArrayList<Ocorrencia>()

    init {
        this.OcorrenciaListFilterable = OcorrenciaList
    }

    // ViewHolder
    // Suporte (Holder) para manter a referencia da View, no caso Item_Contato
    inner class OcorrenciaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val vhTitulo = view.findViewById<TextView>(R.id.lbl_titulo)
        val vhDescricao = view.findViewById<TextView>(R.id.lbl_descricao)
        val vhResumo = view.findViewById<TextView>(R.id.lbl_resumo)
        val vhCategoria = view.findViewById<TextView>(R.id.lbl_categoria)
        val vhContainer = view.findViewById<CardView>(R.id.lay_card)
        val vhFoto = view.findViewById<ImageView>(R.id.img_foto)
        val vhCor = view.findViewById<ImageView>(R.id.img_icon)
        val vhHead = view.findViewById<ConstraintLayout>(R.id.lay_head)
        val vhOcorrencia = view.findViewById<ConstraintLayout>(R.id.lay_ocorrencia)
        val vhCmdOpenClose = view.findViewById<ImageView>(R.id.cmd_open_close)
        val vhCmdCompartilhar = view.findViewById<ImageView>(R.id.cmd_share)
        val vhCmdEditar = view.findViewById<ImageView>(R.id.cmd_edit)
        val vhCmdApagar = view.findViewById<ImageView>(R.id.cmd_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OcorrenciaViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ocorrencia, parent, false)
        return OcorrenciaViewHolder(view)
    }

    override fun onBindViewHolder(holder: OcorrenciaViewHolder, position: Int) {
        val db = DatabaseHelper(holder.vhContainer.context)
        holder.vhTitulo.text = OcorrenciaListFilterable[position].titulo
        holder.vhDescricao.text = OcorrenciaListFilterable[position].descricao
        holder.vhResumo.text = OcorrenciaListFilterable[position].descricao
        holder.vhFoto.setImageURI(Uri.parse(OcorrenciaListFilterable[position].foto))
        val cat = db.categoriaById(OcorrenciaListFilterable[position].cat_id ?: -1)
        holder.vhCategoria.text = cat?.nome
        /*holder.vhCategoria.setBackgroundColor(Color.parseColor(cat?.cor.toString()))*/
        holder.vhCor.setImageURI(Uri.parse(cat?.icon))
        holder.vhHead.setBackgroundColor(Color.parseColor(cat?.cor.toString()))
        holder.vhContainer.visibility = View.GONE

        // Setar OnClickListener dos Botões
        holder.vhCmdOpenClose.setOnClickListener(
            View.OnClickListener {
                if (holder.vhContainer.visibility == View.GONE) {
                    holder.vhResumo.visibility = View.INVISIBLE
                    holder.vhContainer.visibility = View.VISIBLE
                    holder.vhCmdOpenClose.setImageResource(R.drawable.ic_up)
                } else {
                    holder.vhResumo.visibility = View.VISIBLE
                    holder.vhContainer.visibility = View.GONE
                    holder.vhCmdOpenClose.setImageResource(R.drawable.ic_down)
                }
            }
        )

        holder.vhCmdCompartilhar.setOnClickListener {
            listenerShare = { ocorrencia ->
                Image.share(it.context, ocorrencia)
            }

            listenerShare(holder.vhOcorrencia)
        }
    }

    override fun getItemCount(): Int {
        return OcorrenciaListFilterable.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty())
                    OcorrenciaListFilterable = OcorrenciaList
                else {
                    val resultList = ArrayList<Ocorrencia>()
                    for (ocorrencia in OcorrenciaList)
                        if (ocorrencia.titulo.lowercase()
                            .contains(constraint.toString().lowercase())
                        )
                            resultList.add(ocorrencia)
                    OcorrenciaListFilterable = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = OcorrenciaListFilterable
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                OcorrenciaListFilterable = results?.values as ArrayList<Ocorrencia>
                notifyDataSetChanged()
            }
        }
    }
}
