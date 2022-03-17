package com.dc.kavach.main.composables

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.dc.kavach.model.BannedApp

@Composable
fun FilteredAppList(filteredAppList: MutableList<BannedApp>) {
    Card(filteredAppList)
}

@Composable
private fun Card(filteredAppList: MutableList<BannedApp>) {
    val context = LocalContext.current

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (app in filteredAppList) {

            //Log.d("TAG33", (context.packageManager.getApplicationInfo("$index", index.toInt())).toString())

            androidx.compose.material.Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                border = BorderStroke(1.dp, Color.Gray),
                elevation = 8.dp
            ) {
                Row( modifier = Modifier.fillMaxWidth() ,
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
                                .padding(8.dp),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold

                        )
                        Text(
                            text = app.packageName,
                            modifier = Modifier
                                .padding(8.dp),
                            fontSize = TextUnit.Unspecified,
                            fontWeight = FontWeight.Light
                        )

                    }

                    IconButton(modifier = Modifier.padding(8.dp).size(40.dp),
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

private fun intentCall(i: String, context: Context) {
    val intent = Intent(Intent.ACTION_DELETE)
    intent.setData(Uri.parse("package:$i"))
    startActivity(context, intent, null)

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    //FilteredAppList(mutableListOf("fsdff", "ffdfsdf", "fdsfs", "fdfsff"))
}
