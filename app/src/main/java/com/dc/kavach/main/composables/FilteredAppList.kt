package com.dc.kavach.main.composables

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.dc.kavach.R
import com.dc.kavach.model.BannedApp

@Composable
fun FilteredAppList(filteredAppList: MutableList<BannedApp>) {
    if (filteredAppList.isNotEmpty()) {
        Card(filteredAppList)
    } else EmptyListUI()
}

@Composable
fun EmptyListUI() {
    androidx.compose.material.Surface(
        modifier = Modifier.fillMaxSize(1f),
    ) {
        androidx.compose.material.Card(
            elevation = 16.dp,
            border = BorderStroke(1.dp, Color.LightGray),
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
                .padding(16.dp)
        ) {
            Column() {
                Image(
                    painter = painterResource(id = R.drawable.ic_congratulation),
                    contentDescription = "",
                    modifier = Modifier
                        .wrapContentSize(Alignment.TopCenter)
                        .padding(16.dp, 16.dp, 16.dp, 0.dp),
                    alignment = Alignment.TopCenter
                )

                Text(
                    text = "You don't have any kind of Banned Application in your device.\n" +
                            "You are Safe!\n" +
                            "Keep checking & do share this app with your Friends & Family",
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(32.dp, 0.dp, 32.dp, 32.dp),
                    fontSize = 24.sp,
                    fontFamily = FontFamily.SansSerif,

                    )
            }
        }
    }
}

@Composable
private fun Card(filteredAppList: MutableList<BannedApp>) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(1f),
        color = Color.LightGray
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Banned Apps",
                modifier = Modifier.padding(8.dp, 16.dp, 8.dp, 8.dp),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
            for (app in filteredAppList) {

                androidx.compose.material.Card(
                    modifier = Modifier
                        .padding(16.dp, 16.dp, 16.dp, 0.dp)
                        .fillMaxWidth(),
                    border = BorderStroke(1.dp, Color.LightGray),
                    elevation = 8.dp
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = app.name,
                                modifier = Modifier
                                    .padding(16.dp, 8.dp, 16.dp, 8.dp),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = app.packageName,
                                modifier = Modifier
                                    .padding(16.dp, 0.dp, 16.dp, 8.dp),
                                fontSize = TextUnit.Unspecified,
                                fontWeight = FontWeight.Light
                            )
                        }

                        IconButton(modifier = Modifier
                            .padding(8.dp)
                            .size(40.dp),
                            onClick = { intentCall(app.packageName, context = context) }) {
                            Icon(
                                Icons.Filled.Delete,
                                "contentDescription",
                                tint = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }

}

private fun intentCall(i: String, context: Context) {
    val intent = Intent(Intent.ACTION_DELETE)
    intent.setData(Uri.parse("package:$i"))
    startActivity(context, intent, null)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    Card(
        filteredAppList = mutableListOf(
            BannedApp("com.dc.kavach", "Kavach"),
            BannedApp("com.dc.kavach", "Kavach"),
            BannedApp("com.dc.kavach", "Kavach"),
            BannedApp("com.dc.kavach", "Kavach"),
            BannedApp("com.dc.kavach", "Kavach"),
            BannedApp("com.dc.kavach", "Kavach"),
            BannedApp("com.dc.kavach", "Kavach"),
            BannedApp("com.dc.kavach", "Kavach"),
            BannedApp("com.dc.kavach", "Kavach"),
            BannedApp("com.dc.kavach", "Kavach"),
            BannedApp("com.dc.kavach", "Kavach"),
            BannedApp("com.dc.kavach", "Kavach"),
            BannedApp("com.dc.kavach", "Kavach"),
            BannedApp("com.dc.kavach", "Kavach"),
            BannedApp("com.dc.kavach", "Kavach"),
            BannedApp("com.dc.kavach", "Kavach"),
            BannedApp("com.dc.kavach", "Kavach"),
        )
    )
    //EmptyListUI()
}
