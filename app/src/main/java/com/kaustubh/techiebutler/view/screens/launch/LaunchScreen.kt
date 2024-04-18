package com.kaustubh.techiebutler.view.screens.launch

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kaustubh.techiebutler.domain.DataError
import com.kaustubh.techiebutler.domain.DataResult
import com.kaustubh.techiebutler.model.TypeCodeItem
import com.kaustubh.techiebutler.view.components.AppWelcomeBanner
import com.kaustubh.techiebutler.view.theme.ButlerOrange
import com.kaustubh.techiebutler.view.theme.ButlerYellowLight
import com.kaustubh.techiebutler.viewmodel.TypeCodesViewModel
import com.kaustubh.techiebutler.viewmodel.UiStateHome

@Composable
fun LaunchScreen() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppWelcomeBanner()
        AppLandingPage(Modifier.weight(1f))
    }
}

@Composable
fun AppLandingPage(
    modifier: Modifier = Modifier,
    viewModel: TypeCodesViewModel = hiltViewModel(),
) {
    val dataStatePosts by viewModel.dataStatePosts.collectAsStateWithLifecycle()
    var uiState by remember { mutableStateOf<UiStateHome?>(null) }

    HomeScreenUI(modifier, uiState, dataStatePosts as? DataResult.Success) {
        viewModel.fetchImages()
    }

    if (dataStatePosts == null) return

    LaunchedEffect(key1 = dataStatePosts) {
        uiState = when (dataStatePosts) {
            is DataResult.Loading -> {
                UiStateHome.LOADING
            }

            is DataResult.Error -> {
                UiStateHome.ERROR
            }

            is DataResult.Success -> {
                UiStateHome.DATA_LIST
            }

            else -> {
                null
            }
        }
    }
}

@Composable
fun HomeScreenUI(
    modifier: Modifier = Modifier,
    uiState: UiStateHome?,
    dataList: DataResult.Success<List<TypeCodeItem>, DataError>?,
    onBtnClicked: () -> Unit = { }
) {
    when (uiState) {
        UiStateHome.DATA_LIST -> {
            dataList?.let {
                ShowResultScreen(it)
            }
        }

        UiStateHome.ERROR -> {
            ShowErrorScreen()
        }

        UiStateHome.LOADING -> {
            ShowButtonScreen(modifier = modifier, isLoading = true)
        }

        else -> {
            ShowButtonScreen(modifier = modifier, isLoading = false) {
                onBtnClicked()
            }
        }
    }
}

@Composable
fun ShowButtonScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    onBtnClicked: () -> Unit = { }
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        if (isLoading) {
            ShowLoading()
        } else {
            ShowButton("Fetch Posts") {
                onBtnClicked()
            }
        }
    }
}

@Composable
fun ShowButton(
    btnText: String,
    modifier: Modifier = Modifier,
    onBtnClicked: () -> Unit
) {
    Button(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp, top = 32.dp),
        onClick = {
            onBtnClicked()
        },
    ) {
        Text(text = btnText, fontWeight = FontWeight.Bold, fontSize = 15.sp)
    }
}

@Composable
fun ShowLoading(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp, top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ShowResultScreen(result: DataResult.Success<List<TypeCodeItem>, DataError>?) {
    if (result == null) {
        ShowErrorScreen()
        return
    }
    ShowDataList(result.data)
}

@Composable
fun ShowErrorScreen(message: String? = "Failed to load posts!") {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        message?.let {
            Text(text = it)
        }
    }
}

@Composable
fun ShowDataList(resultList: List<TypeCodeItem>) {
//    LazyVerticalStaggeredGrid(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//            .padding(start = 8.dp, end = 8.dp),
//        columns = StaggeredGridCells.Fixed(2),
//        verticalItemSpacing = 12.dp,
//        horizontalArrangement = Arrangement.spacedBy(12.dp),
//    ) {
//        items(resultList) { task ->
//            TaskCards(task) {
//
//            }
//        }
//    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(
            items = resultList,
            key = {
                it.id ?: 0L
            }
        ) { item ->
            InfoCard(item) {

            }
        }
    }
}

@Composable
fun InfoCard(
    item: TypeCodeItem,
    onItemClicked: (TypeCodeItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 12.dp)
            .clickable {
                onItemClicked(item)
            },
        colors = CardDefaults.cardColors(
            containerColor = ButlerYellowLight
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "User ID: ${item.id}",
                    color = Color.Black,
                    maxLines = 1,
                    fontSize = 11.sp,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "${item.userId}",
                    color = Color.Black,
                    maxLines = 1,
                    fontSize = 11.sp,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(color = ButlerOrange, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp),
                )
            }
            Text(
                text = item.title ?: "",
                color = Color.Black,
                maxLines = 2,
                fontSize = 15.sp,
                modifier = Modifier.fillMaxSize(),
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
            )
            Text(
                text = item.body ?: "",
                color = Color.Black,
                maxLines = 6,
                fontSize = 13.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
