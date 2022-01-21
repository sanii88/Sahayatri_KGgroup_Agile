package com.sunny.sahayatribookingsewa.model

data class User(
    var _id:String? = "",
    var username: String? = null,
    var email:String?=null,
    var address:String?=null,
    var phone: String? = null,
    var password:String? = null,
    var profile_pic:String?= null,
    var userType:String?=null
)
