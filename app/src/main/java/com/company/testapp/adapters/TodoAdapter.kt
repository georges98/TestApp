package com.company.testapp.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.company.testapp.R
import com.company.testapp.model.Todo
import java.util.*
import kotlin.collections.ArrayList


class TodoAdapter(private val context: Context,
                  private val dataSource: ArrayList<Todo>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    //1
    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.list_item, parent, false)

        // Get title element
        val titleTextView = rowView.findViewById(R.id.title) as TextView

// Get subtitle element
        val subtitleTextView = rowView.findViewById(R.id.completed) as TextView

        val recipe = getItem(position) as Todo

// 2
        titleTextView.text = recipe.title
        subtitleTextView.text = recipe.completed.toString()



        var contstraint = rowView.findViewById(R.id.contstraint) as ConstraintLayout

        val rnd = Random()
        val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        if(recipe.completed)
        {
            contstraint.setBackgroundColor(color)
        }



        return rowView
    }
}