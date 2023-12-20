package com.example.pora_realm

import io.realm.kotlin.types.RealmObject


class Address : RealmObject{
    var streetName: String = ""
    var postalCode: Int = 0
    var country: String = ""
}