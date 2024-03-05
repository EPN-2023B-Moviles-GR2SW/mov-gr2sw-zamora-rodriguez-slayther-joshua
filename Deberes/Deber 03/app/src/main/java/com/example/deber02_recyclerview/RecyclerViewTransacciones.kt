package com.example.deber02_recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewTransacciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_transacciones)
        val listaTransferencias = arrayListOf<transactions>()
        listaTransferencias
            .add(transactions(1, "Transferencia a  Zamora Rodríguez Slayther Joshua", 17.0,"13/02/2024",99.0))
        listaTransferencias
            .add(transactions(2, "Transferencia recibida", 5.0,"13/02/2024",116.0))
        listaTransferencias
            .add(transactions(4, "Transferencia a Yanez Castro Marcela Marilú", 99.0,"13/02/2024",111.0))
        listaTransferencias
            .add(transactions(5, "Transferencia recibida", 109.0,"13/02/2024",210.0))
        listaTransferencias
            .add(transactions(6, "Transferencia recibida", 67.0,"13/02/2024",101.0))
        listaTransferencias
            .add(transactions(7, "Transferencia recibida", 5.0,"13/02/2024",34.0))
        listaTransferencias
            .add(transactions(8, "Transferencia Zamora Rodríguez Roddy Steeven", 51.0,"13/02/2024",29.0))
        listaTransferencias
            .add(transactions(9, "Transferencia a Balda Rodríguez Heyde Milehissa", 100.0,"13/02/2024",80.0))
        listaTransferencias
            .add(transactions(10, "Transferencia recibida", 25.0,"13/02/2024",180.0))
        listaTransferencias
            .add(transactions(11, "Transferencia recibida", 5.0,"13/02/2024",155.0))
        listaTransferencias
            .add(transactions(12, "Deposito CNB", 150.0,"13/02/2024",5.0))

        val recyclerView = findViewById<RecyclerView>(R.id.rv_transactions)
        inicializarRecyclerView(listaTransferencias, recyclerView)
    }
    fun inicializarRecyclerView(
        lista: ArrayList<transactions>,
        recyclerView: RecyclerView
    ) {
        val adaptador = RecyclerViewAdaptadorTransacciones(
            this,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adaptador.notifyDataSetChanged()
    }
}