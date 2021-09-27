package com.example.tenhelper.ui.goals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.example.tenhelper.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewGoalsFragment : Fragment() {
    val viewModel: GoalsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                ViewGoalsScreen(viewModel)
            }
        }
    }

}