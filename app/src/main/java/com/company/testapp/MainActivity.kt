package com.company.testapp


import android.app.PendingIntent.getActivity
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle(R.string.timer_screen);

        var drawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        ) {
            override fun onDrawerClosed(view: View) {
                super.onDrawerClosed(view)

            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)

            }
        }

        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()



        navigationView.setNavigationItemSelectedListener(this)

        supportFragmentManager.beginTransaction().replace(R.id.content_frame, TimerFragment()).commit()
        navigationView.setCheckedItem(R.id.timer_screen);

    }


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId)
            {
                 R.id.timer_screen -> {
                     setTitle(R.string.timer_screen) ;
                     supportFragmentManager.beginTransaction().replace(R.id.content_frame, TimerFragment()).commit()}
                 R.id.list_view_screen -> {
//                     val intent = Intent(this, ListFragment::class.java)
//
//                     startActivity(intent)

                     setTitle(R.string.list_view_screen) ;
                     supportFragmentManager.beginTransaction().replace(R.id.content_frame, ListFragment()).commit()
                 }
                 R.id.logout -> {

                     AlertDialog.Builder(this)
                         .setTitle("Do you want to Logout?")
                         .setMessage("Are you sure you want to logout?")
                         .setNegativeButton(android.R.string.no, null)
                         .setPositiveButton(android.R.string.yes,
                             DialogInterface.OnClickListener { dialog, which ->
                                 super@MainActivity.onBackPressed()
                                 logout()
                             }).create().show()


                 }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun logout() {
        val perferences: SharedPreferences = getSharedPreferences("login",MODE_PRIVATE)
        val editor :SharedPreferences.Editor  = perferences.edit()
        editor.putString("first_date", "")
        editor.putInt("app_timer",0)
        editor.putString("rememeber", "false")

        editor.commit()

        finish()
    }

    override fun onBackPressed() {



       val frag =  getSupportFragmentManager().findFragmentById(R.id.content_frame)
        if(frag is TimerFragment)
        {
            AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes,
                    DialogInterface.OnClickListener { dialog, which ->
                        super@MainActivity.onBackPressed()
                        quit()
                    }).create().show()
        }
        else{
            supportFragmentManager.beginTransaction().replace(R.id.content_frame, TimerFragment()).commit()
        }


    }
    fun quit() {
        val start = Intent(Intent.ACTION_MAIN)
        start.addCategory(Intent.CATEGORY_HOME)
        start.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(start)
    }

}
