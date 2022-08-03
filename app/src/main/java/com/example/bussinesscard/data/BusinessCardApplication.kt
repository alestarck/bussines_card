package com.example.bussinesscard.data

import android.app.Application

class BusinessCardApplication: Application() {
    var helperDB: HelperDB?= null
    private set

    companion object{
        lateinit var instance: BusinessCardApplication
    }
    override fun onCreate() {
        super.onCreate()
        instance= this
        helperDB= HelperDB(this)
    }
}