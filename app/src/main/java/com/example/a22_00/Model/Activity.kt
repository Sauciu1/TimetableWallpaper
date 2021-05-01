package com.example.a22_00.Model

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import java.sql.Time
import java.time.LocalTime

class Activity {
    //var  id: Int = 0
    var name: String = ""
    @RequiresApi(Build.VERSION_CODES.O)
    var begining: LocalTime = LocalTime.now()
    var duration: Long = 60
    var color: Color? = null

    constructor(){}
    constructor( name: String,begining: LocalTime, durationInMinutes: Long, color: Color){
        //this.id = id
        this.name = name
        this.begining = begining
        this.duration = durationInMinutes
        this.color = color
    }
}