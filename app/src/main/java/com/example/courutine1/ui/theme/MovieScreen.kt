package com.example.courutine1.ui.theme
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.courutine1.data.Movie
import com.example.courutine1.viewmodel.MovieViewModel
@Composable
fun MovieScreen(viewModel: MovieViewModel) {
    val movies = viewModel.movies.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val showDialog = remember { mutableStateOf(false) }

    if (isLoading) {
        Log.d("MovieScreen", "Loading movies...")
        LoadingScreen()
    } else {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    Log.d("MovieScreen", "Add movie dialog opened")
                    showDialog.value = true
                }) {
                    Text("+")
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { padding ->
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(movies) { movie ->
                    Log.d("MovieScreen", "Displaying movie: ${movie.title}")
                    MovieItem(movie)
                }
            }

            if (showDialog.value) {
                AddMovieDialog(
                    onDismiss = { showDialog.value = false },
                    onAddMovie = { title, rating, description ->
                        viewModel.addMovie(title, rating, description)
                    }
                )
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = movie.title, style = MaterialTheme.typography.titleLarge)
            Text(text = "Рейтинг: ${movie.rating}", style = MaterialTheme.typography.bodyMedium)
            Text(text = movie.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
