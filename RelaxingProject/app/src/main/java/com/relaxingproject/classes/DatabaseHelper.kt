package com.relaxingproject.classes

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.media.Rating
import android.provider.BaseColumns
import com.relaxingproject.classes.LogTable.LogEntry.TABLE_NAME

object LogTable {
    // Table contents are grouped together in an anonymous object.
    object LogEntry : BaseColumns {
        const val TABLE_NAME = "entry"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_TEXT = "text"
        const val COLUMN_NAME_DATE= "date"
        const val COLUMN_NAME_IMAGE= "image"
        const val COLUMN_NAME_RATING= "rating"

    }
}
private const val SQL_CREATE_ENTRIES = "CREATE TABLE ${LogTable.LogEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${LogTable.LogEntry.COLUMN_NAME_TITLE} TEXT," +
            "${LogTable.LogEntry.COLUMN_NAME_TEXT} TEXT," +
            "${LogTable.LogEntry.COLUMN_NAME_DATE} TEXT," +
            "${LogTable.LogEntry.COLUMN_NAME_IMAGE} BLOB," +
            "${LogTable.LogEntry.COLUMN_NAME_RATING} TEXT)"

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${LogTable.LogEntry.TABLE_NAME}"

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Relaxing.db"
    }
    //method to insert data
    fun addLog(log: Log, image:ByteArray):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(LogTable.LogEntry.COLUMN_NAME_TITLE, log.title)
        contentValues.put(LogTable.LogEntry.COLUMN_NAME_TEXT, log.text) // EmpModelClass Name
        contentValues.put(LogTable.LogEntry.COLUMN_NAME_DATE, log.date) // EmpModelClass Phone
        contentValues.put(LogTable.LogEntry.COLUMN_NAME_IMAGE, log.image)
        contentValues.put(LogTable.LogEntry.COLUMN_NAME_DATE, log.rating)
        // Inserting Row
        val success = db.insert(TABLE_NAME, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to read data
    fun viewLog():List<Log>{
        val LogList:ArrayList<Log> = ArrayList<Log>()
        val selectQuery = "SELECT  * FROM $TABLE_NAME"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var Title: String
        var Text: String
        var Date: String
        var Rating: String
        var Image:ByteArray
        if (cursor.moveToFirst()) {
            do {
                Title = cursor.getString(cursor.getColumnIndex("title"))
                Text = cursor.getString(cursor.getColumnIndex("text"))
                Date = cursor.getString(cursor.getColumnIndex("date"))
                Rating = cursor.getString(cursor.getColumnIndex("rating"))
                Image = cursor.getBlob(cursor.getColumnIndex("image"))
                val logs = Log()
                logs.title = Title
                logs.text = Text
                logs.date = Date
                logs.rating = Rating
                logs.image = Image
                LogList.add(logs)
            } while (cursor.moveToNext())
        }
        return LogList
    }
    //method to update data
    fun updateLog(log: Log):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(LogTable.LogEntry.COLUMN_NAME_TITLE, log.title)
        contentValues.put(LogTable.LogEntry.COLUMN_NAME_TEXT, log.text) // EmpModelClass Name
        contentValues.put(LogTable.LogEntry.COLUMN_NAME_DATE, log.date) // EmpModelClass Phone
        contentValues.put(LogTable.LogEntry.COLUMN_NAME_RATING, log.rating)
        // Updating Row
        val success = db.update(TABLE_NAME, contentValues,"title="+log.title,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to delete data
    fun deleteLog(log: Log):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(LogTable.LogEntry.COLUMN_NAME_TEXT, log.text) // EmpModelClass Name
        // Deleting Row
        val success = db.delete(TABLE_NAME,"id="+log.text,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
}