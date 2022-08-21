package com.mohammadjaber.montymobileassessmentapplication.presentation.repoList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mohammadjaber.montymobileassessmentapplication.common.CustomClickListener
import com.mohammadjaber.montymobileassessmentapplication.databinding.ViewRepoBinding
import com.mohammadjaber.montymobileassessmentapplication.domain.model.Repo

class ReposAdapter(
    private val repoClickListener: CustomClickListener,
    diffCallback: DiffUtil.ItemCallback<Repo>
 ) : PagingDataAdapter<Repo, ReposAdapter.RepoViewHolder>(diffCallback) {
 private lateinit var binding: ViewRepoBinding

 class RepoViewHolder(val binding: ViewRepoBinding) : RecyclerView.ViewHolder(binding.root) {
  fun bindModel(repo: Repo?) {
   binding.setVariable(BR.model, repo)
   binding.executePendingBindings()
  }
 }

 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
  binding = ViewRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
  return RepoViewHolder(binding)
 }

 override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
  val repo = getItem(position)
  holder.apply {
   bindModel(repo)
   binding.handler = repoClickListener
  }
 }
}