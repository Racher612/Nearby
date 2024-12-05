package com.project.nearby.common.graphics

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.nearby.ui.theme.Colors

@Composable
fun WhiteText(
    text : String,
    fontSize : TextUnit = 16.sp,
    modifier : Modifier = Modifier
){
    Text(
        text,
        color = Colors.White,
        fontSize = fontSize,
        modifier = modifier.padding(4.dp)
    )
}