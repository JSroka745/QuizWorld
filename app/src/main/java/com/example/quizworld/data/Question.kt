package com.example.quizworld.data

data class Question(val quest: String, val trueAnswer:String, val answer1:String, val answer2:String, val answer3:String){


}


data class QuestionList(val items:ArrayList<Question>)