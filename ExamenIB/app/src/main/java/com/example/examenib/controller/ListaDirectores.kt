package com.example.examenib.controller

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.example.examenib.R
import com.example.examenib.model.data.BaseDatos
import com.example.examenib.model.entities.Director

class ListaDirectores : ComponentActivity() {

    val data = BaseDatos.datos
    var positionDirectorSelected = -1
    lateinit var adapter: ArrayAdapter<Director>

    val callbackContenido =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode === RESULT_OK) {
                if (result.data != null) {
                    adapter.notifyDataSetChanged()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_directores)

        val listView = findViewById<ListView>(R.id.lvDirectores)

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            data
        )
        listView.adapter = adapter
        adapter.notifyDataSetChanged()

        val btnCrearDirector = findViewById<Button>(R.id.btnCrearDirector)
        btnCrearDirector.setOnClickListener {
            val intent = Intent(this, FormDirector::class.java)
            intent.putExtra("positionDirectorSelected", -1)
            callbackContenido.launch(intent)
        }
        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_director, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        positionDirectorSelected = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemEditarDirector -> {
                val intent = Intent(this, FormDirector::class.java)
                intent.putExtra("positionDirectorSelected", positionDirectorSelected)
                callbackContenido.launch(intent)
                true
            }

            R.id.itemEliminarDirector -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("¿Desear eliminar el director?")
                builder.setPositiveButton("Sí") { dialog, which ->
                    data.removeAt(positionDirectorSelected)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(
                        applicationContext,
                        "Director eliminado",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                builder.setNegativeButton("No") { dialog, which ->
                    Toast.makeText(
                        applicationContext,
                        "Operación cancelada",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                builder.create().show()
                true
            }

            R.id.itemPeliculas -> {
                val intent = Intent(this, ListaPeliculas::class.java)
                intent.putExtra("positionDirectorSelected", positionDirectorSelected)
                startActivity(intent)
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }
}