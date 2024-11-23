package com.example.courutine1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.courutine1.data.MovieDatabase
import com.example.courutine1.ui.theme.MovieScreen
import com.example.courutine1.viewmodel.MovieViewModel
import com.example.courutine1.viewmodel.MovieViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = MovieDatabase.getDatabase(this)

        setContent {
            val viewModel: MovieViewModel = ViewModelProvider(
                this,
                MovieViewModelFactory(database.movieDao())
            )[MovieViewModel::class.java]

            MovieScreen(viewModel = viewModel)
        }
    }
}
