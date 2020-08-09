package com.company.testapp

import android.content.SharedPreferences
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class TimerFragment : Fragment() {

    lateinit var countTime:TextView
    lateinit var meter:Chronometer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_timer, container, false)
         countTime = rootView.findViewById(R.id.timer_text)
         meter = rootView.findViewById(R.id.c_meter)


        val perferences: SharedPreferences = this.getActivity()!!.getSharedPreferences("login",
            AppCompatActivity.MODE_PRIVATE
        )
        val first_date : String? = perferences.getString("first_date","")
        val timer : Int = perferences.getInt("app_timer",0)

        println("grg"+timer)


        meter.base = SystemClock.elapsedRealtime() - timer
        meter.start()

        countTime.text = first_date

        return rootView


    }

    override fun onPause()
    {



        val perferences: SharedPreferences = this.getActivity()!!.getSharedPreferences("login",
            AppCompatActivity.MODE_PRIVATE
        )
        var elapsedMillis:Int = (SystemClock.elapsedRealtime() - meter.getBase()).toInt()
        val editor : SharedPreferences.Editor  = perferences.edit()

        editor.putInt("app_timer",elapsedMillis)
        editor.apply()
        super.onPause()

    }




}
