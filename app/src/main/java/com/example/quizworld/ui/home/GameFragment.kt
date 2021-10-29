package com.example.quizworld.ui.home

import android.content.ContentProvider
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.quizworld.R
import com.example.quizworld.data.Result
import com.example.quizworld.databinding.GameFragmentBinding

class GameFragment() : Fragment() {

    private lateinit var binding: GameFragmentBinding
    private lateinit var viewModel: GameViewModel
    private val handler = Handler()
    private val lista= mutableListOf<Button>()
    companion object {
        fun newInstance() = GameFragment()
    }



    private fun make_layout_visible(){
        binding.button3.visibility=View.VISIBLE
        binding.button.visibility=View.VISIBLE
        binding.button2.visibility=View.VISIBLE
        binding.button5.visibility=View.VISIBLE
        binding.textView.visibility=View.VISIBLE
        binding.cardViewExample.visibility=View.VISIBLE
    }

    private fun make_layout_invisible(){
        binding.button3.visibility=View.INVISIBLE
        binding.button.visibility=View.INVISIBLE
        binding.button2.visibility=View.INVISIBLE
        binding.button5.visibility=View.INVISIBLE
        binding.textView.visibility=View.INVISIBLE
        binding.cardViewExample.visibility=View.INVISIBLE
        binding.cardEndGame.visibility=View.INVISIBLE
    }

    private fun check_multiple_or_boolean(it: Result){
        if(it.type.contains("multiple"))
        {
            val y= mutableListOf<String>()
            y.addAll(it.incorrect_answers)
            y.add(it.correct_answer)
            y.shuffle()
            binding.button.text=Html.fromHtml(y[0],HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
            binding.button2.text=Html.fromHtml(y[1],HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
            binding.button3.text=Html.fromHtml(y[2], HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
            binding.button5.text=Html.fromHtml(y[3],HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
        }

        else{
            val y= mutableListOf<String>()
            y.addAll(it.incorrect_answers)
            y.add(it.correct_answer)
            y.shuffle()
            binding.button3.visibility=View.INVISIBLE
            binding.button5.visibility=View.INVISIBLE
            binding.button.text=Html.fromHtml(y[0],HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
            binding.button2.text=Html.fromHtml(y[1],HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
            binding.button3.visibility=View.GONE
            binding.button5.visibility=View.GONE
        }
    }




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        container?.removeAllViews()

        var text_for_api= arguments?.getString("category")

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.game_fragment,
            container,
            false
        )
        Log.i("GameFragment", "Called ViewModelProvider.get")

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        if(text_for_api=="0")
        {
            viewModel.makeAPICall("https://opentdb.com/api.php?amount=10")
        }

        else{
            viewModel.makeAPICall("https://opentdb.com/api.php?amount=10&category=$text_for_api")
        }




        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner
        make_layout_invisible()


        binding.buttonAgain.setOnClickListener {
            var bundle= Bundle()
            binding.frameLayout.setBackgroundColor(resources.getColor(R.color.white))
            bundle.putString("category",text_for_api)
            var frag =newInstance()
            frag.arguments=bundle
            this.viewModelStore.clear()
            var transaction =requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, frag)
            transaction.commit()
        }

        binding.buttonBack.setOnClickListener {

            this.viewModelStore.clear()
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent)







        }

        viewModel.endGame.observe(viewLifecycleOwner, Observer {
            if(it==1)
            {
                make_layout_invisible()
                binding.textViewPoints.visibility=View.VISIBLE
                binding.cardEndGame.visibility=View.VISIBLE

            }
        })



                viewModel.actualProgress.observe(viewLifecycleOwner, Observer {
                    if(it>=100)
                    {
                        binding.imageView2Loading.visibility=View.GONE

                        binding.frameLayout.setBackgroundColor(resources.getColor(R.color.dark_yellow))
                        make_layout_visible()
                    }


                })


        viewModel.actualData.observe(viewLifecycleOwner, Observer {
            // binding.button.text=it
            binding.textView.text=Html.fromHtml(it.question,HtmlCompat.FROM_HTML_MODE_LEGACY).toString()




            if(it.difficulty=="easy")
            {

                val drawable = ContextCompat.getDrawable(requireActivity(), R.drawable.gradient_easy)

             //   binding.textView.setBackgroundColor(resources.getColor(R.color.lighter_blue))
                make_layout_invisible()
                binding.textViewDificult.text="EASY\nQUESTION\n\n1 POINT"
                binding.textViewDificult.visibility=View.VISIBLE
                binding.textViewDificult.background=drawable
                val animationDrawable =  binding.textViewDificult.background as AnimationDrawable
                animationDrawable.setEnterFadeDuration(1000)
                animationDrawable.setExitFadeDuration(1000)
                animationDrawable.start()
                handler.postDelayed({
                    binding.textViewDificult.visibility=View.INVISIBLE
                    make_layout_visible()
                    check_multiple_or_boolean(it)


                }, 2500)




            }

            else if(it.difficulty=="medium")
            {

                val drawable = ContextCompat.getDrawable(requireActivity(), R.drawable.gradient_medium)

               // binding.textView.setBackgroundColor(resources.getColor(R.color.light_blue))
                make_layout_invisible()

                binding.textViewDificult.text="MEDIUM\nQUESTION\n\n2 POINTS"
                binding.textViewDificult.visibility=View.VISIBLE
                binding.textViewDificult.background=drawable
                val animationDrawable =  binding.textViewDificult.background as AnimationDrawable
                animationDrawable.setEnterFadeDuration(1000)
                animationDrawable.setExitFadeDuration(1000)
                animationDrawable.start()
                handler.postDelayed({

                    binding.textViewDificult.visibility=View.INVISIBLE
                    make_layout_visible()
                    check_multiple_or_boolean(it)


                }, 2500)

            }

            else if(it.difficulty=="hard")
            {


                val drawable = ContextCompat.getDrawable(requireActivity(), R.drawable.gradient_hard)
               // binding.textView.setBackgroundColor(resources.getColor(R.color.light_blue))
                make_layout_invisible()
                binding.textViewDificult.visibility=View.VISIBLE
                binding.textViewDificult.text="HARD\nQUESTION\n\n3 POINTS"
                binding.textViewDificult.background=drawable
                val animationDrawable =  binding.textViewDificult.background as AnimationDrawable
                animationDrawable.setEnterFadeDuration(1000)
                animationDrawable.setExitFadeDuration(1000)
                animationDrawable.start()
                handler.postDelayed({


                    binding.textViewDificult.visibility=View.INVISIBLE
                    make_layout_visible()
                    check_multiple_or_boolean(it)

                }, 2500)

            }

        })



        lista.add(binding.button)
        lista.add(binding.button2)
        lista.add(binding.button3)
        lista.add(binding.button5)

        for(x: Button in lista)
        {
            x.setOnClickListener( View.OnClickListener {
               var help : Button= it as Button
                val string:String= help.text as String
                if(string==viewModel.actual_correct)
                {

                    it.setBackgroundColor(resources.getColor(android.R.color.holo_green_dark))
                    setButtonsNotClickable()
                    handler.postDelayed({ viewModel.nextQuestion(viewModel.get_points())
                        it.setBackgroundColor( resources.getColor(R.color.light_yellow))
                        setButtonsClickable()
                    }, 2000)
                }

                else
                {
                    it.setBackgroundColor(resources.getColor(android.R.color.holo_red_dark))
                    setButtonsNotClickable()
                    var btn=getButtonWithCorrectAnswer()
                    handler.postDelayed({
                       btn.setBackgroundColor(resources.getColor(android.R.color.holo_green_dark))
                    }, 1000)

                    handler.postDelayed({ viewModel.nextQuestion(0)
                        it.setBackgroundColor( resources.getColor(R.color.light_yellow))
                        btn.setBackgroundColor( resources.getColor(R.color.light_yellow))
                        setButtonsClickable()
                    }, 3000)
                }

            })

        }


        return binding.root

    }

    fun setButtonsNotClickable(){
        binding.button.isClickable=false
        binding.button2.isClickable=false
        binding.button3.isClickable=false
        binding.button5.isClickable=false

    }

    fun setButtonsClickable(){
        binding.button.isClickable=true
        binding.button2.isClickable=true
        binding.button3.isClickable=true
        binding.button5.isClickable=true

    }

    fun getButtonWithCorrectAnswer() : Button{
        if(binding.button.text==viewModel.actual_correct)
        {
            return binding.button
        }

        else if(binding.button2.text==viewModel.actual_correct)
        {
            return binding.button2
        }

        else if(binding.button3.text==viewModel.actual_correct)
        {
            return binding.button3
        }

        else
        {
            return binding.button5
        }

    }



}