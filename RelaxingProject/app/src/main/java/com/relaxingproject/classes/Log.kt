package com.relaxingproject.classes

class Log{
    var title: String = "Default"
        get() = field
        set(value){
            field = value
        }
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
    fun dateFormat(){
        val dateList = date.split("/").toMutableList()
        if(dateList.size != 3){
            date = "invalid"
            return
        }
        for(i in 0..1){
            when (dateList[i]) {
                "01" -> {
                    dateList[i] = "1"
                }
                "02" -> {
                    dateList[i] = "2"
                }
                "03" -> {
                    dateList[i] = "3"
                }
                "04" -> {
                    dateList[i] = "4"
                }
                "05" -> {
                    dateList[i] = "5"
                }
                "06" -> {
                    dateList[i] = "6"
                }
                "07" -> {
                    dateList[i] = "7"
                }
                "08" -> {
                    dateList[i] = "8"
                }
                "09" -> {
                    dateList[i] = "9"
                }
                else -> {
                    if(dateList[i].toIntOrNull() == null){
                        date = "invalid"
                        return
                    }
                    if(i == 0 && (dateList[i].toInt() > 31 || dateList[i].toInt() < 1)){
                        date = "invalid"
                        return
                    }
                    if(i == 1 && (dateList[i].toInt() > 12 || dateList[i].toInt() < 1)){
                        date = "invalid"
                        return
                    }
                }
            }
        }
        if(dateList[2].toIntOrNull() == null){
            date = "invalid"
            return
        }
        date = dateList[0] + "/" + dateList[1] + "/" + dateList[2]
    }
}
