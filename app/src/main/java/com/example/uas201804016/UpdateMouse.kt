package com.example.uas201804016

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class UpdateMouse : AppCompatActivity() {
    lateinit var mouseDBHelper: DBHelper
    lateinit var inputcode: EditText
    lateinit var inputbrand: EditText
    lateinit var inputname: EditText
    lateinit var inputprice: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_mouse)
        inputcode = findViewById(R.id.input_codeu)
        inputbrand = findViewById(R.id.input_brandu)
        inputname = findViewById(R.id.input_nameu)
        inputprice = findViewById(R.id.input_priceu)
        mouseDBHelper = DBHelper(this)
        val bundle = intent.extras
        if (bundle!=null){
            inputcode.setText(bundle.getString("codek"))
            inputbrand.setText(bundle.getString("brandk"))
            inputname.setText(bundle.getString("namek"))
            inputprice.setText(bundle.getString("pricek"))

        }
    }
    fun updateData(v: View){
        var codein = inputcode.text.toString()
        var brandin = inputbrand.text.toString()
        var namein = inputname.text.toString()
        var pricein = inputprice.text.toString()
        mouseDBHelper.updateMouse(codein, brandin, namein, pricein)
        var pindah = Intent(this, RvMouseData::class.java)
        startActivity(pindah)
    }
    fun cancelData(v: View){
        var pindah = Intent(this, RvMouseData::class.java)
        startActivity(pindah)
    }
}