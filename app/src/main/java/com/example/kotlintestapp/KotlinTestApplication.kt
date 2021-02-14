package com.example.kotlintestapp

import android.app.Application
import com.example.kotlintestapp.model.ProducersRepository
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KotlinTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KotlinTestApplication)
            modules(appModule)
        }
    }

    private val appModule
        get() = module {
            single { FirebaseFirestore.getInstance() }
            single { ProducersRepository(get()) }
        }
}
