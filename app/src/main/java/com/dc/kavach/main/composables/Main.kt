package com.dc.kavach.main.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dc.kavach.model.BannedApp
import com.dc.kavach.model.BannedApps

@Composable
fun Main(data: BannedApps) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = data.toString())
    }
}

@Preview
@Composable
fun Demo() {
    Main(data = BannedApps(listOf(BannedApp("kfjkhfshf" ,"f;f;sfsf"))) )
}