package com.lhd.samcenter.common.widget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.lhd.samcenter.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppTopAppBar(
    topAppBarText: String,
    navHostController: NavHostController,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    TopAppBar(
        title = {
            Text(
                text = topAppBarText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navHostController.popBackStack()
            }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "description",
                    tint = colorResource(id = R.color.purple_700)
                )
            }
        },
        actions = {
            IconButton(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        "Snackbar show"
                    )
                }
            }) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "description",
                    tint = colorResource(id = R.color.purple_700)
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Green),
    )
}