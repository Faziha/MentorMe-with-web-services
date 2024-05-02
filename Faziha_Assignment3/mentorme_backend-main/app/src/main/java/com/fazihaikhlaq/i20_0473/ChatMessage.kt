package com.fazihaikhlaq.i20_0473

data class ChatMessage(
    val id: String, // Unique identifier for the message
    val message: String, // The text of the message
    val timestamp: Long, // The timestamp of when the message was sent
    val senderId: String, // The sender's identifier
    val isSent: Boolean // True if the message was sent by the user, false if received
)
