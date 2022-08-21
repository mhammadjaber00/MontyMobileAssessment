package com.mohammadjaber.montymobileassessmentapplication.domain.model

import java.io.Serializable

data class Repo(
    var name: String,
    val createdAt: String,
    val avatarUrl: String,
    val stargazersCount: Int
) : Serializable
