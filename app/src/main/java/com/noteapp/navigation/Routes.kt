package com.noteapp.navigation

sealed class  Routes(val route: String){
    object Notes : Routes("notes")
    object Favorites : Routes ("favorites")
    object Profile : Routes ("profile")

    object  Detail : Routes("detail/{noteid}") {
        fun createRoute(noteid: Int) = "detail/$noteid"
    }
    object  Addnote : Routes ("addnote")
    object  Editnote : Routes ("editnote/{noteid}"){
        fun createRoute(noteId: Int) = "edit_note/$noteId"
    }
}