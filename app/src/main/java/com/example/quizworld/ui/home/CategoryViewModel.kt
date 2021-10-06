package com.example.quizworld.ui.home
import androidx.lifecycle.ViewModel
import com.example.quizworld.data.Category


class CategoryViewModel : ViewModel() {
    fun getDataForCategory() : MutableList<Category>{
        var list = mutableListOf<Category>()
        list.add(Category("Animals", "Animals seem to sense your mood",com.example.quizworld.R.drawable.animal,"27"))
        list.add(Category("Art", "Entertainment isn't always art, but art is always entertaining",com.example.quizworld.R.drawable.art,"25"))
        list.add(Category("Books", " Books and friends should be few but good",com.example.quizworld.R.drawable.books,"10"))
        list.add(Category("Films", "The best films are those which transcend national or cultural barriers",com.example.quizworld.R.drawable.film,"11"))
        list.add(Category("Music", "Music is the eye of the ear",com.example.quizworld.R.drawable.music,"12"))
        list.add(Category("Video Games", "Fus Ro Dah!",com.example.quizworld.R.drawable.games,"15"))
        list.add(Category("Mythology", "It's just a piece of popular mythology that people always get sacked when they are away",com.example.quizworld.R.drawable.myth,"20"))
        list.add(Category("Politics", "What is democracy?",com.example.quizworld.R.drawable.politics,"24"))
        list.add(Category("Sports", "Faster, Stronger, Higher, Better",com.example.quizworld.R.drawable.sport,"21"))
        list.add(Category("Random", "Random questions",com.example.quizworld.R.drawable.random,"0"))


        return list
    }


}


