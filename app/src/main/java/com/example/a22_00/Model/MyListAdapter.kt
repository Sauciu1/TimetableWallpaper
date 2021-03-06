package com.example.a22_00.Model

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.a22_00.DBHelper.DBHelper
import com.example.a22_00.R
import java.lang.String.format
import java.time.format.DateTimeFormatter

class MyListAdapter(private val context: Activity, private val activities: Array<com.example.a22_00.Model.Activity>)
    : ArrayAdapter<String>(context, R.layout.row, activities.mapNotNull { it.name }.toTypedArray()) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.activity_row, null, true)

        val titleText = rowView.findViewById(R.id.activityname) as TextView
        val subtitleText = rowView.findViewById(R.id.activitydescription) as TextView
        val activityContainer = rowView.findViewById(R.id.cntactivity) as LinearLayout

        titleText.text = activities[position].name
        var dabar= (activities[position].dayOfTheWeek).toInt()
        var counter=0;
        var tekstas= ("| Mo | Tu | We | Th | Fr | Sa | Su |").toCharArray()

        for(each in 1..7){
            if(dabar%2==0){
                tekstas[counter+2]='-'
                tekstas[counter+3]='-'
           }

           counter+=5
           dabar/=2
        }
        val teksto=String(tekstas)



        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        subtitleText.text = "${activities[position].begining.format(formatter)} (${activities[position].duration} mins) \n ${teksto}" //+ " ${activities[position].minutesTillStart()}"

        activityContainer.setBackgroundColor(activities[position].color!!.toArgb())

        val btnDelete = rowView.findViewById<Button>(R.id.Naikinti)
        btnDelete.setOnClickListener({
            val db = DBHelper(this.context)
            db.deleteActivity(activities[position])
            db.close()
            this.context.recreate()
        })

        return rowView
    }

}