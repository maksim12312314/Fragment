package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class itemFragment:Fragment( ){

    companion object{
        fun newItem(id:String) = itemFragment()
            //.apply {
            //arguments = Bundle().apply { putString("id", id) }
        //}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.roster_item, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val id = arguments?.getString("id")

        val id = arguments?.let{itemFragmentArgs.fromBundle(it).id}

        val view = getView()

        val description = view?.findViewById<TextView>(R.id.rosterDescription)
        val check = view?.findViewById<TextView>(R.id.rosterCheck)

        id?.let {
            val item = ToDoRepository.getItemById(id)
            description?.text= item?.description
            check?.text = item?.checked
        }
    }

}