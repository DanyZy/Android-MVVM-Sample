package com.aim.leantechtest.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aim.leantechtest.R
import com.aim.leantechtest.data.api.ApiGithubUsersService
import com.aim.leantechtest.data.api.ApiInstance
import com.aim.leantechtest.data.model.User
import com.aim.leantechtest.ui.ViewModelFactory
import com.aim.leantechtest.ui.details.view.DetailsActivity
import com.aim.leantechtest.ui.main.adapter.UserListAdapter
import com.aim.leantechtest.ui.main.viewmodel.MainViewModel
import com.aim.leantechtest.utils.Status
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserListAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : UserListAdapter.OnItemClickListener {
            override fun onItemClick(user: User) {
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra("USER_ICON", user.avatar)
                intent.putExtra("USER_NAME", user.name)
                startActivity(intent)
            }
        })
    }

    private fun setupObserver() {
        mainViewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiInstance(ApiGithubUsersService()))
        ).get(MainViewModel::class.java)
    }
}
