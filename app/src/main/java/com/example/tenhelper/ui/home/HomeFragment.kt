package com.example.tenhelper.ui.home

import android.content.Intent
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.tenhelper.R
import com.example.tenhelper.databinding.FragmentHomeBinding
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.tenhelper.MainActivity
import com.example.tenhelper.TrackerActivity
import com.example.tenhelper.ui.player.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    val playerViewModel: PlayerViewModel by viewModels()
    private lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Scaffold(
                    topBar = {

                    }
                ) {

                    if (playerViewModel.players.value.isEmpty()) {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Surface(
                                    modifier = Modifier.size(50.dp),
                                    shape = CircleShape,
                                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                                ) {
                                    Image(painterResource(R.drawable.tennis), "Tennis Player")
                                }
                                Column(
                                    modifier = Modifier
                                        .clickable(onClick = { findNavController().navigate(R.id.setPlayerFragment) })
                                        .padding(8.dp)
                                        .align(Alignment.CenterVertically)
                                ) {
                                    Text("Set Player Stats", fontWeight = FontWeight.Bold)
                                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                                        Text(
                                            "Enter your fitness level and body comp",
                                            style = MaterialTheme.typography.body2
                                        )
                                    }
                        }
                            }
                }
                    } else {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Surface(
                                    modifier = Modifier.size(50.dp),
                                    shape = CircleShape,
                                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                                ) {
                                    Image(painterResource(R.drawable.tennis), "Tennis Player")
                                }
                                Column(
                                    modifier = Modifier
                                        .clickable(onClick = { findNavController().navigate(R.id.action_navigation_home_to_goalsFragment) })
                                        .padding(8.dp)
                                        .align(Alignment.CenterVertically)
                                ) {
                                    Text("Add Goal", fontWeight = FontWeight.Bold)
                                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                                        Text(
                                            "Set a SMART Goal",
                                            style = MaterialTheme.typography.body2
                                        )
                                    }
                                }
                            }
                            Row {
                                Surface(
                                    modifier = Modifier.size(50.dp),
                                    shape = CircleShape,
                                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                                ) {
                                    Image(painterResource(R.drawable.tennis), "Tennis Player")
                                }
                                Column(
                                    modifier = Modifier
                                        .clickable(onClick = { findNavController().navigate(R.id.action_navigation_home_to_viewGoalsFragment) })
                                        .padding(8.dp)
                                        .align(Alignment.CenterVertically)
                                ) {
                                    Text("View Goals", fontWeight = FontWeight.Bold)
                                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                                        Text(
                                            "View all Goals",
                                            style = MaterialTheme.typography.body2
                                        )
                                    }
                                }
                            }
                            Row {
                                Surface(
                                    modifier = Modifier.size(50.dp),
                                    shape = CircleShape,
                                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                                ) {
                                    Image(painterResource(R.drawable.tennis), "Tennis Player")
                                }
                                Column(
                                    modifier = Modifier
                                        .clickable(onClick = { findNavController().navigate(R.id.action_navigation_home_to_setFitnessPlanFragment) })
                                        .padding(8.dp)
                                        .align(Alignment.CenterVertically)
                                ) {
                                    Text("Generate Fitness Plan", fontWeight = FontWeight.Bold)
                                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                                        Text(
                                            "Generate new Plan",
                                            style = MaterialTheme.typography.body2
                                        )
                                    }
                                }
                            }
                            Row {
                                Surface(
                                    modifier = Modifier.size(50.dp),
                                    shape = CircleShape,
                                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                                ) {
                                    Image(painterResource(R.drawable.tennis), "Tennis Player")
                                }
                                Column(
                                    modifier = Modifier
                                        .clickable(onClick = {findNavController().navigate(R.id.action_navigation_home_to_fitnessFragment) })
                                        .padding(8.dp)
                                        .align(Alignment.CenterVertically)
                                ) {
                                    Text("View Fitness Plan", fontWeight = FontWeight.Bold)
                                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                                        Text(
                                            "View current plan",
                                            style = MaterialTheme.typography.body2
                                        )
                                    }
                                }
                            }


                            Row {
                                Surface(
                                    modifier = Modifier.size(50.dp),
                                    shape = CircleShape,
                                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                                ) {
                                    Image(painterResource(R.drawable.tennis), "Tennis Player")
                                }
                                Column(
                                    modifier = Modifier
                                        .clickable(onClick = { findNavController().navigate(R.id.navigation_dashboard) })
                                        .padding(8.dp)
                                        .align(Alignment.CenterVertically)
                                ) {
                                    Text("Edit Player", fontWeight = FontWeight.Bold)
                                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                                        Text(
                                            "Edit your player profile",
                                            style = MaterialTheme.typography.body2
                                        )
                                    }
                                }
                            }
                            Row {
                                Surface(
                                    modifier = Modifier.size(50.dp),
                                    shape = CircleShape,
                                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                                ) {
                                    Image(painterResource(R.drawable.tennis), "Tennis Player")
                                }
                                Column(
                                    modifier = Modifier
                                        .clickable(onClick = { findNavController().navigate(R.id.action_navigation_home_to_trackerActivity) })
                                        .padding(8.dp)
                                        .align(Alignment.CenterVertically)
                                ) {
                                    Text("New Activity", fontWeight = FontWeight.Bold)
                                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                                        Text("Track", style = MaterialTheme.typography.body2)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

}


@Composable
fun TopNavBar() {
    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text("TennisApp")
            },
            backgroundColor =  MaterialTheme.colors.primarySurface,
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }, actions = {
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(Icons.Filled.Share, null)
                }
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(Icons.Filled.Settings, null)
                }
            })

}}
