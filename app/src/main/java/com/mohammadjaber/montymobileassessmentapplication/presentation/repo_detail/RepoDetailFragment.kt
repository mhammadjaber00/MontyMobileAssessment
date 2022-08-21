package com.mohammadjaber.montymobileassessmentapplication.presentation.repo_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.mohammadjaber.montymobileassessmentapplication.databinding.FragmentRepoDetailBinding
import com.mohammadjaber.montymobileassessmentapplication.databinding.FragmentRepoDetailBinding.inflate

class RepoDetailFragment : Fragment() {

    private val args: RepoDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentRepoDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.model = args.repo
    }
}