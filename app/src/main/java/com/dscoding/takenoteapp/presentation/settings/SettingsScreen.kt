package com.dscoding.takenoteapp.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.presentation.settings.components.SwitchField

@Composable
fun SettingsScreen(navController: NavController) {

    val generalMargin = dimensionResource(R.dimen.general_margin)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Settings")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, "Back Arrow")
                    }
                },
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(
                        generalMargin,
                        generalMargin,
                        generalMargin,
                        generalMargin
                    )
            ) {
                SwitchField("Show Greeting", true, onSelect = { })
                SwitchField("Name", false, onSelect = { })
                SwitchField("Dark theme", false, onSelect = { })
                SwitchField("Notes on Grid Layout", false, onSelect = { })

            }
        })
}