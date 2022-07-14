package com.android.mobile.alteacaretest.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RoomModule {
    private var roomName = "room-database"

    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context) : RoomDb {
        return Room
            .databaseBuilder(context, RoomDb::class.java, roomName)
            .allowMainThreadQueries()
            .build()
    }
}