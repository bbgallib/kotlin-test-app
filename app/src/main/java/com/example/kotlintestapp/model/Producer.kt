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

fun Map<String, Any>.toProducer(): Producer {
    val id = this["id"] as String
    val name = this["name"] as String
    val iconURL = this["iconURL"] as String?
    val createdAt = this["createdAt"] as String
    return Producer(id, name, iconURL, createdAt)
}
