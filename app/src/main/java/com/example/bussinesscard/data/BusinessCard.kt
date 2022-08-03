package com.example.bussinesscard.data
import androidx.room.Entity

@Entity
data class BusinessCard(
    val id: Int = -1,
    val nome: String,
    val empresa: String,
    val telefone: String,
    val email: String,
    val fundoPersonalisado: String)
