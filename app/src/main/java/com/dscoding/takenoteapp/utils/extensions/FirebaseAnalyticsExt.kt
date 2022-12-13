package com.dscoding.takenoteapp.utils.extensions

import com.dscoding.takenoteapp.common.Constants.FIREBASE_ANALYTICS_EVENT_ADD_NOTE
import com.dscoding.takenoteapp.common.Constants.FIREBASE_ANALYTICS_EVENT_ADD_NOTE_COLOR
import com.dscoding.takenoteapp.common.Constants.FIREBASE_ANALYTICS_EVENT_DELETE_NOTE
import com.dscoding.takenoteapp.common.Constants.FIREBASE_ANALYTICS_EVENT_EDIT_NOTE
import com.dscoding.takenoteapp.common.Constants.FIREBASE_ANALYTICS_EVENT_PRIVACY_POLICY
import com.dscoding.takenoteapp.common.Constants.FIREBASE_ANALYTICS_EVENT_RATE_APP
import com.dscoding.takenoteapp.common.Constants.FIREBASE_ANALYTICS_EVENT_SWAP_THEME
import com.dscoding.takenoteapp.common.Constants.FIREBASE_ANALYTICS_EVENT_SWAP_THEME_PARAM
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent

fun FirebaseAnalytics.logAddNote(color: String) {
    this.logEvent(FIREBASE_ANALYTICS_EVENT_ADD_NOTE) {
        param(FIREBASE_ANALYTICS_EVENT_ADD_NOTE_COLOR, color)
    }
}

fun FirebaseAnalytics.logEditNote() {
    this.logEvent(FIREBASE_ANALYTICS_EVENT_EDIT_NOTE, null)
}

fun FirebaseAnalytics.logDeleteNote() {
    this.logEvent(FIREBASE_ANALYTICS_EVENT_DELETE_NOTE, null)
}

fun FirebaseAnalytics.logRateApp() {
    this.logEvent(FIREBASE_ANALYTICS_EVENT_RATE_APP, null)
}

fun FirebaseAnalytics.logShareApp() {
    this.logEvent(FirebaseAnalytics.Event.SHARE, null)
}

fun FirebaseAnalytics.logOpenPrivacyPolicyPage() {
    this.logEvent(FIREBASE_ANALYTICS_EVENT_PRIVACY_POLICY, null)
}

fun FirebaseAnalytics.logSwapTheme(theme: String) {
    this.logEvent(FIREBASE_ANALYTICS_EVENT_SWAP_THEME) {
        param(FIREBASE_ANALYTICS_EVENT_SWAP_THEME_PARAM, theme)
    }
}
