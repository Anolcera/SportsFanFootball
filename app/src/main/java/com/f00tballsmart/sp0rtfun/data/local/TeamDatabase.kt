package com.f00tballsmart.sp0rtfun.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TeamEntity::class],
    version = 1
)
abstract class TeamDatabase : RoomDatabase() {
    abstract val dao : TeamsDao
}