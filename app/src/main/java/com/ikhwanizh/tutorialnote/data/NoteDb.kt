package com.ikhwanizh.tutorialnote.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDb: RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile private var instance : NoteDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDb::class.java,
            "noteDatabase.db"
        ).build()
    }
}