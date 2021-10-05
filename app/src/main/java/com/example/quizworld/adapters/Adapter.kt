package com.example.quizworld.adapters

import android.content.Context

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import android.view.ViewGroup

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.quizworld.data.Category





class Adapter( private var modelList: List<Category>, val itemClickHandler: (Category) -> Unit) : RecyclerView.Adapter<Adapter.Viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {

        var view: View = LayoutInflater.from(parent.context).inflate(com.example.quizworld.R.layout.card_view, parent, false)


        return Viewholder(view)
    }


    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        var model: Category = modelList[position]
        holder.image.setImageResource(model.img)
        holder.category_name.text=model.category_name
        holder.category_desc.text=model.category_desc

        holder.itemView.setOnClickListener { itemClickHandler(model) }

    }

    override fun getItemCount(): Int {
        return modelList.size;
    }


    class Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var image: ImageView = itemView.findViewById(com.example.quizworld.R.id.imageView)
        var category_name: TextView = itemView.findViewById(com.example.quizworld.R.id.tv_title)
        var category_desc: TextView = itemView.findViewById(com.example.quizworld.R.id.tv_desc)






    }




}