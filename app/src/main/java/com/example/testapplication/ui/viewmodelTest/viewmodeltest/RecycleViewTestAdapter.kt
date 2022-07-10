package com.example.testapplication.ui.viewmodelTest.viewmodeltest

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.data.Test
import com.example.testapplication.databinding.RecyclerviewtestBinding
import com.example.testapplication.ui.viewmodelTest.viewmodeltest.RecycleViewTestAdapter.*

class RecycleViewTestAdapter(private val list : MutableList<Test>)
    : RecyclerView.Adapter<MyViewHolder>() {

    private val selecItems : SparseBooleanArray = SparseBooleanArray()

    private var positions : Int = -1

    inner class MyViewHolder(val binding : RecyclerviewtestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(c : Test){
            binding.data = c

            itemView.setOnClickListener {
                if(positions != layoutPosition){
                    positions = layoutPosition
                    binding.textView4.text = positions.toString()
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerviewtestBinding.inflate(LayoutInflater.from(parent.context),parent,false)



        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])

    }


    override fun getItemCount(): Int {
        return list.size
    }


}
object MyBindingAdapter{

    @BindingAdapter("items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, items : ArrayList<Test>){
        if(recyclerView.adapter == null) {
            val adapter = RecycleViewTestAdapter(items)
            adapter.setHasStableIds(true)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        }

        val myAdapter = recyclerView.adapter as RecycleViewTestAdapter

        //myAdapter.list = items
        myAdapter.notifyDataSetChanged()
    }
}