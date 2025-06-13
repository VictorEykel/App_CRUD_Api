package com.example.app_crud_api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter(val contexto: Context, val itens: ArrayList<UserModel>, private val listener: OnItemClickListener):
    RecyclerView.Adapter<UserAdapter.viewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class viewHolder(view: View): RecyclerView.ViewHolder(view){
        val ivFoto = view.findViewById<ImageView>(R.id.ivFoto)
        val tvNome = view.findViewById<TextView>(R.id.tvNome)
        val tvCidade = view.findViewById<TextView>(R.id.tvCidade)
        val tvPais = view.findViewById<TextView>(R.id.tvPais)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder{
        val view = LayoutInflater.from(contexto).inflate(R.layout.lista_pessoa, parent, false)

        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return itens.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.tvNome.text = itens[position].name
        holder.tvCidade.text = itens[position].endereco_city
        holder.tvPais.text = itens[position].endereco_pais

        Glide
            .with(contexto)
            .load(itens[position].foto)
            .into(holder.ivFoto);

        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }
    }
}