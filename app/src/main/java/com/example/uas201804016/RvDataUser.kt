package com.example.uas201804016

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RvDataUser : AppCompatActivity() {
    private lateinit var rv_tampilanku: RecyclerView
    lateinit var userDBHelper: DBHelper
    private  var list: ArrayList<DataModelUser> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rv_userdata)
        rv_tampilanku = findViewById(R.id.rv_views)
        rv_tampilanku.setHasFixedSize(true)
        userDBHelper = DBHelper(this)
        list.addAll(userDBHelper.fullDataUser())
        rv_tampilanku.layoutManager = LinearLayoutManager(this)
        var cardData = RvDataUserAdapter(list)
        rv_tampilanku.adapter = cardData

        val txtDashboard: TextView = findViewById(R.id.txtdashboard)
        txtDashboard.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}