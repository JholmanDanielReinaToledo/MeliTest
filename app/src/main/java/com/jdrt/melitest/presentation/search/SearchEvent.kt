package com.jdrt.melitest.presentation.search

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String): SearchEvent()
    object SearchProducts: SearchEvent()
}