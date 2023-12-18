package com.example.note_app.data

import androidx.compose.runtime.MutableState
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.note_app.model.Note
import kotlinx.coroutines.flow.Flow

//learn about coroutines(suspendable functions) and Flow
@Dao
interface NoteDatabaseDao {
    @Query("SELECT * from notes_tbl")
    fun getNotes():Flow<List<Note>>

    @Query("SELECT * FROM notes_tbl where id =:id")
    suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note:Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note:Note)

    @Query("DELETE from notes_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note:Note)

}
