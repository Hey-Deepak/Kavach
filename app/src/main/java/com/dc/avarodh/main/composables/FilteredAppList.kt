package com.dc.avarodh.main.composables

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dc.avarodh.ui.theme.AvarodhTheme

@Composable
fun FilteredAppList(filteredAppList: MutableList<String>) {
    Log.d("TAG6", "FilteredList" + filteredAppList.toString())
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in filteredAppList){
        Text(text =  "$i",
            modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FilteredAppList(mutableListOf("fsdff", "ffdfsdf", "fdsfs", "fdfsff"))
}
