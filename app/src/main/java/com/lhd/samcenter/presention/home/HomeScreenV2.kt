import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lhd.samcenter.common.widget.MyAppTopAppBar
import com.lhd.samcenter.presention.home.HomeViewModel
import com.lhd.samcenter.service.MyService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenV2(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            MyAppTopAppBar(topAppBarText = "HomeV2", navHostController)
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "ASDASDAS")
            var isServiceRunning = false
            val context = LocalContext.current
            val intent = Intent(context, MyService::class.java)
            Button(onClick = {
                ContextCompat.startForegroundService(
                    context,
                    intent
                )
                isServiceRunning = true
            }) {
                Text(text = "Start Service")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                if (isServiceRunning) {
                    context.stopService(intent)
                }
            }) {
                Text(text = "End Service")
            }
        }
    }
}

fun checkAndRequestCameraPermission(
    context: Context,
    permission: String,
    launcher: ManagedActivityResultLauncher<String, Boolean>
) {
    val permissionCheckResult = ContextCompat.checkSelfPermission(context, permission)
    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
        // Open camera because permission is already granted
    } else {
        // Request a permission
        launcher.launch(permission)
    }
}

//@Preview
//@Composable
//fun HomeScreenV2() {
//    MaterialTheme {
//        HomeScreenV2()
//    }
//}