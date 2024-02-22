package com.mmbethub.armexbet.theme3.model.local
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "user_table")
data class User (
        @PrimaryKey var id: UUID = UUID.randomUUID(),
        var name:String = "",
        var email:String = "",
        var date: String = "")