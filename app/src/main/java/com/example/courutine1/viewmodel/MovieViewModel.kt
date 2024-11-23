package com.example.courutine1.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courutine1.data.Movie
import com.example.courutine1.data.MovieDao
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class MovieViewModel(private val movieDao: MovieDao) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            Log.d("MovieViewModel", "Starting data loading...")
            _isLoading.value = true
            delay(2000)
            _movies.value = movieDao.getAllMovies()
            _isLoading.value = false
            Log.d("MovieViewModel", "Data loading complete!")
        }
    }

    fun addMovie(title: String, rating: Float, description: String) {
        viewModelScope.launch {
            Log.d("MovieViewModel", "Adding movie to database...")
            movieDao.insertMovie(Movie(title = title, rating = rating, description = description))
            Log.d("MovieViewModel", "Movie added successfully!")
            loadMovies()
        }
    }
}
