package com.sarathexp.sampleapp.task.domain.model

import androidx.annotation.Keep

@Keep data class LoginRequest(val userName: String, val password: String)
