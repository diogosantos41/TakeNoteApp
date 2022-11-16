package com.dscoding.takenoteapp.common

sealed interface Failure {
    object EmptyNoteTitle : Failure
    object EmptyNoteContent : Failure
}