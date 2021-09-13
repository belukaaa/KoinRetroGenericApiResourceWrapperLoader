package com.leavingston.mapexample.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leavingston.mapexample.exceptions.ApiExceptions
import com.leavingston.mapexample.exceptions.NoInternetException
import com.leavingston.mapexample.models.Error
import com.leavingston.mapexample.models.ModelClass
import com.leavingston.mapexample.models.Resource
import com.leavingston.mapexample.repository.downloadDataRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class downloadDataViewModel(private val repo : downloadDataRepo) : ViewModel() {

    val oneTimeCode =  MutableLiveData<Resource<ArrayList<ModelClass>>>()

    private val _data = MutableLiveData<ArrayList<ModelClass>>()
    val data : LiveData<ArrayList<ModelClass>>
        get() = _data


    fun downloadData2(){

        val request = CoroutineScope(Dispatchers.IO).launch {
            try {

                val response = repo.getData()
                withContext(Main){


                    oneTimeCode.value = Resource(Resource.Status.SUCCESS , response , "")

                }



            }catch(e: Exception){
                when(e){
                    is NoInternetException ->{
                        withContext(Main){


                            oneTimeCode.value =  Resource(Resource.Status.NO_INTERNET, null, e.message)
                        }
                    }
                    is ApiExceptions -> {
                        withContext(Main){



                            if (e.message.equals(Error.ERROR_BLOCKED_USER)){

                                oneTimeCode.value =  Resource(Resource.Status.BLOCKED_USER, null, "")

                            }
                            else if (e.message.equals(Error.USER_DOES_NOT_EXIST)){

                                oneTimeCode.value = Resource(Resource.Status.USER_DOES_NOT_EXIST , null , "")
                            }

                            else{
                                oneTimeCode.value =  Resource(Resource.Status.ERROR, null, "")

                            }
                        }
                    }


                }
                e.printStackTrace()


            }

        }


    }



    fun downloadData(){
        val request = viewModelScope.launch {

            val response = repo.getData()

            _data.postValue(response)

        }
    }








}