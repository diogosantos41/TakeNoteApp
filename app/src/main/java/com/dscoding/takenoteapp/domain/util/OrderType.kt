package com.dscoding.takenoteapp.domain.util

sealed interface OrderType {
    object Ascending : OrderType
    object Descending : OrderType
}
