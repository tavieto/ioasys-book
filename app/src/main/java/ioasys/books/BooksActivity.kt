package ioasys.books

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import ioasys.books.BooksViewAction.Get
import ioasys.books.ui.theme.IoasysBooksTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

class BooksActivity : ComponentActivity() {

    private lateinit var viewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            IoasysBooksTheme {
                val systemUi = rememberSystemUiController()
                viewModel = getViewModel()

                systemUi.setStatusBarColor(Color.Transparent)
                systemUi.setNavigationBarColor(Color.Transparent)
                systemUi.systemBarsDarkContentEnabled = resources.configuration.uiMode == UI_MODE_NIGHT_YES

                ProvideWindowInsets {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        if (this::viewModel.isInitialized) {
                            Content(
                                viewState = viewModel.viewState,
                                action = viewModel::dispatchAction
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Content(
    viewState: BooksViewState,
    action: (BooksViewAction) -> Unit
) {
    val lazyState = rememberLazyListState()

    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Books",
            fontSize = 32.sp,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.height(24.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyState
        ) {
            items(
                items = viewState.books,
                key = { it.id }
            ) {
                BookItem(
                    id = it.id,
                    imageUrl = it.imageUrl,
                    authors = it.authors,
                    title = it.title,
                    category = it.category
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        action(Get.Books)
    }

    LaunchedEffect(viewState.books) {
        if (viewState.books.isNotEmpty()) lazyState.scrollToItem(0)
    }
}

@Composable
private fun BookItem(
    id: String,
    imageUrl: String,
    title: String,
    authors: List<String>,
    category: String
) {
    var authorsText by remember {
        mutableStateOf(String())
    }

    BoxWithConstraints {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 24.dp)
        ) {
            GlideImage(
                modifier = Modifier
                    .height(122.dp)
                    .width(81.dp)
                    .clip(RoundedCornerShape(8.dp)),
                imageModel = imageUrl,
                circularReveal = CircularReveal()
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.height(122.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = authorsText,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic
                )
                Spacer(modifier = Modifier.weight(weight = 1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "ID: $id",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = category,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

    LaunchedEffect(Unit) {
        authors.forEachIndexed { index, s ->
            authorsText += if (index != authors.lastIndex) "$s, " else s
        }
    }
}
