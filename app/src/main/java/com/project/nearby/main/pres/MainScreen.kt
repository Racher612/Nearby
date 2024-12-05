package com.project.nearby.main.pres

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.nearby.R
import com.project.nearby.common.graphics.LoadingScreen
import com.project.nearby.common.graphics.WhiteText
import com.project.nearby.ui.theme.Colors
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    navigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    PopUpWindow(
        viewModel,
    )
    Scaffold(
        topBar = { Search(onClick = { viewModel.openWindow() }) }
    ) {padding ->
        if (viewModel.loading.value){
            LoadingScreen()
        } else {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(padding)
                    .background(Colors.Black)
            ) {
                item {
                    viewModel.concerts.value?._embedded?.events?.forEach{event ->
                        EventCard(
                            event = event,
                            navigateToRoute = navigateToRoute,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Search(
    onClick: () -> Unit
){
    var query: String by rememberSaveable { mutableStateOf("") }
    Row(
        modifier = Modifier
            .background(Colors.Black)
    ) {
        TextField(
            value = query,
            onValueChange = { onQueryChanged ->
                query = onQueryChanged
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    tint = Colors.White,
                    contentDescription = stringResource(R.string.search_icon)
                )
            },
            trailingIcon = {
                IconButton(onClick = { onClick() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter),
                        tint = Colors.White,
                        contentDescription = stringResource(R.string.clear_icon)
                    )
                }
            },
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
            placeholder = { Text(text = stringResource(R.string.nav_id)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .background(color = Colors.Black, shape = RoundedCornerShape(8.dp))
                .border(1.dp, Colors.White, RoundedCornerShape(8.dp))
        )
    }
}

@Composable
fun PopUpWindow(
    viewModel: MainScreenViewModel,
//    load : () -> Unit
){
    val scope = rememberCoroutineScope()

    if (viewModel.popUp.value){
        Dialog(onDismissRequest = viewModel::openWindow) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .heightIn(200.dp, 250.dp)
                    .padding(4.dp)
                    .border(2.dp, Colors.White, RoundedCornerShape(8.dp))
                    .background(Colors.Black, RoundedCornerShape(8.dp))
            ) {
                TextField(
                    value = viewModel.searchRadius.value,
                    maxLines = 1,
                    onValueChange = viewModel::readRadius,
                    placeholder = { Text(
                        text = stringResource(R.string.radius),
                        color = Colors.White
                    ) },
                    textStyle = TextStyle(Colors.White, 16.sp),
                    colors = TextFieldDefaults.textFieldColors(
                        Colors.White,
                        cursorColor = Colors.White
                    ),
                    modifier = Modifier
                        .background(Colors.Blue, RoundedCornerShape(8.dp))
                )
                Button(
                    onClick = {
                        scope.launch {
                            viewModel.loadConcertsByGeo()
                            viewModel.openWindow()
                        }
                    } ,
                    colors = ButtonDefaults.buttonColors(Colors.Blue, Colors.Grey)
                ) {
                    WhiteText(stringResource(R.string.search))
                }
            }
        }
    }
}