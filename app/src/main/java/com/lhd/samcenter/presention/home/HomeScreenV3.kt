
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.lhd.samcenter.common.widget.MyAppTopAppBar
import com.lhd.samcenter.presention.home.HomeViewModel
import com.lhd.samcenter.service.MyWorkManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenV3(
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
            val context = LocalContext.current

            val data = Data.Builder()
            val lifecycleOwner = LocalLifecycleOwner.current
            val workerManager = WorkManager.getInstance(context)
            data.apply {
                putString("image_url", "https://s2.q4cdn.com/175719177/files/doc_presentations/Placeholder-PDF.pdf")
            }
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            Button(onClick = {
                val imageDownloadRequest = OneTimeWorkRequestBuilder<MyWorkManager>()
                    .setConstraints(constraints)
                    .setInputData(data.build())
                    .build()

                workerManager.enqueue(imageDownloadRequest)
                workerManager.getWorkInfoByIdLiveData(imageDownloadRequest.id)
                    .observe(lifecycleOwner) {
                        it?.let {
                            when (it.state) {
                                WorkInfo.State.SUCCEEDED -> {
                                    Toast.makeText(context, "SUCCEEDED", Toast.LENGTH_SHORT).show()
                                }

                                WorkInfo.State.FAILED -> {
                                    Toast.makeText(context, "FAILED", Toast.LENGTH_SHORT).show()

                                }

                                WorkInfo.State.RUNNING -> {
                                    Toast.makeText(context, "RUNNING", Toast.LENGTH_SHORT).show()

                                }

                                WorkInfo.State.ENQUEUED -> {
                                    Toast.makeText(context, "ENQUEUED", Toast.LENGTH_SHORT).show()

                                }

                                else -> {
                                    Toast.makeText(
                                        context,
                                        "Some thing went wrong!",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                            }
                        }
                    }

            }) {
                Text(text = "Start Service")
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
