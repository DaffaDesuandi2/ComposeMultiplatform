package com.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.noteapp.navigation.AppNavGraph
import com.noteapp.navigation.MyBottomNavigation
import com.noteapp.navigation.Routes
import com.noteapp.ui.screen.NotesScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    MyBottomNavigation(navController)
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        navController.navigate(Routes.Addnote.route)
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Note")
                    }
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)){
                    AppNavGraph(navController = navController)
                }

            }
        }
    }
}

