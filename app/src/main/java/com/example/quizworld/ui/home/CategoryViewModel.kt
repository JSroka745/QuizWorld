package com.example.quizworld.ui.home
import androidx.lifecycle.ViewModel
import com.example.quizworld.data.Category


class CategoryViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    fun getDataForCategory() : MutableList<Category>{
        var list = mutableListOf<Category>()
        list.add(Category("Animals", "lot of animals",com.example.quizworld.R.drawable.animal,"27"))
        list.add(Category("Art", "lot of painting etc",com.example.quizworld.R.drawable.art,"25"))
        list.add(Category("Mythology", "lot of Posejdon",com.example.quizworld.R.drawable.myth,"20"))
        list.add(Category("Politics", "lot of Trump",com.example.quizworld.R.drawable.politics,"24"))
        list.add(Category("Sports", "lot of Usain Bolt",com.example.quizworld.R.drawable.sport,"21"))
        list.add(Category("Random", "Random things",com.example.quizworld.R.drawable.random,"0"))

        return list
    }


}


