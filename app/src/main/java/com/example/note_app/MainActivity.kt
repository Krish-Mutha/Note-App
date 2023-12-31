package com.example.note_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.note_app.data.NotesDataSource
import com.example.note_app.model.Note
import com.example.note_app.screen.NoteScreen
import com.example.note_app.screen.NoteViewModel
import com.example.note_app.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.sql.DataSource
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        val noteViewModel = viewModel<NoteViewModel>()
                       // val noteViewModel:NoteViewModel by viewModels()
                        NotesApp(noteViewModel)
                    }
                }
            }
        }
    }


@Composable
fun NotesApp(noteViewModel: NoteViewModel){
    val notesList = noteViewModel.noteList.collectAsState().value
    NoteScreen(notes = notesList,
        onAddNote = {
            noteViewModel.addNote(it)
        },
        onRemoveNote = {
            noteViewModel.removeNote(it)
        })

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteAppTheme {

    }
}