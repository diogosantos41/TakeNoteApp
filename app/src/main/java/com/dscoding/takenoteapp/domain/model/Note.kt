package com.dscoding.takenoteapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dscoding.takenoteapp.ui.theme.LightBlue
import com.dscoding.takenoteapp.ui.theme.LightGreen
import com.dscoding.takenoteapp.ui.theme.LightPink
import com.dscoding.takenoteapp.ui.theme.LightRed
import com.dscoding.takenoteapp.ui.theme.LightYellow

@Entity
data class Note(
    val title: String,
    val content: String,
    val createdTime: Long?,
    val editedTime: Long?,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(LightPink, LightRed, LightYellow, LightGreen, LightBlue)
    }
}