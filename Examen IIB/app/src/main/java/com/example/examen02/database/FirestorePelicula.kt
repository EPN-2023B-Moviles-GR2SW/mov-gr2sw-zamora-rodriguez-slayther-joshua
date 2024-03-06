package com.example.examen02.database

import com.example.examen02.modelo.Pelicula
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestorePelicula {
    companion object{
        fun crearPelicula(pelicula: Pelicula, idDirector: String){
            val db = Firebase.firestore
            val peliculas = db.collection("peliculas")

            val datosMateria = hashMapOf(
                "titulo" to pelicula.titulo,
                "genero" to pelicula.genero,
                "soloEnCines" to pelicula.soloEnCines,
                "idDirector" to idDirector
            )
            peliculas.document(pelicula.idIMDB).set(datosMateria)
        }

        fun consultarPeliculas(listener: (ArrayList<Pelicula>) -> Unit)
        {
            val db = Firebase.firestore
            val arregloPeliculas = arrayListOf<Pelicula>()
            val peliculasRefUnico = db.collection("peliculas")

            peliculasRefUnico
                .orderBy("titulo", Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener {querySnapshot ->
                    // it == eso (lo que llegue)
                    for (pelicula in querySnapshot){
                        pelicula.id
                        arregloPeliculas.add(anadirPelicula(pelicula))
                    }
                    listener(arregloPeliculas)
                }
                .addOnFailureListener{
                    // Errores
                }
        }


        fun anadirPelicula(
            pelicula: QueryDocumentSnapshot
        ) : Pelicula {
            val nuevaPelicula =  Pelicula(
                pelicula.id,
                pelicula.data.get("titulo") as String,
                pelicula.data.get("genero") as String,
                pelicula.data.get("soloEnCines") as Boolean,
                pelicula.data.get("idDirector") as String
            )
            return nuevaPelicula
        }

        fun consultarPelicula(
            imdbId: String,
            onSuccess: (Pelicula) -> Unit
        ) {
            val db = Firebase.firestore
            val peliculasRefUnica = db.collection("peliculas")
            peliculasRefUnica
                .document(imdbId)
                .get() // obtener 1 DOCUMENTO
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val document = task.result
                        if (document != null && document.exists()) {
                            val pelicula = Pelicula(
                                document.reference.id,
                                document.data?.get("titulo") as String,
                                document.data?.get("genero") as String,
                                document.data?.get("soloEnCines") as Boolean,
                                document.data?.get("idDirector") as String
                            )
                            onSuccess(pelicula)
                        } else {
                            //salio mal
                        }
                    } else {
                        //salio mal
                    }
                }
        }

        fun eliminarPelicula(
            imdbId: String
        ){
            val db = Firebase.firestore
            val peliculasRefUnica = db
                .collection("peliculas")

            peliculasRefUnica
                .document(imdbId)
                .delete()
                .addOnCompleteListener{ /* si todo sale bien */}
                .addOnFailureListener{/* Si algo salio mal*/}
        }

        fun actualizarPelicula(
            pelicula: Pelicula
        ){
            val db = Firebase.firestore
            val peliculasRefUnica = db
                .collection("peliculas")

            val datosActualizados = hashMapOf(
                "titulo" to pelicula.titulo,
                "genero" to pelicula.genero,
                "soloEnCines" to pelicula.soloEnCines
            )

            peliculasRefUnica
                .document(pelicula.idIMDB)
                .update(datosActualizados as Map<String, Any>)
                .addOnSuccessListener {
                    // Operación de actualización exitosa
                }
                .addOnFailureListener { e ->
                    // Manejar el error en caso de falla
                }
        }

        fun consultarPeliculasDirector(
            idDirector: String,
            listener: (ArrayList<Pelicula>) -> Unit
        ){
            var arregloPeliculas = arrayListOf<Pelicula>()
            val db = Firebase.firestore
            val peliculasRefUnica = db.collection("peliculas")
            peliculasRefUnica
                .whereEqualTo("idDirector", idDirector)
                .orderBy("nombre", Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (pelicula in querySnapshot){
                        pelicula.id
                        arregloPeliculas.add(anadirPelicula(pelicula))
                    }
                    listener(arregloPeliculas)
                }
                .addOnFailureListener{
                    // Errores
                }
        }
    }
}