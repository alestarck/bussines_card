package com.example.bussinesscard.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HelperDB(
    context: Context?,
): SQLiteOpenHelper(context, NOME_BANCO, null,versionAtual) {
    companion object{
        private val NOME_BANCO= "businesscartbase.db"
        private val versionAtual= 2
    }
    val table_name="businesscard"
    val COLUMN_ID="id"
    val COLUMN_NOME="nome"
    val COLUMN_EMPRESA="empresa"
    val COLUNN_TELEFONE="telefone"
    val COLUMN_EMAIL="email"
    val COLUMN_FUNDO="fundoPersonalisado"
    val drop_table="DROP TABLE IF EXISTS $table_name"
    val CREATE_TABLE by lazy {
        "CREATE TABLE $table_name (" +
            "$COLUMN_ID INTEGER NOT NULL," +
            "$COLUMN_NOME TEXT NOT NULL," +
            "$COLUMN_EMPRESA TEXT NOT NULL," +
            "$COLUNN_TELEFONE TEXT NOT NULL," +
            "$COLUMN_EMAIL TEXT NOT NULL," +
            "$COLUMN_FUNDO TEXT NOT NULL," +
            "" +
            "PRIMARY KEY($COLUMN_ID AUTOINCREMENT)" +
            ")"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion!=newVersion){
            db?.execSQL(drop_table)
        }
        onCreate(db)
    }
    // INSERT ----------------------------------------------------------------------------------------
    public fun salvarCard(card: BusinessCard){
        val db : SQLiteDatabase= writableDatabase?: return
        val sql="INSERT INTO $table_name ($COLUMN_NOME,$COLUMN_EMPRESA,$COLUNN_TELEFONE,$COLUMN_EMAIL,$COLUMN_FUNDO) VALUES(?,?,?,?,?)"
        var array: Array<String> = arrayOf(card.nome,card.empresa,card.telefone,card.email,card.fundoPersonalisado)
        db.execSQL(sql,array)
        db.execSQL(sql)
        db.close()
    }
    // DELETE
    fun apagarCard(card: BusinessCard){
        val db : SQLiteDatabase = writableDatabase?: return
        val sql = "DELETE FROM $table_name WHERE $COLUMN_NOME LIKE ${card.nome}"
        db.execSQL(sql)
        db.close()
    }
    //================================================================PART 2 inicialização de busta SELECT

    fun buscarCard(/*busca: String, isBuscaPorID: Boolean = false*/) : List<BusinessCard> {
        val db = readableDatabase ?: return mutableListOf()
        var lista = mutableListOf<BusinessCard>()
        var where: String? = null
        var args: Array<String> = arrayOf()
        /*if(isBuscaPorID){
            where = "$COLUMN_ID = ?"
            args = arrayOf("$busca")
        }else{
            where = "$COLUMN_NOME LIKE ?"
            args = arrayOf("%$busca%")
        }*/
        var cursor = db.query(table_name,null,where,args,null,null,null)
        if (cursor == null){
            db.close()
            return mutableListOf()
        }
        while (cursor.moveToNext()){
            var card= BusinessCard(
                nome=cursor.getString(cursor.getColumnIndex(COLUMN_NOME)),
                empresa=cursor.getString(cursor.getColumnIndex(COLUMN_EMPRESA)),
                telefone = cursor.getString(cursor.getColumnIndex(COLUNN_TELEFONE)),
                email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)),
                fundoPersonalisado = cursor.getString(cursor.getColumnIndex(COLUMN_FUNDO))
            )
            lista.add(card)
        }
        db.close()
        return lista
    }
    /*fun buscaCard(/*busca: String*/): List<BusinessCard>{
        salvarCard(BusinessCard("asdf","asdfasdf","asfdasdf","asdfasf","asdfas"))
        val db: SQLiteDatabase= readableDatabase?: return mutableListOf()
        var lista= mutableListOf<BusinessCard>()
        val sql= "SELECT * FROM $table_name"
        var cursor: Cursor = db.rawQuery(sql, null)
        if (cursor==null){
            db.close()
            return mutableListOf()
        }
        while (cursor.moveToNext()){
            var card= BusinessCard(
            nome=cursor.getString(cursor.getColumnIndex(COLUMN_NOME)),
            empresa=cursor.getString(cursor.getColumnIndex(COLUMN_EMPRESA)),
            telefone = cursor.getString(cursor.getColumnIndex(COLUNN_TELEFONE)),
            email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)),
            fundoPersonalisado = cursor.getString(cursor.getColumnIndex(COLUMN_FUNDO))
            )
            lista.add(card)
        }
        db.close()
        return lista
    }*/



}