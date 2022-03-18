package com.dc.kavach.main.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dc.kavach.R
import kotlinx.coroutines.Delay

@Composable
fun Loading() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_tank_app_icon), contentDescription = "",
            modifier = Modifier.defaultMinSize(32.dp, 32.dp))

        Text(text = "Loading...",
        modifier = Modifier.wrapContentSize(Alignment.Center),
        fontWeight = FontWeight.SemiBold,
            fontSize = 32.sp
        )
    }

}