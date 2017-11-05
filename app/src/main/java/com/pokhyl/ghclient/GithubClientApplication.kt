package com.pokhyl.ghclient

import android.app.Application
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory


class GithubClientApplication : Application(), KodeinAware {

    override val kodein = Kodein {
        bind<Retrofit>() with singleton {
            Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object Factory {
        lateinit var instance: GithubClientApplication
    }

}