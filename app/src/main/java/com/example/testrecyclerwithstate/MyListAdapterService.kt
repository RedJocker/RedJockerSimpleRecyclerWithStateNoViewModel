package com.example.testrecyclerwithstate

interface MyListAdapterService {
    fun setCurrentItem(modelItem: ModelWithState)
    fun getCurrentItem(): ModelWithState?
}