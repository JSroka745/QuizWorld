package com.example.quizworld.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizworld.R
import com.example.quizworld.adapters.Adapter
import com.example.quizworld.data.Category
import com.example.quizworld.databinding.CategoryFragmentBinding

class CategoryFragment : Fragment() {

    lateinit var binding : CategoryFragmentBinding
    private lateinit var viewModel: CategoryViewModel
    lateinit var data_list : MutableList<Category>
    companion object {
        fun newInstance() = CategoryFragment()
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.category_fragment,container,false)
        viewModel=CategoryViewModel()
        binding.viewModel=viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        data_list=viewModel.getDataForCategory()
        var adapter =Adapter(data_list,this@CategoryFragment::onItemClickHandler)
        binding.RecyclerCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)
        binding.RecyclerCategory.adapter=adapter
        adapter.itemClickHandler
        adapter.notifyDataSetChanged()



        return binding.root
    }

    private fun onItemClickHandler(cat:Category){


        var category= cat.category_id
        var bundle= Bundle()
        bundle.putString("category",category)
        val frag =GameFragment.newInstance()
        this.viewModelStore.clear()
        frag.arguments=bundle
        val transaction =requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.category_layout, frag)
        transaction.commit()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}