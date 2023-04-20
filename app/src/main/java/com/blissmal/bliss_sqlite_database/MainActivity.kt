package com.blissmal.bliss_sqlite_database

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var nameinput: EditText
    lateinit var ageinput: EditText
    lateinit var btnadd: Button
    lateinit var btnprint: Button
    lateinit var viewtextname: TextView
    lateinit var viewtextage:TextView
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameinput = findViewById(R.id.enterName)
        ageinput = findViewById(R.id.enterAge)
        btnadd = findViewById(R.id.addName)
        btnprint = findViewById(R.id.printName)
        viewtextname = findViewById(R.id.Name)
        viewtextage = findViewById(R.id.Age)

        // below code is to add on click
        // listener to our add name button
        btnadd.setOnClickListener{

            // below we have created
            // a new DBHelper class,
            // and passed context to it
            val db = DBHelper(this, null)

            // creating variables for values
            // in name and age edit texts
            val name = nameinput.text.toString()
            val age = ageinput.text.toString()

            // calling method to add
            // name to our database
            db.addName(name, age)

            // Toast to message on the screen
            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            nameinput.text.clear()
            ageinput.text.clear()
        }

        // below code is to add on  click
        // listener to our print name button
        btnprint.setOnClickListener{

            // creating a DBHelper class
            // and passing context to it
            val db = DBHelper(this, null)

            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor = db.getName()

            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            viewtextname.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
            viewtextage.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")

            // moving our cursor to next
            // position and appending values
            while(cursor.moveToNext()){
                viewtextname.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                viewtextage.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
            }

            // at last we close our cursor
            cursor.close()
        }
    }
}