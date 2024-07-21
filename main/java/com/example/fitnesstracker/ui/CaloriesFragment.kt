package com.example.fitnesstracker.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fitnesstracker.R

class CaloriesFragment : Fragment() {
    private lateinit var addCaloriesButton: Button
    private lateinit var totalCaloriesTextView: TextView
    private var totalCalories = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calories, container, false)
        addCaloriesButton = view.findViewById(R.id.add_calories_button)
        totalCaloriesTextView = view.findViewById(R.id.total_calories_text_view)

        addCaloriesButton.setOnClickListener {
            showAddCaloriesDialog()
        }

        return view
    }

    private fun showAddCaloriesDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_add_calories, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.edit_text_calories)

        with(builder) {
            setTitle("Add Calories")
            setPositiveButton("Add") { dialog, which ->
                val caloriesStr = editText.text.toString()
                if (caloriesStr.isNotEmpty()) {
                    val calories = caloriesStr.toInt()
                    totalCalories += calories
                    updateTotalCaloriesDisplay()
                }
            }
            setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            setView(dialogLayout)
            show()
        }
    }

    private fun updateTotalCaloriesDisplay() {
        totalCaloriesTextView.text = "Total Calories: $totalCalories"
    }
}