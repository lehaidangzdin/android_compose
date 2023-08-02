package com.lhd.samcenter.presention.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.lhd.samcenter.common.widget.MyAppTopAppBar
import com.lhd.samcenter.data.models.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            MyAppTopAppBar(topAppBarText = "Home", navHostController)
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (viewModel.isProgress.value) {
                CircularProgressIndicator(
                    strokeWidth = 1.dp,
                    color = Color.Red
                )
            } else {
                ContentList(viewModel.lsProduct)
            }
        }
    }
}

@Composable
fun ContentList(lsProduct: List<Product>) {
    LazyColumn {
        item {
            lsProduct.map { product ->
                RowItem(product)
            }
        }
    }
}

@Composable
fun RowItem(product: Product) {

    val model = ImageRequest.Builder(LocalContext.current)
        .data(product.image)
        .crossfade(true)
        .size(250)
        .build()
    Row(
        Modifier.padding(8.dp)
    ) {
        AsyncImage(
            model = model,
            contentDescription = product.description
        )
        Text(
            text = product.title,
            fontSize = 12.sp
        )
    }
}

@Composable
fun MyButton(title: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(title)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val navHostController = rememberNavController()
    HomeScreen(navHostController)
}