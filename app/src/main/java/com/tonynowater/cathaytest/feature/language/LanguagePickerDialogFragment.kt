package com.tonynowater.cathaytest.feature.language

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tonynowater.cathaytest.databinding.DialogFragmentLanguagePickerBinding
import com.tonynowater.cathaytest.utils.extensions.setWidthPercent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguagePickerDialogFragment : DialogFragment() {

    private var _binding: DialogFragmentLanguagePickerBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LanguagePickerViewModel>()
    private val listAdapter = LanguagePickerListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentLanguagePickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = listAdapter.apply {
            onClickLanguage = {
                viewModel.changeLocale(it.languageTag)
                findNavController().popBackStack()
            }
        }

        viewModel.uiState.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }

        viewModel.loadAppLocales()
    }

    override fun onResume() {
        super.onResume()
        setWidthPercent(65)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}