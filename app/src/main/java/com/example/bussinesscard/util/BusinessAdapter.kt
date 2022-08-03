package com.example.bussinesscard.util

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bussinesscard.R
import com.example.bussinesscard.data.BusinessCard
import com.example.bussinesscard.ui.AddBusinessCard
import kotlinx.android.synthetic.main.item_business_card.view.*


class BusinessAdapter(
    private val context: Context,
    private val lista: List<BusinessCard>,
    private val onClick: ((Int) -> Unit)
) : RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_business_card, parent, false)
        return BusinessViewHolder(view)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) {
        val businesscard = lista[position]
        with(holder.itemView) {
            nome_ale.text = businesscard.nome
            empresa.text = businesscard.empresa
            telefone.text = businesscard.telefone
            email.text = businesscard.email
            clicavel.setOnClickListener {
                val intent = Intent(context, AddBusinessCard::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }

        }
    }

    class BusinessViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}