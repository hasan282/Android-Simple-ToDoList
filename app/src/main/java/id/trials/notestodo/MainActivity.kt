package id.trials.notestodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var noteAdapter: AdapterNotes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteAdapter = AdapterNotes(mutableListOf())

        rvTaskList.adapter = noteAdapter
        rvTaskList.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            val noteTitle: String = etNewTask.text.toString()
            if (noteTitle.isNotEmpty()) {
                noteAdapter.addNotes(DataNotes(noteTitle))
                etNewTask.text.clear()
            }
        }

        btnDelete.setOnClickListener {
            noteAdapter.deleteNotes()
        }
    }
}