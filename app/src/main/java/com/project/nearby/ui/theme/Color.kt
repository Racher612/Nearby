package com.project.nearby.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.project.nearby.R

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

object Colors{
    val Black : Color
        @Composable
        get() = colorResource(id = R.color.black)
    val Grey : Color
        @Composable
        get() = colorResource(id = R.color.grey)
    val White : Color
        @Composable
        get() = colorResource(id = R.color.white)
    val Blue : Color
        @Composable
        get() = colorResource(id = R.color.blue)
    val DarkBlue : Color
        @Composable
        get() = colorResource(id = R.color.dark_blue)
    val Green : Color
        @Composable
        get() = colorResource(id = R.color.green)
    val DarkGreen: Color
        @Composable
        get() = colorResource(id = R.color.dark_green)
    val Red : Color
        @Composable
        get() = colorResource(id = R.color.red)
    val Orange : Color
        @Composable
        get() = colorResource(id = R.color.orange)
}