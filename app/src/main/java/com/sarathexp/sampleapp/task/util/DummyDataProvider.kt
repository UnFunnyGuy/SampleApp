package com.sarathexp.sampleapp.task.util

import com.sarathexp.sampleapp.task.data.model.User

object DummyDataProvider {
    private val data: List<User> =
        listOf(
            User(1, "user1@example.com", "John", "Doe", null),
            User(2, "user2@example.com", "Jane", "Smith", null),
            User(3, "user3@example.com", "Michael", "Johnson", null),
            User(4, "user4@example.com", "Emily", "Williams", null),
            User(5, "user5@example.com", "David", "Brown", null),
            User(6, "user6@example.com", "Sarah", "Jones", null),
            User(7, "user7@example.com", "Christopher", "Garcia", null),
            User(8, "user8@example.com", "Jessica", "Miller", null),
            User(9, "user9@example.com", "Matthew", "Davis", null),
            User(10, "user10@example.com", "Amanda", "Rodriguez", null),
            User(11, "user11@example.com", "Daniel", "Martinez", null),
            User(12, "user12@example.com", "Melissa", "Hernandez", null),
            User(13, "user13@example.com", "James", "Lopez", null),
            User(14, "user14@example.com", "Ashley", "Gonzalez", null),
            User(15, "user15@example.com", "Robert", "Wilson", null),
            User(16, "user16@example.com", "Jennifer", "Anderson", null),
            User(17, "user17@example.com", "William", "Thomas", null),
            User(18, "user18@example.com", "Stephanie", "Taylor", null),
            User(19, "user19@example.com", "Joseph", "Moore", null),
            User(20, "user20@example.com", "Nicole", "Jackson", null),
            User(21, "user21@example.com", "Samuel", "Martin", null),
            User(22, "user22@example.com", "Olivia", "Lee", null),
            User(23, "user23@example.com", "Daniel", "White", null),
            User(24, "user24@example.com", "Emma", "Clark", null),
            User(25, "user25@example.com", "Ethan", "Lewis", null)
        )

    fun getUsers(page: Int, perPage: Int = 6): List<User> {
        val start = (page - 1) * perPage
        val end = start + perPage
        return data.subList(start, end.coerceAtMost(data.size))
    }
}
