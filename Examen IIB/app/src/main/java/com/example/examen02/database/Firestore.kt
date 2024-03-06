package com.example.examen02.database

import com.example.examen02.modelo.Director
import com.example.examen02.modelo.Pelicula
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Firestore {

    companion object{
        fun crearDirector(director: Director){
            val db = Firebase.firestore
            val directores = db.collection("directores")

            val datosDirector = hashMapOf(
                "nombre" to director.nombre,
                "apellido" to director.apellido,
                "nacionalidad" to director.nacionalidad,
            )
            directores.document(director.id).set(datosDirector)
        }

        fun consultarPrefesores( listener: (ArrayList<Director>) -> Unit)
        {
            val db = Firebase.firestore
            val arregloDirectores = arrayListOf<Director>()
            val directoresRefUnico = db.collection("directores")

            directoresRefUnico
                .orderBy("nombre", Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener {querySnapshot ->
                    // it == eso (lo que llegue)
                    for (director in querySnapshot){
                        director.id
                        arregloDirectores.add(anadirDirector(director))
                    }
                    listener(arregloDirectores)
                }
                .addOnFailureListener{
                    // Errores
                }
        }


        fun anadirDirector(
            director: QueryDocumentSnapshot
        ) : Director {
            val nuevoDirector =  Director(
                director.id,
                director.data.get("nombre") as String,
                director.data.get("apellido") as String,
                director.data.get("nacionalidad") as String,
                director.data.get("peliculas") as ArrayList<Pelicula>?,
            )
            return nuevoDirector
        }

        fun consultarDirector(
            id: String,
            listener: (Director) -> Unit,
        ) {
            val db = Firebase.firestore
            val directoresRefUnica = db.collection("directores")
            directoresRefUnica
                .document(id)
                .get() // obtener 1 DOCUMENTO
                .addOnSuccessListener { querySnapshot ->
                    val document = querySnapshot
                    val director = Director(
                        document.reference.id,
                        document.data?.get("nombre") as String,
                        document.data?.get("apellido") as String,
                        document.data?.get("nacionalidad") as String,)
                    listener(director)
                }
                .addOnFailureListener{
                    // Errores
                }
        }

        fun eliminarDirector(
            id: String
        ){
            val db = Firebase.firestore
            val directoresRefUnica = db
                .collection("directores")

            directoresRefUnica
                .document(id)
                .delete()
                .addOnCompleteListener{ /* si todo sale bien */}
                .addOnFailureListener{/* Si algo salio mal*/}
        }

        fun actualizarDirector(
            director: Director
        ){
            val db = Firebase.firestore
            val directoresRefUnica = db
                .collection("directores")


            val datosActualizados = hashMapOf(
                "nombre" to director.nombre,
                "apellido" to director.apellido,
                "nacionalidad" to director.nacionalidad,
                "materias" to listOf("west_coast", "norcal"),
            )

            directoresRefUnica
                .document(director.id)
                .update(datosActualizados)
                .addOnSuccessListener {
                    // Operación de actualización exitosa
                }
                .addOnFailureListener { e ->
                    // Manejar el error en caso de falla
                }
        }
    }
}