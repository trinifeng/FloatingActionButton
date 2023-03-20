package com.example.feng.floatingactionbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var myListView: ListView
    lateinit var addCommentFAB: FloatingActionButton
    lateinit var addReactionFAB: FloatingActionButton

    var items = ArrayList<String>()
    var myAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myListView = findViewById(R.id.my_list_view)
        addCommentFAB = findViewById(R.id.add_comment_fab)
        addReactionFAB = findViewById(R.id.add_reaction_fab)

        myAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        myListView.adapter = myAdapter

        addCommentFAB.setOnClickListener {
            items.add("This is a new message " + items.size)
            myAdapter?.notifyDataSetChanged()
            val undoClickListener = View.OnClickListener {
                items.removeAt(items.size - 1)
                myAdapter?.notifyDataSetChanged()
                Snackbar.make(it, "Message Removed", Snackbar.LENGTH_LONG)
                    .setAction("None", null)
                    .show()
            }
            Snackbar.make(it, "Message Added", Snackbar.LENGTH_LONG)
                .setAction("Undo", undoClickListener)
                .show()
        }

        addReactionFAB.setOnClickListener {
            myListView.setOnItemClickListener { parent, view, position, id ->
                items[position] += "\n❤️"
                myAdapter?.notifyDataSetChanged()
                Snackbar.make(it, "Reaction Added", Snackbar.LENGTH_LONG)
                    .setAction("None", null)
                    .show()
            }

        }
    }
}