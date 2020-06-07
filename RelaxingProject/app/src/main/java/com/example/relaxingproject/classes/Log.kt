package com.relaxingproject.classes

import java.util.Date

class Log{
    var title: String = "Default"
        get() = field
        set(value){
            field = value
        }
    /*var date: Date = Date(2020,1,1)
        get() = field
        set(value) {
            field = value
        }*/
    var date: String = "01/01/2020"
        get() = field
        set(value) {
            field = value
        }
    var text: String = "Default"
    get() = field
    set(value){
        field = value
    }
    var rating: String = "5"
        get() = field
        set(value){
            field = value
        }
    var image: ByteArray = ByteArray(0)
        get() = field
        set(value){
            field = value
        }
}
