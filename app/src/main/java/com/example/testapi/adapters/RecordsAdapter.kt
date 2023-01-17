package com.example.testapi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.testapi.MainActivity
import com.example.testapi.R
import com.example.testapi.models.RecordsModel

class RecordsAdapter (
    val context: MainActivity,
    val recordList: List<RecordsModel>,
    val layoutId: Int
        ): RecyclerView.Adapter<RecordsAdapter.ViewHolder>(){


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val uuid = view.findViewById<TextView>(R.id.record_item_uuid)
        val artist_uuid = view.findViewById<TextView>(R.id.record_item_artist_uuid)
        val title = view.findViewById<TextView>(R.id.record_item_title)
        val number_of_plays = view.findViewById<TextView>(R.id.record_item_number_of_plays)
        val number_of_moons = view.findViewById<TextView>(R.id.record_item_number_of_moons)
        val voice_style = view.findViewById<TextView>(R.id.record_item_voice_style)
        val kind = view.findViewById<TextView>(R.id.record_item_kind)
        val description = view.findViewById<TextView>(R.id.record_item_description)
        val created_at = view.findViewById<TextView>(R.id.record_item_created_at)
        val updated_at = view.findViewById<TextView>(R.id.record_item_updated_at)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentRecord: RecordsModel = recordList[position]

        holder.uuid.text = currentRecord.uuid
        holder.artist_uuid.text = currentRecord.artist_uuid
        holder.title.text = currentRecord.title
        holder.number_of_plays.text = currentRecord.number_of_plays.toString()
        holder.number_of_moons.text = currentRecord.number_of_moons.toString()
        holder.voice_style.text = currentRecord.voice_style
        holder.kind.text = currentRecord.kind
        holder.description.text = currentRecord.description
        holder.created_at.text = currentRecord.created_at
        holder.updated_at.text = currentRecord.updated_at
    }

    override fun getItemCount(): Int {
        return  recordList.size
    }
}