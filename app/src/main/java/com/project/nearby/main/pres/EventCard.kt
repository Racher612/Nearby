package com.project.nearby.main.pres

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.project.nearby.R
import com.project.nearby.navigation.domain.model.Routes
import com.project.nearby.retrofit.models.concert.Event
import com.project.nearby.retrofit.models.concert.ImageXX
import com.project.nearby.ui.theme.Colors


@Composable
fun EventCard(
    event : Event,
    navigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
){
    val imageUrls = event.images.map { it.url }
    val slash = stringResource(R.string.slash)
    Card(
        colors = CardColors(Colors.Black, Colors.White, Colors.Grey, Colors.Grey),
        modifier = modifier
            .padding(4.dp)
            .border(2.dp, Colors.White, RoundedCornerShape(4.dp))
            .clickable {
                navigateToRoute(
                    Routes.Event.route + slash + event.id
                )
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = imageUrls[0],
                contentDescription = stringResource(R.string.preview),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Text(text = event.name)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 12.dp)
            ) {
                if (!event.ticketLimit?.info.isNullOrBlank()){
                    event.ticketLimit?.info?.let { TicketContainer(text = it) }
                } else {
                    TicketContainer(text = "0")
                }
                if (!event.dates.start.localDate.isNullOrBlank())
                    DateContainer(event.dates.start.localDate)
                }
            }
        }

    }


@Composable
fun HorizontalScroller(
    images: List<ImageXX>
){
    val imageUrls = images.map { it.url }
    val pagerState = rememberPagerState(pageCount = { imageUrls.size })


    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)

    ) { page ->
        Image(
            painter = rememberAsyncImagePainter(imageUrls[page]),
            contentDescription = "Image $page",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
    }

    // Индикаторы страниц
    PagerState(
        currentPage = 2,
        pageCount = {imageUrls.size}
    )
}

@Composable
fun DateContainer(
    text : String,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        color = Colors.Black,
        maxLines = 1,
        fontSize = 20.sp,
        modifier = modifier
            .background(Colors.Orange, RoundedCornerShape(4.dp))
            .padding(4.dp)
    )
}

@Composable
fun TicketContainer(
    text: String,
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .background(Colors.Red, RoundedCornerShape(4.dp))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ticket),
            contentDescription = stringResource(R.string.tickets),
            tint = Colors.White,
            modifier = Modifier
                .padding(4.dp)
                .size(24.dp)
        )
        Text(
            text = "${text.filter { it.isDigit() }} left".substring(0, 2),
            fontSize = 20.sp,
        )
    }
}