package com.example.kotlintestapp.model

import java.time.LocalDateTime
import java.util.UUID


data class Producer(
    val id: String,
    val name: String,
    val iconURL: String?,
    val createdAt: String,
) {
    companion object {
        fun create(name: String, iconURL: String?): Producer =
            Producer(UUID.randomUUID().toString(), name, iconURL, LocalDateTime.now().toString())
    }
}

fun Producer.toMap(): Map<String, *> {
    return hashMapOf(
        "id" to this.id,
        "name" to this.name,
        "iconURL" to this.iconURL,
        "createdAt" to this.createdAt
    )
}
