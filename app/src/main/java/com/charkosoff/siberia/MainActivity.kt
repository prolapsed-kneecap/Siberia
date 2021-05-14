package com.charkosoff.siberia

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController


import com.charkosoff.siberia.data.Data.schetchik

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(schetchik == 1){





        }

//        val request = NavDeepLinkRequest.Builder
//            .fromUri("resultFragment".toUri())
//            .build()
//        findNavController(R.id.nav_host_fragment_container).navigate(request)


    }
}


