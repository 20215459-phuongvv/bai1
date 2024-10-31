package com.example.bai1

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var number: EditText
    private lateinit var even: RadioButton
    private lateinit var odd: RadioButton
    private lateinit var square: RadioButton
    private lateinit var show: Button
    private lateinit var listView: ListView
    private lateinit var error: TextView
    private lateinit var adapter: ArrayAdapter<String>
    private val listItems = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        number = findViewById(R.id.number)
        even = findViewById(R.id.even)
        odd = findViewById(R.id.odd)
        square = findViewById(R.id.square)
        show = findViewById(R.id.show)
        listView = findViewById(R.id.listView)
        error = findViewById(R.id.error)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter

        show.setOnClickListener { showNumbers() }
    }

    private fun showNumbers() {
        listItems.clear()
        error.visibility = View.GONE

        val input = number.text.toString()
        if (input.isEmpty()) {
            error.text = "Please enter a number"
            error.visibility = View.VISIBLE
            return
        }

        val n = input.toIntOrNull()
        if (n == null || n < 0) {
            error.text = "Please enter a positive integer"
            error.visibility = View.VISIBLE
            return
        }

        when {
            even.isChecked -> {
                for (i in 0..n step 2) {
                    listItems.add(i.toString())
                }
            }
            odd.isChecked -> {
                for (i in 1..n step 2) {
                    listItems.add(i.toString())
                }
            }
            square.isChecked -> {
                var i = 0
                while (i * i <= n) {
                    listItems.add((i * i).toString())
                    i++
                }
            }
            else -> {
                error.text = "Please select a number type"
                error.visibility = View.VISIBLE
                return
            }
        }

        adapter.notifyDataSetChanged()
    }
}