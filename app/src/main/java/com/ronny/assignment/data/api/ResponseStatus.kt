package com.ronny.assignment.data.api


data class ResponseStatus<T>(val status: Status, val data: T?, val error: String?) {

    companion object {
        fun <T> success(data: T?): ResponseStatus<T> {
            return ResponseStatus(Status.SUCCESS, data, null)
        }

        fun <T> loading(): ResponseStatus<T> {
            return ResponseStatus(Status.LOADING, null, null)
        }

        fun <T> error(error: String?): ResponseStatus<T> {
            return ResponseStatus(Status.ERROR, null, error)
        }
    }
}

enum class Status {
    LOADING, SUCCESS, ERROR
}