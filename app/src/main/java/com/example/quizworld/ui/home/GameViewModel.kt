package com.example.quizworld.ui.home

import android.graphics.Color
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizworld.R
import com.example.quizworld.data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GameViewModel : ViewModel() {
    // TODO: Implement the ViewModel
   lateinit  var questionListData :MutableLiveData<Result>
    private lateinit var questionList: MutableList<Result>
    private var _data= MutableLiveData<Result>()
    var actual_correct: String
    private var score= MutableLiveData<Int>()
    private var _endgame= MutableLiveData<Int>()
    private var progress=MutableLiveData<Int>()
    private var pos_of_correct_answer:Int


    init {
        questionListData= MutableLiveData()
        questionList= mutableListOf<Result>()
        actual_correct= ""
        pos_of_correct_answer=0
        progress.value=35
        score.value=0
        _endgame.value=0

    }

    val actualProgress: LiveData<Int>
    get() = progress

    val actualScore: LiveData<Int>
    get() = score

    val endGame: LiveData<Int>
        get() = _endgame


    val actualData: LiveData<Result>
        get() =_data


    fun get_points() : Int{
        if(_data.value?.difficulty =="hard")
        {
            return 3
        }

        else if(_data.value?.difficulty=="medium")
        {
            return 2
        }
        else{
            return 1
        }
    }

    private fun onGameFinish(){
        _endgame.value=1
    }

     fun nextQuestion(points: Int) {
        if (questionList.isEmpty()) {
            onGameFinish()

        } else {
            //Select and remove a _word from the list
            score.value= score.value?.plus(points)
            var x:Result=questionList.removeAt(0)
            Log.i("pis","lvl: "+x.difficulty+" answer: "+x.correct_answer)
            actual_correct=x.correct_answer
         _data.value=x

        }
    }


    fun makeAPICall(input:String){
       val retroInstance= RetroInstance.getRetroInstance().create(RetroService::class.java)
       val call=retroInstance.getDataFromAPI(input)

        call.enqueue(object : Callback<data>{
            override fun onFailure(call: Call<data>, t: Throwable) {
                questionListData.postValue(null)

            }

            override fun onResponse(call: Call<data>, response: Response<data>) {
               if(response.isSuccessful){
                   val responsebody=response.body()
                   if (responsebody != null) {

                           Log.i("pis", "response code: "+responsebody.response_code.toString())

                       for(data in responsebody.results)
                       {
                           questionList.add(data)
                           progress.value= progress.value?.plus(10)

                       }

                       var x:Result=questionList.removeAt(0)
                       Log.i("pis","lvl: "+x.difficulty+" answer: "+x.correct_answer)
                       actual_correct=x.correct_answer
                       _data.value=x

                   }

               }
                else{
                    questionListData.postValue(null)
                }
            }


        })

    }



}

