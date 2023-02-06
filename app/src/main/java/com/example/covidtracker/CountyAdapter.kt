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
        val textViewCounty: TextView
        val textViewDate: TextView
        val textViewWeeklyCases: TextView
        val layout: ConstraintLayout

        init {
            textViewCounty = view.findViewById(R.id.textView_countyitem_county)
            textViewDate = view.findViewById(R.id.textView_countyitem_lastUpdatedDate)
            textViewWeeklyCases = view.findViewById(R.id.textView_countyitem_weeklyCases)
            layout = view.findViewById(R.id.layout_countyitem)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_county_data, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val county = dataSet[position]
        viewHolder.textViewCounty.text = county.county
        viewHolder.textViewDate.text = county.lastUpdatedDate
        viewHolder.textViewWeeklyCases.text = county.metrics.weeklyNewCasesPer100K.toString()
        viewHolder.layout.setOnClickListener {
            Toast.makeText(it.context, county.toString(), Toast.LENGTH_SHORT).show()
            //make intent to open new activity
            val detailIntent = Intent(it.context, CountyDetailActivity::class.java)
//            detailIntent.putExtra(HeroesDetailActivity.EXTRA_NAME, hero.name)
//            detailIntent.putExtra(HeroesDetailActivity.EXTRA_DESCRIPTION, hero.description)
//            detailIntent.putExtra(HeroesDetailActivity.EXTRA_SUPERPOWER, hero.superpower)
//            detailIntent.putExtra(HeroesDetailActivity.EXTRA_RANKING, hero.ranking)
//            detailIntent.putExtra(HeroesDetailActivity.EXTRA_IMAGE, hero.image)

//            detailIntent.putExtra(CountyDetailActivity.EXTRA_COUNTY, county)
//            it.context.startActivity(detailIntent)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
