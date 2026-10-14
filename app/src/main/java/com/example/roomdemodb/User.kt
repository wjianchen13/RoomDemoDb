package com.example.roomdemodb

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 测试6
 */
@Entity
data class User(var firstName: String, var lastName: String, var age: Int) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}