package com.mohammadjaber.montymobileassessmentapplication.data.remote.dto


data class Permissions(
    val admin: Boolean,
    val maintain: Boolean,
    val pull: Boolean,
    val push: Boolean,
    val triage: Boolean
)