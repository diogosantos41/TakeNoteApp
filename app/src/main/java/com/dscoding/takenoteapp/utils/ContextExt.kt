package com.dscoding.takenoteapp.utils

import android.content.Context
import android.content.Intent
import com.dscoding.takenoteapp.R


fun Context.launchReviewFlow() {
    /* val manager = ReviewManagerFactory.create(this)

     val request = manager.requestReviewFlow()
     request.addOnCompleteListener { task ->
         if (task.isSuccessful) {
             // We got the ReviewInfo object
             val flow = task.result?.let { manager.launchReviewFlow(this as ComponentActivity, it) }
             flow?.addOnCompleteListener { _ ->
                 // The flow has finished. The API does not indicate whether the user
                 // reviewed or not, or even whether the review dialog was shown. Thus, no
                 // matter the result, we continue our app flow.
             }
         } else {
             // There was some problem, log or handle the error code.
         }
     } */
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