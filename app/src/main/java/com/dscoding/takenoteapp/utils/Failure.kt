package com.dscoding.takenoteapp.utils

sealed class Failure {

    object EmptyNoteTitle : Failure()
    object EmptyNoteContent : Failure()

}