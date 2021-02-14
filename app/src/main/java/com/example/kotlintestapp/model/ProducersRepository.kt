package com.example.kotlintestapp.model

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ProducersRepository(
    private val db: FirebaseFirestore
) {
    suspend fun add(producer: Producer): Boolean {
        return try {
            val data = producer.toMap()
            db.collection(COLLECTION_PATH)
                .document(producer.id)
                .set(data).await()
            true
        } catch (ex: Exception) {
            Log.w(TAG, "Failed to add producer.")
            false
        }
    }

    suspend fun delete(producer: Producer): Boolean {
        return try {
            db.collection(COLLECTION_PATH)
                .document(producer.id)
                .delete().await()
            true
        } catch (ex: Exception) {
            Log.w(TAG, "Failed to delete producer.")
            false
        }
    }

    suspend fun getProducers(limit: Long): List<Producer> {
        return try {
            val dataList = db.collection(COLLECTION_PATH)
                .limit(limit)
                .get().await()
                .documents.map { it.data }
            dataList.mapNotNull { it?.toProducer() }
        } catch (ex: Exception) {
            Log.w(TAG, "Failed to get producers.")
            listOf()
        }
    }

    companion object {
        private const val COLLECTION_PATH = "Producers"
    }
}
