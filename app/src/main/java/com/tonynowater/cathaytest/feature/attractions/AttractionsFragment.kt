package com.tonynowater.cathaytest.feature.attractions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tonynowater.cathaytest.R
import com.tonynowater.cathaytest.databinding.FragmentAttractionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AttractionsFragment : Fragment() {

    private var _binding: FragmentAttractionsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AttractionsViewModel>()
    private val listAdapter = AttractionsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_locale, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menuLocale -> {
                        findNavController().navigate(AttractionsFragmentDirections.attractionsToLocalePicker())
                        true
                    }
                    else -> {
                        false
                    }
                }
            }

        }, viewLifecycleOwner)
        _binding = FragmentAttractionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = listAdapter.apply {
            onClickAttraction = {
                findNavController().navigate(
                    AttractionsFragmentDirections.attractionsToAttractionDetail(
                        id = it.id,
                        title = it.name
                    )
                )
            }
        }

        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is AttractionsUiState.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is AttractionsUiState.Attractions -> {
                    binding.progressBar.isVisible = false
                    listAdapter.submitList(it.attractions)
                }
                is AttractionsUiState.Error -> {
                    binding.progressBar.isVisible = false
                    MaterialAlertDialogBuilder(requireActivity())
                        .setTitle(android.R.string.dialog_alert_title)
                        .setMessage(it.errorMessage)
                        .setPositiveButton(resources.getString(android.R.string.ok), null)
                        .show()
                }
            }
        }

        viewModel.fetchAttractions()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}