package com.dscoding.takenoteapp.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
