package com.example.tenhelper.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.tenhelper.R
import com.example.tenhelper.databinding.FragmentDashboardBinding
import com.example.tenhelper.ui.home.HomeFragmentDirections
import com.example.tenhelper.ui.player.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val playerViewModel: PlayerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {

                Scaffold(
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(color = Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.White)
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "Edit Player Screen",
                                style = MaterialTheme.typography.body1,
                                textAlign = TextAlign.Center)
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.White)
                                .padding(8.dp)
                        ) {
                            var players = playerViewModel.players.value

                            // Choose player to edit in case of multiple options in future
                            LazyColumn() {
                                players.forEach { (id, name, height, weight, rating, timesPerWeek) ->
                                    item {
                                        Text(text = name)
                                        Button(onClick = {
                                            val action = DashboardFragmentDirections.actionNavigationDashboardToEditPlayerFragment(
                                                name
                                            )
                                            findNavController().navigate(action)
                                        }) {
                                            Text(text = "Edit ${name}'s profile")
                                        }
                                    }
                                }
                            }

                        }

                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}