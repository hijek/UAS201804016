package com.example.uas201804016

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvMouseDataAdapter(private val listData: ArrayList<DataModelMouse>): RecyclerView.Adapter<RvMouseDataAdapter.CardViewHolder>() {
    inner class CardViewHolder(itemV: View): RecyclerView.ViewHolder(itemV) {
        var tvcode: TextView = itemV.findViewById(R.id.tv_code)
        var tvbrand: TextView = itemV.findViewById(R.id.tv_brand)
        var tvname: TextView = itemV.findViewById(R.id.tv_name)
        var tvprice: TextView = itemV.findViewById(R.id.tv_price)
        var btndelete: Button = itemV.findViewById(R.id.btn_deletecard)
        var btnupdate: Button = itemV.findViewById(R.id.btn_updatecard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_datamouse_item, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val data = listData[position]
        holder.tvcode.text = data.code
        holder.tvbrand.text = data.brand
        holder.tvname.text = data.name
        holder.tvprice.text = data.price

        holder.btndelete.setOnClickListener {
            var adapterDBHelper: DBHelper
            adapterDBHelper = DBHelper(holder.itemView.context)
            adapterDBHelper.deleteMouse(data.code)
            listData.removeAt(position)
            notifyDataSetChanged()
        }

        holder.btnupdate.setOnClickListener {
            val pindahUpdAc = Intent(holder.itemView.context, UpdateMouse::class.java)
            val bundle = Bundle()
            bundle.putString("codek", data.code)
            bundle.putString("brandk", data.brand)
            bundle.putString("namek", data.name)
            bundle.putString("pricek", data.price)
            pindahUpdAc.putExtras(bundle)
            holder.itemView.context.startActivity(pindahUpdAc)
        }

    }

    override fun getItemCount(): Int {
        return listData.size
    }
}