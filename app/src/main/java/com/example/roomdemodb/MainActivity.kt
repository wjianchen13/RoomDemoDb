package com.example.roomdemodb

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var user1: User
    private lateinit var userDao: UserDao
    private lateinit var user2: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        userDao = AppDatabase.getDatabase(this).userDao()
        user1 = User("Tom", "Brady", 40)
        user2 = User("Tom", "Hanks", 63)
    }

    /**
     * 插入数据
     */
    fun onTest1(v: View) {
        thread {
            user1.id = userDao.insertUser(user1)
            user2.id = userDao.insertUser(user2)
        }
    }

    /**
     * 更新数据
     */
    fun onTest2(v: View) {
        thread {
            user1.age = 42
            userDao.updateUser(user1)
        }
    }

    /**
     * 删除数据
     */
    fun onTest3(v: View) {
        thread {
            userDao.deleteUserByLastName("Hanks")
        }

    }

    /**
     * 加载全部数据
     */
    fun onTest4(v: View) {
        thread {
            for (user in userDao.loadAllUsers()) {
                Log.d("MainActivity", user.toString())
            }
        }
    }

}