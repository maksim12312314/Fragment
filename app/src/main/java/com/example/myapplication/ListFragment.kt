package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class OurListAdapter(val context: Context?, val items:List<ToDoModel>, val cb: (ToDoModel)->Unit ):RecyclerView.Adapter<OurListAdapter.OurListViewHolder>(){


    inner class OurListViewHolder(val view:View):RecyclerView.ViewHolder(view) {
        val check = view.findViewById<TextView>(R.id.rosterCheck)
        val description = view.findViewById<TextView>(R.id.rosterDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OurListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.roster_item, parent, false)
        val holder = OurListViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: OurListViewHolder, position: Int) {
        holder.check.text = items[position].checked
        holder.description.text = items[position].description
        holder.view.setOnClickListener{
            cb(items[position])
        }
    }

}


class ListFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.roster, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = ToDoRepository.getItems()

        val adapter = OurListAdapter(context , items,  ::goTo)

        val recycler = view.findViewById<RecyclerView>(R.id.roster)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)
    }

    fun goTo(item:ToDoModel){
        findNavController().navigate(ListFragmentDirections.actionListFragmentToItemFragment(item.id))
        //val tr = fragmentManager?.beginTransaction()
        // tr?.replace(android.R.id.content, itemFragment.newItem(item.id) )
        //tr?.addToBackStack(null)
        //tr?.commit()
    }

}
