package com.example.testapi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.testapi.adapters.RecordsAdapter
import com.example.testapi.models.RecordsModel
import com.example.testapi.repositories.RecordRepository
import com.example.testapi.viewModels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val create: Button = findViewById(R.id.create)
        val read: Button = findViewById(R.id.read)
        val update: Button = findViewById(R.id.update)
        val delete: Button = findViewById(R.id.delete)

        // CREATE
        create.setOnClickListener {

            val record = RecordsModel(
                uuid = findViewById<EditText>(R.id.edit_text_uuid).text.toString(),
                artist_uuid = findViewById<EditText>(R.id.edit_text_artist_uuid).text.toString(),
                title = findViewById<EditText>(R.id.edit_text_title).text.toString(),
                number_of_plays = findViewById<EditText>(R.id.edit_text_number_of_plays).text.toString()
                    .toIntOrNull(),
                number_of_moons = findViewById<EditText>(R.id.edit_text_number_of_moons).text.toString()
                    .toIntOrNull(),
                voice_style = findViewById<EditText>(R.id.edit_text_voice_style).text.toString(),
                kind = findViewById<EditText>(R.id.edit_text_kind).text.toString(),
                description = findViewById<EditText>(R.id.edit_text_description).text.toString(),
                created_at = findViewById<EditText>(R.id.edit_text_created_at).text.toString(),
                updated_at = findViewById<EditText>(R.id.edit_text_updated_at).text.toString()
            )

            viewModel.createRecord(record)
        }

        //READ
        val recycler = findViewById<RecyclerView>(R.id.recycler)

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.fetchRecord()
        }
        viewModel.recordList.observe(this, Observer { recordList ->
            if (recordList != null) {
                recycler.adapter = RecordsAdapter(
                    this@MainActivity,
                    recordList,
                    R.layout.item_record
                )
            }
        })


        //READONE
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.fetchOneRecord("uuid")
        }

        viewModel.record.observe(this, Observer { record ->
            if (record != null) {
                findViewById<TextView>(R.id.record_one_item_uuid).text = record.uuid
                findViewById<TextView>(R.id.record_one_item_artist_uuid).text = record.artist_uuid
                findViewById<TextView>(R.id.record_one_item_title).text = record.title
                findViewById<TextView>(R.id.record_one_item_number_of_plays).text =
                    record.number_of_plays.toString()
                findViewById<TextView>(R.id.record_one_item_number_of_moons).text =
                    record.number_of_moons.toString()
                findViewById<TextView>(R.id.record_one_item_voice_style).text = record.voice_style
                findViewById<TextView>(R.id.record_one_item_kind).text = record.kind
                findViewById<TextView>(R.id.record_one_item_description).text = record.description
                findViewById<TextView>(R.id.record_one_item_created_at).text = record.created_at
                findViewById<TextView>(R.id.record_one_item_updated_at).text = record.updated_at
            }
        })

    // UPDATE
    update.setOnClickListener{

        val record = RecordsModel(
            uuid = findViewById<EditText>(R.id.edit_text_uuid).text.toString(),
            artist_uuid = findViewById<EditText>(R.id.edit_text_artist_uuid).text.toString(),
            title = findViewById<EditText>(R.id.edit_text_title).text.toString(),
            number_of_plays = findViewById<EditText>(R.id.edit_text_number_of_plays).text.toString().toIntOrNull(),
            number_of_moons = findViewById<EditText>(R.id.edit_text_number_of_moons).text.toString().toIntOrNull(),
            voice_style = findViewById<EditText>(R.id.edit_text_voice_style).text.toString(),
            kind = findViewById<EditText>(R.id.edit_text_kind).text.toString(),
            description = findViewById<EditText>(R.id.edit_text_description).text.toString(),
            created_at = findViewById<EditText>(R.id.edit_text_created_at).text.toString(),
            updated_at = findViewById<EditText>(R.id.edit_text_updated_at).text.toString()
        )

        viewModel.updateRecord(record)
    }

        delete.setOnClickListener{
            viewModel.deleteRecord(
                recordUuid = findViewById<EditText>(R.id.edit_text_uuid).text.toString())
        }
    }
}

