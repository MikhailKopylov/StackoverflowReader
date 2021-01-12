package com.amk.stackoverflowreader.mvp.model

class UserRepository {

    private val userList = listOf<User>(
        User("user1"),
        User("user2"),
        User("user3"),
        User("user4"),
        User("user5"),
        User("user6"),
    )

    fun getUsers() = userList
}