package com.mohammadjaber.montymobileassessmentapplication.presentation.repoList.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.filter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mohammadjaber.montymobileassessmentapplication.R
import com.mohammadjaber.montymobileassessmentapplication.presentation.paging_common.RepoLoadStateAdapter
import com.mohammadjaber.montymobileassessmentapplication.databinding.FragmentRepoListBinding
import com.mohammadjaber.montymobileassessmentapplication.domain.model.Repo
import com.mohammadjaber.montymobileassessmentapplication.presentation.repoList.adapter.ReposAdapter
import com.mohammadjaber.montymobileassessmentapplication.presentation.repoList.viewmodel.RepoListViewModel
import com.mohammadjaber.montymobileassessmentapplication.common.CustomClickListener
import com.mohammadjaber.montymobileassessmentapplication.common.RepoComparator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepoListFragment : Fragment(), CustomClickListener {

    private lateinit var binding: FragmentRepoListBinding
    private lateinit var listener: CustomClickListener
    private lateinit var menuHost: MenuHost
    private lateinit var recyclerView: RecyclerView
    private lateinit var pagingAdapter: ReposAdapter
    private lateinit var loadStateAdapter: RepoLoadStateAdapter
    private lateinit var concatAdapter: ConcatAdapter
    private val viewModel by viewModels<RepoListViewModel>()
    private var query: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoListBinding.inflate(inflater, container, false)
        listener = this
        menuHost = requireActivity()

        setupMenu()
        viewModel.queryLiveData.observe(viewLifecycleOwner) {
            query = it
            pagingAdapter.refresh()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerView = binding.rvRepo
        pagingAdapter = ReposAdapter(listener, RepoComparator)
        loadStateAdapter = RepoLoadStateAdapter(pagingAdapter::retry)
        concatAdapter = pagingAdapter.withLoadStateFooter(loadStateAdapter)
        recyclerView.adapter = concatAdapter

        val itemDecor = DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecor)

        pagingAdapter.addLoadStateListener { loadStates ->
            binding.swipeRefreshLayout.isRefreshing = loadStates.refresh is LoadState.Loading
            when {
                loadStates.append is LoadState.Error -> loadStateAdapter.loadState = loadStates.append
                loadStates.refresh is LoadState.Error -> loadStateAdapter.loadState = loadStates.refresh
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            pagingAdapter.refresh()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getRepos().map { data ->
                data.filter { it.name.startsWith(query, ignoreCase = true) }
            }.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }

    private fun setupMenu() {
        menuHost.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.app_bar_menu, menu)
            }


            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_search -> {
                        handleSearchView(menuItem)
                        true
                    }
                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun handleSearchView(menuItem: MenuItem) {
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.queryLiveData.value = query
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    viewModel.queryLiveData.value = query
                    return true
                }
            })

            onActionViewCollapsed().apply {
                pagingAdapter.refresh()
            }
        }
    }

    override fun onClick(item: Repo) {
        val action = RepoListFragmentDirections.actionRepoListFragmentToRepoDetailFragment(item)
        findNavController().navigate(action)
    }
}