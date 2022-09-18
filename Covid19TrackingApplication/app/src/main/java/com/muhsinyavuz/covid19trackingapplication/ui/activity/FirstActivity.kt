package com.muhsinyavuz.covid19trackingapplication.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import com.muhsinyavuz.covid19trackingapplication.R
import com.muhsinyavuz.covid19trackingapplication.databinding.ActivityFirstBinding
import okhttp3.OkHttpClient
import okhttp3.Request

import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocate()
        binding =ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.examineButton.setOnClickListener {
           val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }
        val actionBar = supportActionBar
        actionBar!!.title = resources.getString(R.string.app_name)

        binding.settings.setOnClickListener{
        showChangeLang()
        }

    }

    private fun showChangeLang(){
        val listItem = arrayOf("Türkçe","English")
        val builder = AlertDialog.Builder(this@FirstActivity)
        builder.setTitle(R.string.chooselanguage)
        builder.setSingleChoiceItems(listItem,-1){dialog,which ->

            if (which ==0){
                setLocale("tr")
                recreate()
            }else if(which ==1){
                setLocale("en")
                recreate()
            }

            dialog.dismiss()
        }
        val mDialog = builder.create()
        mDialog.show()
    }
    fun setLocale(Lang : String){
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config,baseContext.resources.displayMetrics)
        val editor = getSharedPreferences("Settings",Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang",Lang)
        editor.apply()

    }
    fun loadLocate(){
        val sharedPreferences = getSharedPreferences("Settings",Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang","")

        if (language != null) {
            setLocale(language)
        }
    }
}