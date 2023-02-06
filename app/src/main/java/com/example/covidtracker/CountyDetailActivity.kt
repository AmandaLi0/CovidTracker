package com.example.covidtracker


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.covidtracker.databinding.ActivityCountyDetailBinding
import com.example.covidtracker.databinding.ActivityCountyListBinding

class CountyDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_COUNTY = "county"

    }

    private lateinit var binding: ActivityCountyDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountyDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hero = intent?.getParcelableExtra<County>(EXTRA_COUNTY)
        binding.textViewDetailCounty.text = county?.county
        binding.textViewDetailDate.text = county?.date
        binding.textViewDetailWeeklyCases.text = county?.metrics.weeklyNewCasesPer100K.toString()
        //binding.textViewDetailSuperpower.text = hero?.superpower
//        val heroName = ""
//        val heroDrawable = getDrawable(resources.getIdentifier(hero?.image, "drawable", packageName))
//        binding.imageViewDetailPicture.setImageDrawable(heroDrawable)
    }
}