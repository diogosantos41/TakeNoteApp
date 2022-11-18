package com.dscoding.takenoteapp.common

object Constants {

    const val NOTE_INVALID_ID = -1
    const val NOTE_INVALID_COLOR = -1

    // Arguments
    const val NOTE_ID_ARG = "noteId"
    const val NOTE_COLOR_ARG = "noteColor"

    // Data names
    const val DATABASE_NAME = "note_db"
    const val DATASTORE_NAME = "settings_dss"

    // Animation
    const val NAVIGATION_ANIMATION_DURATION = 250
    const val NAVIGATION_SLIDE_HORIZONTAL_ANIMATION_TARGET_OFFSET = 1600
    const val COLOR_SWAP_ANIMATION_DURATION = 500

    // Firebase log events
    const val FIREBASE_ANALYTICS_EVENT_ADD_NOTE = "ADD_NOTE"
    const val FIREBASE_ANALYTICS_EVENT_ADD_NOTE_COLOR = "ADD_NOTE_COLOR_PARAM"
    const val FIREBASE_ANALYTICS_EVENT_EDIT_NOTE = "EDIT_NOTE"
    const val FIREBASE_ANALYTICS_EVENT_DELETE_NOTE = "DELETE_NOTE"
    const val FIREBASE_ANALYTICS_EVENT_RATE_APP = "RATE_APP"
    const val FIREBASE_ANALYTICS_EVENT_PRIVACY_POLICY = "PRIVACY_POLICY"
    const val FIREBASE_ANALYTICS_EVENT_SWAP_THEME = "SWAP_THEME"
    const val FIREBASE_ANALYTICS_EVENT_SWAP_THEME_PARAM = "SWAP_THEME_PARAM"

    // Pages URLs
    const val GOOGLE_PLAY_APP_URL = "https://play.google.com/store/apps/details?id=com.dscoding.takenoteapp"
    const val PRIVACY_POLICY_URL = "https://htmlpreview.github.io/?https://github.com/diogosantos41/TakeNoteApp/blob/development/privacy_policy.html"
}