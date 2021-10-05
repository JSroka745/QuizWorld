package com.example.quizworld.ui.home

import android.graphics.drawable.AnimationDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.quizworld.R
import com.example.quizworld.databinding.StartFragmentBinding

class StartFragment : Fragment() {

    private lateinit var binding: StartFragmentBinding
    private lateinit var startviewModel: StartViewModel
    companion object {
        fun newInstance() = StartFragment()
    }

    private lateinit var viewModel: StartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.start_fragment,
            container,
            false
        )
        startviewModel= ViewModelProvider(this).get(StartViewModel::class.java)
        binding.startviewModel = startviewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val drawable = ContextCompat.getDrawable(requireActivity(), R.drawable.gradient_start)
        binding.startLayout.background=drawable
        val animationDrawable =  binding.startLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(1000)
        animationDrawable.setExitFadeDuration(1000)
        animationDrawable.start()
        binding.button4.setOnClickListener( View.OnClickListener {
            val transaction =requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.startLayout, GameFragment.newInstance())
            transaction.commit()

        })
        return binding.root
    }



}