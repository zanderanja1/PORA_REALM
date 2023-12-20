package com.example.pora_realm

import com.example.pora_realm.Address
import com.example.pora_realm.Reminder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import java.sql.Time
import java.text.DateFormat
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val configuration = RealmConfiguration.create(schema = setOf( Reminder::class, Address::class))
            val realm = Realm.open(configuration)

        val now = System.currentTimeMillis()
        val sqlTime = Time(now)
        val df: DateFormat = SimpleDateFormat("MM-dd-yyyy")
        val sqlDate = java.sql.Date(df.parse("02-04-2024").getTime())

        val reminder = Reminder().apply {
            time = "12.00"
            message = "Zobozdravnik"
            date = sqlDate.toString()
            location = Address().apply { streetName = "Gradnikova 1"; postalCode = 5290; country ="Slovenia"}
        }

        realm.writeBlocking { // this : MutableRealm
            val managedReminder = copyToRealm(reminder)
        }

        val all = realm.query<Reminder>().find()
        val reminderByMessage: RealmQuery<Reminder> = realm.query<Reminder>("message = $1", "Zobozdravnik")
        val filteredByMessage: RealmResults<Reminder> = reminderByMessage.find()

        Log.v("all reminders", filteredByMessage.toString())
    }
}