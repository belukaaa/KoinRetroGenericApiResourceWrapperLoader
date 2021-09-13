package com.leavingston.mapexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.leavingston.mapexample.ViewModel.downloadDataViewModel
import com.leavingston.mapexample.databinding.ActivityMainBinding
import com.leavingston.mapexample.models.ModelClass
import com.leavingston.mapexample.models.Resource
import com.leavingston.mapexample.recycler.recyclerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


// ,OnMapReadyCallback
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter : recyclerAdapter

    private val viewModel by viewModel<downloadDataViewModel>()


//    private lateinit var mapSupport : SupportMapFragment
//    private lateinit var googleMap : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        adapter = recyclerAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

//        viewModel.data.observe(this , Observer {
//
//            adapter.setData(it)
//
//        })



            observeData()


        binding.downloadButton.setOnClickListener{
            if (adapter.itemCount == 0){
                binding.progressBar.visibility = View.VISIBLE

            }
            viewModel.downloadData2()
        }
        binding.clearButton.setOnClickListener {
            binding.status.text = "წავშალეთ"
            adapter.clearData()
        }

//        mapSupport = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
//
//        mapSupport.getMapAsync(this)




    }

    private fun observeData(){
        viewModel.oneTimeCode.observe(this,Observer{

            when(it.status){
                Resource.Status.SUCCESS -> {
                    Handler().postDelayed({
                        adapter.setData(it)
                        binding.status.text = "ჩაიტვირთა"
                        binding.progressBar.visibility = View.GONE

                    }, 2000)


                }

                Resource.Status.NO_INTERNET ->{
                    binding.status.text = "ინტერნეტი არ არის"

                    binding.progressBar.visibility = View.GONE

                    adapter.clearData()
                }
                Resource.Status.USER_DOES_NOT_EXIST -> {
                    binding.status.text = "User doesn't exist"
                    adapter.clearData()
                    binding.progressBar.visibility = View.GONE

                }
            }


        })
    }



















//    override fun onMapReady(p0: GoogleMap) {
//
//        this.googleMap = p0
//    }
}