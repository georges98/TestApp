package com.company.testapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.company.testapp.model.User
import kotlinx.android.synthetic.main.login_activity.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class LoginFragment : AppCompatActivity() {


     lateinit var email: EditText
     lateinit var password: EditText
     lateinit var rememberMe: CheckBox
     lateinit var login: Button
     var isBtnChecked:Boolean = false

      var users: List<User> = arrayListOf( User(1,"georges","123"), User(2,"admin","admin"))

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)



        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        rememberMe = findViewById(R.id.rememberMe)
        login = findViewById(R.id.btn_login)

        login.setOnClickListener{

            if(email.text.isNotEmpty() && password.text.isNotEmpty() && users.count() > 0)
            {

                var user: User? = users.find{ user -> user.email.equals(email.text.toString()) && user.password.equals(password.text.toString())}


                if(user != null)
                {

                    val perferences: SharedPreferences  = getSharedPreferences("login",MODE_PRIVATE)
                    val editor :SharedPreferences.Editor  = perferences.edit()
                    val first_date : String? = perferences.getString("first_date","")
                    if(first_date == "") {

                        val current = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                        val formatted = current.format(formatter)

                        editor.putString("first_date", formatted)
                    }

                    editor.putInt("app_timer", 0)


                    editor.putString("rememeber", isBtnChecked.toString())

                    editor.apply()

                    val intent = Intent(this@LoginFragment, MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"Invalid Credentials",Toast.LENGTH_SHORT).show()
                }

            }

        }

        rememberMe.setOnCheckedChangeListener{ buttonView, isChecked ->

            isBtnChecked = isChecked

        }



    }

    override fun onStart()
    {
        super.onStart()
        val perferences: SharedPreferences  = getSharedPreferences("login",MODE_PRIVATE)
        val checkbox : String? = perferences.getString("rememeber","")
        if(checkbox.equals("true"))
        {
            val intent = Intent(this@LoginFragment, MainActivity::class.java)
            startActivity(intent)
        }
        else{
            if(checkbox.equals("false"))
            {
                Toast.makeText(this,"Please Sign In.",Toast.LENGTH_SHORT).show()
            }
        }
    }
}

