package com.example.pomodoro

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.StringBuilder

class DataBaseOpenHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val sb = StringBuilder()
        sb.append("CREATE TABLE $TABLE_NAME ")
        sb.append("($COLUMN_ID INTEGER PRIMARY KEY, ")
        sb.append("$COLUMN_NAME TEXT NOT NULL, ")
        sb.append("$COLUMN_DONE INTEGER DEFAULT 0, ")
        sb.append("$COLUMN_DATE INTEGER DEFAULT (datetime(CURRENT_TIMESTAMP,'localtime')))")
        p0?.execSQL(sb.toString())
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }
}