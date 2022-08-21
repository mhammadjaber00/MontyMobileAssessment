package com.mohammadjaber.montymobileassessmentapplication.common

import androidx.recyclerview.widget.DiffUtil
import com.mohammadjaber.montymobileassessmentapplication.domain.model.Repo

object RepoComparator: DiffUtil.ItemCallback<Repo>() {
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem == newItem
    }
}