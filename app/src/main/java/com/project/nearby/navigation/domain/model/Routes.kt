package com.project.nearby.navigation.domain.model

sealed class Routes(
    val route : String
){
    data object Event : Routes("Event")
    data object Main : Routes("Main")
    data object Id : Routes("/{id}")
}
