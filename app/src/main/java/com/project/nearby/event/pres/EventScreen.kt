package com.project.nearby.event.pres

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.project.nearby.R
import com.project.nearby.common.graphics.LoadingScreen
import com.project.nearby.common.graphics.WhiteText
import com.project.nearby.retrofit.models.concert.AttractionX
import com.project.nearby.retrofit.models.concert.Dates
import com.project.nearby.retrofit.models.concert.ImageXX
import com.project.nearby.retrofit.models.concert.Venue
import com.project.nearby.ui.theme.Colors

@Composable
fun EventScreen(
    id : String,
    viewModel: EventScreenViewModel = hiltViewModel(),
) {
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(id) {
        viewModel.loadEvent(id)
    }

    if (viewModel.loading.value){
        LoadingScreen()
    } else {
        viewModel.event.value?.let {event ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(Colors.Black)
        ){
                //photo
            item {
                HorizontalScroller(
                    images = event.images,
                    text = viewModel.event.value?.name ?: "not loaded",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
            item {
                //dates
                DateContainer(event.dates)
            }
            item {
                //location
                LocationContainer(
                    uriHandler,
                    event._embedded.venues
                )
            }
            item {
                //info
                if (!event.info.isNullOrBlank()) {
                    TextContainer(text = event.info)
                }else {
                    if (!event.promoter.description.isNullOrBlank()){
                        TextContainer(text = event.promoter.description)
                    }
                }
            }
            item {
                //buy tickets url
                TicketsURLContainer(
                    uriHandler,
                    event.url
                )
            }
            item {
                //social networks
                Networks(
                    uriHandler,
                    event._links.attractions,
                    Modifier.heightIn(50.dp, 200.dp)
                )
            }
            item {

            }
            }
        }
    }
}

@Composable
fun Networks(
    uriHandler: UriHandler,
    attractions: List<AttractionX>,
    modifier: Modifier = Modifier
){
    val itemList = attractions.map { it.href }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        WhiteText(text = stringResource(R.string.learn_more_about_event))
        LazyColumn(){
                items(itemList){link ->
                    LinkButton(
                        uriHandler = uriHandler,
                        uri = link
                    ) {
                            WhiteText(text = stringResource(R.string.learn))
                    }
                }
            }
        }
    }

@Composable
fun TicketsURLContainer(
    uriHandler: UriHandler,
    url: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        WhiteText(text = stringResource(id = R.string.order_tickets))
        LinkButton(
            uriHandler = uriHandler,
            uri = url
        ) {
            WhiteText(text = stringResource(R.string.buy))
        }
    }
}


@Composable
fun TextContainer(
    text : String = ""
){
    LazyColumn(
        modifier = Modifier
            .heightIn(50.dp, 200.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Colors.Blue, RoundedCornerShape(4.dp))
            .border(1.dp, Colors.White, RectangleShape)
    ){
        item{
            WhiteText(
                text = text,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun DateContainer(dates: Dates) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        WhiteText(
            text = dates.start.localDate ?: stringResource(R.string.question),
            modifier = Modifier
                .background(Colors.Blue, RoundedCornerShape(4.dp)
            )
        )
        WhiteText(
            text = (dates.start.localTime ?: "").substring(0, (dates.start.localTime?.length ?: 3) - 3),
            modifier = Modifier
                .background(Colors.Blue, RoundedCornerShape(4.dp))
        )
    }
}

@Composable
fun HorizontalScroller(
    images: List<ImageXX>,
    text : String,
    modifier : Modifier = Modifier
){
    val imageUrls = images.map { it.url }
    val pagerState = rememberPagerState(pageCount = { imageUrls.size })

    Box {
        HorizontalPager(
            state = pagerState,
            modifier = modifier

        ) { page ->
            Image(
                painter = rememberAsyncImagePainter(imageUrls[page]),
                contentDescription = "Image $page",
                modifier = Modifier
                    .border(1.dp, Colors.White, RectangleShape)
                    .clip(RoundedCornerShape(4.dp))
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        PagerState(
            currentPage = 2,
            pageCount = {imageUrls.size}
        )

        if (text != ""){
            Column(
                modifier = modifier
                    .padding(4.dp)
            ){
                Spacer(modifier = Modifier.height(160.dp))
                WhiteText(
                    text,
                    fontSize = 20.sp,
                    modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun LocationContainer(
    uriHandler: UriHandler,
    venues: List<Venue>,
    modifier : Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .heightIn(min = 50.dp, max = 200.dp)
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        WhiteText(stringResource(R.string.places))
        LazyColumn{
            items(venues){venue ->
                LinkButton(
                    uriHandler = uriHandler,
                    uri = venue.url,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    ){
                        WhiteText(text = venue.city.name)
                        Text(
                            text = venue.address.line1,
                            fontSize = 12.sp,
                            color = Colors.White,
                            maxLines = 1,
                            overflow = TextOverflow.Clip
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun LinkButton(
    uriHandler: UriHandler,
    uri : String = "",
    content : @Composable () -> Unit
){
    Button(
        colors = ButtonDefaults.buttonColors(Colors.Blue, Colors.White),
        onClick = { if (uri != null && uri != ""){
            uriHandler.openUri(uri)
            }
        },
        contentPadding = PaddingValues(0.dp),
    ) {
        content()
    }
}