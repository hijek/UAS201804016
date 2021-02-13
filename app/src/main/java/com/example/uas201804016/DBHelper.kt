package com.example.uas201804016

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlin.jvm.Throws

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        val DATABASE_NAME = "myaps.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_USER = "CREATE TABLE " + DBInfo.UserTable.TABLE_NAME + "("+DBInfo.UserTable.COL_EMAIL+" VARCHAR(200) PRIMARY KEY, " + DBInfo.UserTable.COL_PASS + " TEXT, " + DBInfo.UserTable.COL_FULLNAME + " TEXT, " + DBInfo.UserTable.COL_JENKAL + " VARCHAR(200), " + DBInfo.UserTable.COL_ALAMAT + " TEXT)"
        private val SQL_CREATE_MOUSE = "CREATE TABLE " + DBInfo.MouseTable.TABLE_NAME + "("+DBInfo.MouseTable.COL_CODE+" VARCHAR(200) PRIMARY KEY, " + DBInfo.MouseTable.COL_BRAND + " TEXT, " + DBInfo.MouseTable.COL_NAME + " TEXT, " + DBInfo.MouseTable.COL_PRICE + " TEXT)"
        private val SQL_CREATE_KEYBOARD = "CREATE TABLE " + DBInfo.KeyboardTable.TABLE_NAME + "("+DBInfo.KeyboardTable.COL_CODE+" VARCHAR(200) PRIMARY KEY, " + DBInfo.KeyboardTable.COL_BRAND + " TEXT, " + DBInfo.KeyboardTable.COL_NAME + " TEXT, " + DBInfo.KeyboardTable.COL_PRICE + " TEXT)"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBInfo.UserTable.TABLE_NAME
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_USER)
        db?.execSQL(SQL_CREATE_MOUSE)
        db?.execSQL(SQL_CREATE_KEYBOARD)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    @Throws(SQLiteConstraintException::class)

    //insert data
    fun RegisterUser(emailin: String, passin:String, fullnamein: String, jenkalin: String, alamatin: String) {
        val db = writableDatabase
        val namatable = DBInfo.UserTable.TABLE_NAME
        val emailt = DBInfo.UserTable.COL_EMAIL
        val passt = DBInfo.UserTable.COL_PASS
        val fullnamet = DBInfo.UserTable.COL_FULLNAME
        val jenkalt = DBInfo.UserTable.COL_JENKAL
        val alamatt = DBInfo.UserTable.COL_ALAMAT
        var sql = "INSERT INTO " + namatable + " (" + emailt + ", " + passt + ", " + fullnamet + ", " + jenkalt + ", " + alamatt + ") VALUES('" + emailin + "', '" + passin + "', '" + fullnamein + "', '" + jenkalin + "', '" + alamatin + "')"
        db.execSQL(sql)
    }
    fun InsertMouse(codein: String,brandin: String, namein:String, pricein: String) {
        val db = writableDatabase
        val tablename = DBInfo.MouseTable.TABLE_NAME
        val codet = DBInfo.MouseTable.COL_CODE
        val brandt = DBInfo.MouseTable.COL_BRAND
        val namet = DBInfo.MouseTable.COL_NAME
        val pricet = DBInfo.MouseTable.COL_PRICE
        var sql = "INSERT INTO " + tablename + " (" + codet + ", " + brandt + ", " + namet + ", " + pricet + ") VALUES('" + codein + "', '" + brandin +"', '" + namein + "', '" + pricein + "')"
        db.execSQL(sql)
    }
    fun InsertKeyboard(codein: String, brandin: String, namein:String, pricein: String) {
        val db = writableDatabase
        val tablename = DBInfo.KeyboardTable.TABLE_NAME
        val codet = DBInfo.KeyboardTable.COL_CODE
        val brandt = DBInfo.KeyboardTable.COL_BRAND
        val namet = DBInfo.KeyboardTable.COL_NAME
        val pricet = DBInfo.KeyboardTable.COL_PRICE
        var sql = "INSERT INTO " + tablename + " (" + codet + ", " + brandt + ", " + namet + ", " + pricet + ") VALUES('" + codein + "', '" + brandin + "', '" + namein + "', '" + pricein + "')"
        db.execSQL(sql)
    }
    fun cekUser(emailin: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+ DBInfo.UserTable.COL_EMAIL +") as jumlah FROM "+ DBInfo.UserTable.TABLE_NAME + " WHERE " + DBInfo.UserTable.COL_EMAIL + "=='" + emailin +"'" , null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }
        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_JUMLAH))
        }
        return jumlah
    }
    fun cekLogin(emailin: String, passin: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+ DBInfo.UserTable.COL_EMAIL +") as jumlah FROM "+ DBInfo.UserTable.TABLE_NAME + " WHERE " + DBInfo.UserTable.COL_EMAIL + "=='" + emailin +"' AND " + DBInfo.UserTable.COL_PASS + "=='" + passin + "'" , null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }
        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_JUMLAH))
        }
        return jumlah
    }

    //tampil data
    fun fullDataUser():ArrayList<DataModelUser> {
        val users = arrayListOf<DataModelUser>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM "+DBInfo.UserTable.TABLE_NAME, null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return ArrayList()
        }
        var emailt: String
        var passt: String
        var fullnamet: String
        var alamatt: String
        var jkt: String
        if (cursor!!.moveToFirst()){
            while (cursor.isAfterLast==false){
                emailt = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_EMAIL))
                passt = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_PASS))
                fullnamet = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_FULLNAME))
                alamatt = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_ALAMAT))
                jkt = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_JENKAL))

                users.add(DataModelUser(emailt, passt, fullnamet, alamatt, jkt))
                cursor.moveToNext()
            }
        }
        return  users
    }
    fun fullDataMouse():ArrayList<DataModelMouse> {
        val mouse = arrayListOf<DataModelMouse>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM "+DBInfo.MouseTable.TABLE_NAME, null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_MOUSE)
            return ArrayList()
        }
        var codet: String
        var brandt: String
        var namet: String
        var pricet: String
        if (cursor!!.moveToFirst()){
            while (cursor.isAfterLast==false){
                codet = cursor.getString(cursor.getColumnIndex(DBInfo.MouseTable.COL_CODE))
                brandt = cursor.getString(cursor.getColumnIndex(DBInfo.MouseTable.COL_BRAND))
                namet = cursor.getString(cursor.getColumnIndex(DBInfo.MouseTable.COL_NAME))
                pricet = cursor.getString(cursor.getColumnIndex(DBInfo.MouseTable.COL_PRICE))

                mouse.add(DataModelMouse(codet, brandt, namet, pricet))
                cursor.moveToNext()
            }
        }
        return  mouse
    }
    fun fullDataKeyboard():ArrayList<DataModelKeyboard> {
        val keyboard = arrayListOf<DataModelKeyboard>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM "+DBInfo.KeyboardTable.TABLE_NAME, null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_KEYBOARD)
            return ArrayList()
        }
        var codet: String
        var brandt: String
        var namet: String
        var pricet: String
        if (cursor!!.moveToFirst()){
            while (cursor.isAfterLast==false){
                codet = cursor.getString(cursor.getColumnIndex(DBInfo.KeyboardTable.COL_CODE))
                brandt = cursor.getString(cursor.getColumnIndex(DBInfo.KeyboardTable.COL_BRAND))
                namet = cursor.getString(cursor.getColumnIndex(DBInfo.KeyboardTable.COL_NAME))
                pricet = cursor.getString(cursor.getColumnIndex(DBInfo.KeyboardTable.COL_PRICE))

                keyboard.add( DataModelKeyboard(codet, brandt, namet, pricet))
                cursor.moveToNext()
            }
        }
        return  keyboard
    }

    //delete data
    fun deleteUser(emailin: String){
        val db = writableDatabase
        val namatablet = DBInfo.UserTable.TABLE_NAME
        val emailt = DBInfo.UserTable.COL_EMAIL
        val sql = "DELETE FROM " +namatablet+ " WHERE "+emailt+"='"+emailin+"'"
        db.execSQL(sql)
    }
    fun deleteMouse(codein: String){
        val db = writableDatabase
        val tablenamet = DBInfo.MouseTable.TABLE_NAME
        val codet = DBInfo.MouseTable.COL_CODE
        val sql = "DELETE FROM " +tablenamet+ " WHERE "+codet+"='"+codein+"'"
        db.execSQL(sql)
    }
    fun deleteKeyboard(codein: String){
        val db = writableDatabase
        val tablenamet = DBInfo.KeyboardTable.TABLE_NAME
        val codet = DBInfo.KeyboardTable.COL_CODE
        val sql = "DELETE FROM " +tablenamet+ " WHERE "+codet+"='"+codein+"'"
        db.execSQL(sql)
    }

    //update data
    fun updateUser(emailin: String, passin:String, fullnamein: String, jenkalin: String, alamatin: String){
        val db = writableDatabase
        val namatablet = DBInfo.UserTable.TABLE_NAME
        val emailt = DBInfo.UserTable.COL_EMAIL
        val passt = DBInfo.UserTable.COL_PASS
        val namet = DBInfo.UserTable.COL_FULLNAME
        val alamatt = DBInfo.UserTable.COL_ALAMAT
        val jkt = DBInfo.UserTable.COL_JENKAL
        var sql = "UPDATE "+ namatablet + " SET "+
                passt+"='"+passin+"', "+namet+"='"+fullnamein+"', "+alamatt+"='"+jenkalin+"', "+jkt+"='"+alamatin+"' "+
                "WHERE "+emailt+"='"+emailin+"'"
        db.execSQL(sql)
    }
    fun updateMouse(codein: String,brandin: String, namein:String, pricein: String){
        val db = writableDatabase
        val namatablet = DBInfo.MouseTable.TABLE_NAME
        val brandt = DBInfo.MouseTable.COL_BRAND
        val codet = DBInfo.MouseTable.COL_CODE
        val namet = DBInfo.MouseTable.COL_NAME
        val pricet = DBInfo.MouseTable.COL_PRICE
        var sql = "UPDATE "+ namatablet + " SET "+
                brandt+"='"+brandin+"', "+namet+"='"+namein+"', "+pricet+"='"+pricein+"' "+
                "WHERE "+codet+"='"+codein+"'"
        db.execSQL(sql)
    }
    fun updateKeyboard(codein: String, brandin:String, namein:String, pricein: String){
        val db = writableDatabase
        val tablenamet = DBInfo.KeyboardTable.TABLE_NAME
        val codet = DBInfo.KeyboardTable.COL_CODE
        val brandt = DBInfo.KeyboardTable.COL_BRAND
        val namet = DBInfo.KeyboardTable.COL_NAME
        val pricet = DBInfo.KeyboardTable.COL_PRICE
        var sql = "UPDATE "+ tablenamet + " SET "+
                brandt+"='"+brandin+"', "+namet+"='"+namein+"', "+pricet+"='"+pricein+"' "+
                "WHERE "+codet+"='"+codein+"'"
        db.execSQL(sql)
    }
}