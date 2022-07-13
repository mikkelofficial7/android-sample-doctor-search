package com.android.mobile.alteacaretest.room

import android.content.Context
import androidx.room.Room

object RoomBuilder {
    private var roomDatabase: RoomDb? = null
    private var roomName = "room-database"

    fun init(context: Context) : RoomDb? {
        roomDatabase = Room
            .databaseBuilder(context, RoomDb::class.java, roomName)
            .allowMainThreadQueries()
            .build()

        return roomDatabase
    }
}