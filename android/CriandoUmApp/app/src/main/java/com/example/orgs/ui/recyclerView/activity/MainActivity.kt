package com.example.orgs.ui.recyclerView.activity
import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.model.Produto.Produto
import com.example.orgs.ui.recyclerView.adapter.ListaProdutosAdapter

class MainActivity:  Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ListaProdutosAdapter(context = this, produtos = listOf(
            Produto("Iogute", "leite, maçã e açucar", 10.99.toBigDecimal())
        ))
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}