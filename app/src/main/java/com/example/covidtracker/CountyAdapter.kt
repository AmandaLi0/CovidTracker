package com.example.covidtracker

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class CountyAdapter(var dataSet: List<CountyData>) :
    RecyclerView.Adapter<CountyAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView
        val lastUpdatedTextView: TextView
        val casesTextView: TextView
        val layout: ConstraintLayout


        init {
            nameTextView = view.findViewById(R.id.textView_countyitem_county)
            lastUpdatedTextView = view.findViewById(R.id.textView_countyitem_lastUpdatedDate)
            casesTextView = view.findViewById(R.id.textView_countyitem_weeklyCases)
            layout = view.findViewById(R.id.layout_countyitem)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_county_data, viewGroup, false)

        val holder = ViewHolder(view)
        return holder
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: CountyAdapter.ViewHolder, position: Int) {
        val context = holder.casesTextView.context
        val countyData = dataSet[holder.layoutPosition]
        holder.nameTextView.text = countyData.county
        holder.casesTextView.text = countyData?.metrics?.weeklyNewCasesPer100k.toString()
        holder.lastUpdatedTextView.text = countyData.lastUpdatedDate
        holder.layout.setOnClickListener {
            val detailIntent = Intent(it.context, CountyDetailActivity::class.java)
            detailIntent.putExtra(CountyDetailActivity.EXTRA_COUNTY, countyData)
            it.context.startActivity(detailIntent)
        }

        when (countyData.cdcTransmissionLevel) {
            // low
            0 -> {
                holder.nameTextView.setTextColor(
                    context.resources.getColor(
                        R.color.low_transmission,
                        context.theme
                    )
                )
                holder.nameTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0)
            }
            // moderate
            1 -> {
                holder.nameTextView.setTextColor(
                    context.resources.getColor(
                        R.color.black,
                        context.theme
                    )
                )
                holder.nameTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    R.drawable.ic_baseline_notification_important_24, 0, 0, 0
                )
            }
            // substantial
            2 -> {
                holder.nameTextView.setTextColor(
                    context.resources.getColor(
                        R.color.substantial_transmission,
                        context.theme
                    )
                )
                holder.nameTextView
                    .setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.ic_baseline_warning_amber_24, 0, 0, 0
                    )
            }
            // high
            3 -> {
                holder.nameTextView.setTextColor(
                    context.resources.getColor(
                        R.color.high_transmission,
                        context.theme
                    )
                )
                holder.nameTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    R.drawable.ic_baseline_warning_24,
                    0,
                    0,
                    0
                )
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
