package com.example.mobilenews

import android.app.Application
import android.content.Context
import android.os.Bundle


class NewsApp : Application(){


    override fun onCreate() {
        super.onCreate()
    }


    init {
        instance = this
    }

    /**
     * When the NewsApp class is initialized, the instance variable is assigned a reference to the current instance of the class.
     * The applicationContext() function can then be called from any part of the code to retrieve the application context.
     */
    companion object {
        private var instance: NewsApp? = null

        /**
         *
         */
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}



