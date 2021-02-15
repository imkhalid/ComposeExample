package com.example.myapplication.ui.wiget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.background500
import com.example.myapplication.ui.theme.background700

@Preview
@Composable
fun Header(text: String = "Header",onClickListner:()->Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .fillMaxWidth()
            .background(color = if (isSystemInDarkTheme()) background500 else background700)
            .height(60.dp)) {
        Image(vectorResource(id = R.drawable.ic_baseline_arrow_back_24),
                modifier = Modifier
                        .clickable(onClick = onClickListner)
                        .size(width = 50.dp, height = 50.dp)
                        .padding(10.dp))
        Text(text = text,
                modifier = Modifier.padding(start = 10.dp),
                style = TextStyle(fontSize = TextUnit.Sp(18),
                        fontWeight = FontWeight.Bold,
                color = Color.White))
    }
}