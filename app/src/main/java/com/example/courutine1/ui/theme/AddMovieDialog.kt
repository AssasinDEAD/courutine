package com.example.courutine1.ui.theme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
@Composable
fun AddMovieDialog(onDismiss: () -> Unit, onAddMovie: (String, Float, String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Добавить фильм") },
        text = {
            Column {
                OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Название") })
                OutlinedTextField(value = rating, onValueChange = { rating = it }, label = { Text("Рейтинг") })
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Описание") })
            }
        },
        confirmButton = {
            Button(onClick = {
                val parsedRating = rating.toFloatOrNull() ?: 0f
                onAddMovie(title, parsedRating, description)
                onDismiss()
            }) {
                Text("Добавить")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Отмена")
            }
        }
    )
}
