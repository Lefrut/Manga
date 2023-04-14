package com.dashkevich.utility.adapter

interface AdapterItemDelegate {

    fun id(): Any

    fun content(): Any

    fun payload(newItem: Any): Payload

    interface Payload {
        object None: Payload
    }

}