package com.school.coroutines.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.annotations.SerializedName
import com.school.coroutines.adapter.Adapter
import com.school.coroutines.util.Action
import com.school.coroutines.viewmodel.ActivityViewModel
import com.school.coroutines.util.State
import com.school.coroutines.databinding.ActivityMainBinding
import com.school.coroutines.databinding.ItemHolderBinding
import com.school.coroutines.model.Item

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<ActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = Adapter()
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            recyclerView.adapter = adapter
            viewModel.state.observe(this@MainActivity) { state ->
                when (state) {
                    State.Loading -> root.isRefreshing = true
                    is State.Loaded -> {
                        root.isRefreshing = false
                        adapter.submitList(state.content)
                    }
                }
            }
            root.setOnRefreshListener { viewModel.loadPosts() }
        }
    }


}
