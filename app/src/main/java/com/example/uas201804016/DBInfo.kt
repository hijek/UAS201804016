package com.example.uas201804016
import android.provider.BaseColumns
object DBInfo {
    class UserTable: BaseColumns {
        companion object {
            val TABLE_NAME = "user"
            val COL_EMAIL = "email"
            val COL_PASS = "pass"
            val COL_FULLNAME = "fullname"
            val COL_ALAMAT = "alamat"
            val COL_JENKAL = "jeniskelamin"
            val COL_JUMLAH = "jumlah"
        }
    }

    class MouseTable: BaseColumns {
        companion object {
            val TABLE_NAME = "mouse"
            val COL_CODE = "code"
            val COL_BRAND = "brand"
            val COL_NAME = "name"
            val COL_PRICE = "price"
        }
    }

    class KeyboardTable: BaseColumns {
        companion object {
            val TABLE_NAME = "keyboard"
            val COL_CODE = "code"
            val COL_BRAND = "brand"
            val COL_NAME = "name"
            val COL_PRICE = "price"
        }
    }
}