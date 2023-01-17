package com.f00tballsmart.sp0rtfun.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class TeamEntity(
    @PrimaryKey val name : String,
    val stadium : String,
    val city : String,
    val strength: Int,
    val win: Int,
    val lose: Int,
    val draw: Int,
    val point: Int,
)
