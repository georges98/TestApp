package com.company.testapp.model

import java.io.Serializable


data class Todo(
    var id:Int   = 0,
    var userId:Int       = 0,
    var title:String = "",
    var completed:Boolean = false
) : Serializable