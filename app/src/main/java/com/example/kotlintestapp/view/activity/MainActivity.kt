package com.example.kotlintestapp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kotlintestapp.R
import com.example.kotlintestapp.model.Producer
import com.example.kotlintestapp.model.ProducersRepository
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val repository: ProducersRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO Firestoreのセキュリティルール設定（認証処理追加すればいけるか？）
        lifecycleScope.launch {
            // ①現在のプロデューサー一覧を取得する
            println("①現在のプロデューサー一覧を取得する ▶ " + repository.getProducers(100))

            // ②新しいプロデューサーを追加する
            val newProducer = Producer.create("プロデューサー2", null)
            repository.add(newProducer)
            println("①新しいプロデューサーを追加する ▶ " + repository.getProducers(100))

            // ③新しく追加したプロデューサーを削除する
            repository.delete(newProducer)
            println("③新しく追加したプロデューサーを削除する ▶ " + repository.getProducers(100))
        }
    }
}
