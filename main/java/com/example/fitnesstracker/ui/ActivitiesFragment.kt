package com.example.fitnesstracker.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesstracker.R

class ActivitiesFragment : Fragment() {
    private lateinit var activitiesList: RecyclerView
    private lateinit var addActivityButton: Button
    private val activities = mutableListOf<String>()
    private lateinit var adapter: ActivityAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_activities, container, false)
        activitiesList = view.findViewById(R.id.activities_list)
        addActivityButton = view.findViewById(R.id.add_activity_button)

        adapter = ActivityAdapter(activities)
        activitiesList.adapter = adapter
        activitiesList.layoutManager = LinearLayoutManager(context)

        addActivityButton.setOnClickListener {
            showAddActivityDialog()
        }

        return view
    }

    private fun showAddActivityDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_add_activity, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.edit_text_activity)

        with(builder) {
            setTitle("Add New Activity")
            setPositiveButton("Add") { dialog, which ->
                val newActivity = editText.text.toString()
                if (newActivity.isNotEmpty()) {
                    activities.add(newActivity)
                    adapter.notifyItemInserted(activities.size - 1)
                }
            }
            setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            setView(dialogLayout)
            show()
        }
    }
}