package com.dscoding.takenoteapp.utils

sealed interface Failure {
    object EmptyNoteTitle : Failure
    object EmptyNoteContent : Failure
}