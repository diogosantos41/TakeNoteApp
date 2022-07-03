package com.dscoding.takenoteapp.utils

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import com.dscoding.takenoteapp.R
import com.google.android.play.core.review.ReviewManagerFactory


fun Context.launchReviewFlow() {
    val reviewManager = ReviewManagerFactory.create(this)
    val requestReviewFlow = reviewManager.requestReviewFlow()
    requestReviewFlow.addOnCompleteListener { request ->
        if (request.isSuccessful) {
            val reviewInfo = request.result
            val flow = reviewManager.launchReviewFlow(this as ComponentActivity, reviewInfo)
            flow.addOnCompleteListener {

            }
        } else {

        }
    }
}


fun Context.launchShareAppIntent() {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, getString(R.string.settings_share_app_intent_message))
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}