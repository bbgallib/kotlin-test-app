package com.example.kotlintestapp.model

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class ProducersRepository {
    private val db: FirebaseFirestore get() = FirebaseFirestore.getInstance()

    fun add(producer: Producer) {
        val data = producer.toMap()
        db.collection(COLLECTION_PATH)
            .document(producer.id)
            .set(data)
            .addOnSuccessListener {
                Log.i(TAG, "Success adding producer.")
            }
            .addOnFailureListener { ex ->
                Log.w(TAG, "Failure adding producer.", ex)
            }
    }

    fun delete(producer: Producer) {
        db.collection(COLLECTION_PATH)
            .document(producer.id)
            .delete()
            .addOnSuccessListener {
                Log.i(TAG, "Success deleting producer.")
            }
            .addOnFailureListener { ex ->
                Log.w(TAG, "Failure deleting producer.", ex)
            }
    }

    fun getProducers(limit: Long): List<Producer>? {
        return db.collection(COLLECTION_PATH)
            .limit(limit)
            .get().result?.toObjects(Producer::class.java)
    }

    companion object {
        private const val COLLECTION_PATH = "Producers"
    }
}
