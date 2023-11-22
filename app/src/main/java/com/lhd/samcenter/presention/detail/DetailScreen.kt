package com.lhd.samcenter.presention.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lhd.samcenter.common.widget.MyAppTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            MyAppTopAppBar(topAppBarText = "Screen", navHostController)
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Detail Screen")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val navHostController = rememberNavController()
    DetailScreen(navHostController)
}