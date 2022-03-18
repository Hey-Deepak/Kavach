package com.dc.kavach

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dc.kavach.main.MainScreen
import com.dc.kavach.main.MainUiState
import com.dc.kavach.main.MainViewModel
import com.dc.kavach.ui.theme.KavachTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var string: String

    @Inject
    lateinit var pm: PackageManager

    private lateinit var loginLauncher: ActivityResultLauncher<Intent>
    private val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KavachTheme {
                Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                    Scaffold(
                        topBar = {
                            TopAppBar(title = { Text(text = "Kavach") })
                        }
                    ) {
                        //Navigation()
                        MainScreen(loginLauncher, viewmodel)
                    }
                }
            }
        }

        setupLoginLauncher()
        getLoggedInEmailFromPrefs()
        //viewmodel.pushList()

    }

    private fun setupLoginLauncher() {
        loginLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                user?.let {
                    val email = it.email!!
                    viewmodel.uiState.value = MainUiState.LoggedIn(email)
                    saveEmailToPrefs(email)
                    viewmodel.fetchList()
                    Toast.makeText(
                        this,
                        "Congratulation! You have logged in as $email",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun getLoggedInEmailFromPrefs() {
        val prefs = getSharedPreferences("main", MODE_PRIVATE)
        val email = prefs.getString("emailId", null)
        email?.let {
            viewmodel.uiState.value = MainUiState.LoggedIn(it)
            viewmodel.fetchList()
        }
    }

    private fun saveEmailToPrefs(email: String) {
        val prefs = getSharedPreferences("main", MODE_PRIVATE)
        prefs.edit().putString("emailId", email).apply()
    }


    override fun onResume() {
        super.onResume()
        if (viewmodel.uiState.value is MainUiState.FilteredAppList) {
            Log.d("TAG11", "Hi")
            val localAppList = viewmodel.getLocalappList()
            viewmodel.filterList(localAppList)
        }
    }
}


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }

        // Main Screen
        composable("main_screen") {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Main Screen", color = Color.Black, fontSize = 24.sp)
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    // Animation
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            // tween Animation
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        // Customize the delay time
        delay(3000L)
        navController.navigate("main_screen")
    }

    // Image
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // Change the logo
        Image(
            painter = painterResource(id = R.drawable.ic_tank_app_icon),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KavachTheme {

    }
}




