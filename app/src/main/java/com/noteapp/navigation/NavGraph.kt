package com.noteapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.noteapp.ui.NoteViewModel
import com.noteapp.ui.screen.AddnoteScreen
import com.noteapp.ui.screen.EditNoteSceen
import com.noteapp.ui.screen.FavoritesScreen
import com.noteapp.ui.screen.NotesScreen
import com.noteapp.ui.screen.ProfileScreen

@Composable
fun AppNavGraph(navController: NavHostController, viewModel: NoteViewModel) {
    NavHost(
        navController = navController,
        startDestination = Routes.Notes.route
    ){
        composable(Routes.Notes.route) {
            NotesScreen(navController, viewModel)
        }
        composable(Routes.Favorites.route){
            FavoritesScreen(viewModel)
        }
        composable(Routes.Profile.route){
            ProfileScreen()
        }

        composable(
            route = Routes.Detail.route,
            arguments = listOf(navArgument("noteid"){type = NavType.IntType})
        ) { backStackEntry ->
            val noteid = backStackEntry.arguments?.getInt("noteid")?: 0
            EditNoteSceen(noteid, navController, viewModel)

        }
        composable(Routes.Addnote.route){
            AddnoteScreen(navController, viewModel)
        }
        composable(
            route = Routes.Editnote.route,
            arguments = listOf(navArgument("noteid"){type = NavType.IntType})
        ) {backStackEntry ->
            val noteid = backStackEntry.arguments?.getInt("noteid")?: 0
            EditNoteSceen(noteid, navController, viewModel)
        }
    }
}