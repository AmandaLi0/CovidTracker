package com.example.covidtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.databinding.ActivityCountyListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountyListActivity : AppCompatActivity() {
    companion object{
        const val TAG = "CountyListActivity"
        const val STATE = "CA"
    }

    private lateinit var binding: ActivityCountyListBinding
    lateinit var adapter: CountyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityCountyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCountyDataByStateApiCall(STATE)
    }

    private fun getCountyDataByStateApiCall(state: String) {
        var counties = listOf<CountyData>()
        //create an instance of Retrofit that has our base url
        //and the service interface that has out get resources
        val covidDataService = RetrofitHelper.getInstance().create(CovidDataService::class.java)
        //create our call object
        val countyDataCall = covidDataService.getCountyDataByState(state, Constants.APT_KEY)
        //execute our call object
        //new Callback<List<CountyData>> {  //method implementations   }
        countyDataCall.enqueue(object: Callback<List<CountyData>> {
            override fun onResponse(
                call: Call<List<CountyData>>,
                response: Response<List<CountyData>>
            ) {
                Log.d(TAG, "onResponse: ${response.body()}")
                if(response.body() != null){
                    adapter = CountyAdapter(response.body()!!)
                    adapter.dataSet =
                        adapter.dataSet.sortedWith(compareByDescending<CountyData> {it.cdcTransmissionLevel}
                            .thenByDescending { it.county })
                }
                else{
                    Log.d(TAG, "body is null :(")
                }
                //adapter = CountyAdapter(counties)
                binding.recyclerViewCountyListList.adapter = adapter
                binding.recyclerViewCountyListList.layoutManager = LinearLayoutManager(this@CountyListActivity)
            }

            //what we do if the call doesn't work
            override fun onFailure(call: Call<List<CountyData>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        //all of the code here ... we might not have data yet
        //can't put what to do with the data below the call
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.countylist_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.item_countylist_menu_sortByName -> {
                // sort by name
                adapter.dataSet = adapter.dataSet.sortedBy {
                    it.county
                }
                //adapter.dataSet.sortedWith(compareBy { it.name }, {it.ranking})
                adapter.notifyDataSetChanged()
                true
            }
            R.id.item_countylist_menu_sortByTransmissionLevel -> {
                // sort by rank
                adapter.dataSet = adapter.dataSet.sorted()
                adapter.notifyDataSetChanged()
                adapter.dataSet = adapter.dataSet.reversed()
                adapter.notifyDataSetChanged()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}