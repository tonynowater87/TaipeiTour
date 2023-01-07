package com.tonynowater.cathaytest.feature.attraction_detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tonynowater.cathaytest.databinding.FragmentAttractionDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AttractionDetailFragment : Fragment() {
    private var _binding: FragmentAttractionDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<AttractionDetailViewModel>()
    private val args: AttractionDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAttractionDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is AttractionDetailUiState.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is AttractionDetailUiState.Success -> {
                    binding.progressBar.isVisible = false
                    binding.model = it.data
                    binding.textViewWebUrl.setOnClickListener { view ->
                        findNavController().navigate(
                            AttractionDetailFragmentDirections.toWebView(
                                title = it.data.name,
                                webUrl = it.data.url
                            )
                        )
                    }
                }
                is AttractionDetailUiState.Error -> {
                    binding.progressBar.isVisible = false
                    MaterialAlertDialogBuilder(requireActivity())
                        .setTitle(android.R.string.dialog_alert_title)
                        .setMessage(it.errorMessage)
                        .setPositiveButton(resources.getString(android.R.string.ok), null)
                        .show()
                }
            }
        }

        viewModel.fetchAttractionDetail(id = args.id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}