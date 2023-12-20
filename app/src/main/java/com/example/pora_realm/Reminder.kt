package com.example.pora_realm

import io.realm.kotlin.types.RealmObject
import java.sql.Time
import java.util.Date
import java.util.UUID


class Reminder: RealmObject {
    var time: String = ""
    var message: String = ""
    var date: String =""
    var location: Address? = null
}