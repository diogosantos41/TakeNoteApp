package com.dscoding.takenoteapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Label(
    val labelName: String,
    @PrimaryKey val id: Int? = null
)
