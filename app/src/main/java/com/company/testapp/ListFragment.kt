package com.company.testapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.company.testapp.adapters.TodoAdapter
import com.company.testapp.model.Todo
import kotlinx.android.synthetic.main.fragment_list.*
import org.json.JSONArray
import org.json.JSONException


class ListFragment : Fragment() , SwipeRefreshLayout.OnRefreshListener {

    private lateinit var listView: ListView


    //the hero list where we will store all the hero objects after parsing json
    lateinit var todoList: ArrayList<Todo>
    lateinit var swiperefresh:SwipeRefreshLayout


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_list)
//
//
//    }

    private fun jsonParse() {
        val url = "https://jsonplaceholder.typicode.com/todos"

        val requestQueue = Volley.newRequestQueue(activity)

        val request = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener<JSONArray> {
                response ->try {

            progress_home.setVisibility(View.INVISIBLE)
            swiperefresh.setRefreshing(false);

            for (i in 0 until response.length()) {
                val res = response.getJSONObject(i)
                val userId = res.getInt("userId")
                val id = res.getInt("id")
                val title = res.getString("title")
                val completed  = res.getBoolean("completed")

                val todo = Todo(userId, id,title,completed)
                todoList.add(todo)

            }



            val adapter =
                TodoAdapter(activity!!, todoList)
            listView.adapter = adapter
        } catch (e: JSONException) {
            println("grg"+e.toString())
            e.printStackTrace()
        }
        }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue?.add(request)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView = inflater.inflate(R.layout.fragment_list, container, false)


        listView = rootView.findViewById(R.id.recipe_list_view)
        todoList = ArrayList<Todo>()

        jsonParse()

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem:Todo = parent.getItemAtPosition(position) as Todo
            val intent = Intent(activity, TodoDetailsActivity::class.java)
            intent.putExtra("item", selectedItem)
            startActivity(intent)
        }
        swiperefresh = rootView.findViewById(R.id.swiperefresh)
        swiperefresh.setOnRefreshListener(this)


        return rootView
    }



    override fun onRefresh() {
        jsonParse()

    }


}

