package com.dscoding.takenoteapp.data.data_source

import androidx.room.Embedded
import androidx.room.Relation
import com.dscoding.takenoteapp.domain.model.Label
import com.dscoding.takenoteapp.domain.model.Note

data class NoteAndLabelRelation(
    @Embedded val note: Note,
    @Relation(
        parentColumn = "labelName",
        entityColumn = "labelName"
    )
    val label: Label
)