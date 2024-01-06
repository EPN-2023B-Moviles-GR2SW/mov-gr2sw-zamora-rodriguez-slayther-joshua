import model.dao.implementaciones.DirectorDAO
import model.entities.Director
import model.entities.Pelicula
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun main() {

    val directorDAO = DirectorDAO
    var opcion: Int

    do {
        opcion = showMenuDirectores()
        when (opcion) {
            1 -> {
                val director = leerDirector()
                if (director != null) {
                    directorDAO.save(director)
                }
            }

            2 -> {
                if (directorDAO.getAll().isEmpty()) {
                    println("No hay directores registrados.")
                } else {
                    showDirectores(directorDAO.getAll())
                    print("Escoja un director: ")
                    val director: Director
                    try {
                        val numeroDirector = readln().toInt()
                        director = directorDAO.getAll()[numeroDirector - 1]
                        println("Informacion del director:  $director")
                    } catch (nfe: NumberFormatException) {
                        println("Opcion no valida. Solo se aceptan numeros enteros")
                        continue
                    } catch (index: IndexOutOfBoundsException) {
                        println("Opcion no valida. No existe el director")
                        continue
                    } catch (e: Exception) {
                        println("Opcion no valida. Error desconocido")
                        continue
                    }
                    var opcionPelicula: Int
                    do {
                        opcionPelicula = showMenuPeliculas()
                        when (opcionPelicula) {
                            1 -> {
                                val pelicula = leerPelicula()
                                if (pelicula != null) {
                                    director.peliculas.add(pelicula)
                                    directorDAO.update(director.id, director)
                                }
                            }

                            2 -> {
                                showPeliculasByDirector(director)
                            }

                            3 -> {
                                if (director.peliculas.isEmpty()) {
                                    println("No hay peliculas registradas.")
                                } else {
                                    showPeliculasByDirector(director)
                                    print("Ingrese el numero de la pelicula a actualizar:  ")
                                    try {
                                        val numeroPelicula = readln().toInt()
                                        val peliculaAntigua = director.peliculas[numeroPelicula - 1]
                                        println("Informacion actual de la pelicula:  $peliculaAntigua")
                                        val peliculaNueva = leerPelicula()
                                        if (peliculaNueva != null) {
                                            director.peliculas[numeroPelicula - 1] = peliculaNueva
                                            directorDAO.update(director.id, director)
                                            println("Se ha actualizado la pelicula ${peliculaNueva.titulo} correctamente")
                                        }
                                    } catch (nfe: NumberFormatException) {
                                        println("Opcion no valida. Solo se aceptan numeros enteros")
                                        continue
                                    } catch (index: IndexOutOfBoundsException) {
                                        println("Opcion no valida. No existe la pelicula")
                                        continue
                                    } catch (e: Exception) {
                                        println("Opcion no valida. Error desconocido")
                                        continue
                                    }
                                }
                            }

                            4 -> {
                                if (director.peliculas.isEmpty()) {
                                    println("No hay peliculas registradas.")
                                } else {
                                    showPeliculasByDirector(director)
                                    print("Ingrese el numero de la pelicula a eliminar:  ")
                                    try{
                                        val numeroPelicula = readln().toInt()
                                        director.peliculas.removeAt(numeroPelicula - 1)
                                        directorDAO.update(director.id, director)
                                        println("Se ha eliminado la pelicula correctamente")
                                    } catch (nfe: NumberFormatException) {
                                        println("Opcion no valida. Solo se aceptan numeros enteros")
                                        continue
                                    } catch (index: IndexOutOfBoundsException) {
                                        println("Opcion no valida. No existe la pelicula")
                                        continue
                                    } catch (e: Exception) {
                                        println("Opcion no valida. Error desconocido")
                                        continue
                                    }
                                }
                            }

                            5 -> {
                                break
                            }

                            else -> {
                                println("Opcion no valida")
                            }
                        }
                    } while (opcionPelicula != 5)
                }

            }

            3 -> {
                if (directorDAO.getAll().isEmpty()) {
                    println("No hay directores registrados.")
                } else {
                    showDirectores(directorDAO.getAll())
                    print("Ingrese el numero del director a actualizar:  ")
                    try{
                        val numeroDirector = readln().toInt()
                        val directorAntiguo = directorDAO.getAll()[numeroDirector - 1]
                        println("Informacion actual del director:  $directorAntiguo")
                        val directorNuevo = leerDirector()
                        if (directorNuevo != null) {
                            directorDAO.update(directorAntiguo.id, directorNuevo)
                            println("Se ha actualizado el director ${directorNuevo.nombre} correctamente")
                        }
                    } catch (nfe: NumberFormatException) {
                        println("Opcion no valida. Solo se aceptan numeros enteros")
                        continue
                    } catch (index: IndexOutOfBoundsException) {
                        println("Opcion no valida. No existe el director")
                        continue
                    } catch (e: Exception) {
                        println("Opcion no valida. Error desconocido")
                        continue
                    }
                }
            }

            4 -> {
                if (directorDAO.getAll().isEmpty()) {
                    println("No hay directores registrados.")
                } else {
                    showDirectores(directorDAO.getAll())
                    print("Ingrese el numero del director a eliminar:  ")
                    try{
                        val numeroDirector = readln().toInt()
                        val director = directorDAO.getAll()[numeroDirector - 1]
                        directorDAO.deleteById(director.id)
                    } catch (nfe: NumberFormatException) {
                        println("Opcion no valida. Solo se aceptan numeros enteros")
                        continue
                    } catch (index: IndexOutOfBoundsException) {
                        println("Opcion no valida. No existe el director")
                        continue
                    } catch (e: Exception) {
                        println("Opcion no valida. Error desconocido")
                        continue
                    }
                }
            }

            5 -> {
                println("Gracias por usar el programa")
                break
            }

            else -> println("Opcion no valida")
        }
    } while (opcion != 5)

}

private fun convertirFecha(fecha: String): Date {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    return simpleDateFormat.parse(fecha)
}

private fun showMenuDirectores(): Int {
    return try {
        println("-----------------------------------------")
        println("¿Que accion desea realizar?")
        println("-----------------------------------------")
        println("1 - Insertar nuevo Director")
        println("2 - Ver lista de directores")
        println("3 - Actualizar director")
        println("4 - Eliminar director")
        println("5 - Salir")
        print("Ingrese el numero de la opcion:  ")
        readln().toInt()
    } catch (nfe: NumberFormatException) {
        0
    } catch (e: Exception) {
        0
    }
}

private fun showMenuPeliculas(): Int {
    return try {
        println("-----------------------------------------")
        println("¿Que accion desea realizar?")
        println("-----------------------------------------")
        println("1 - Insertar nueva Pelicula")
        println("2 - Ver lista de Peliculas")
        println("3 - Actualizar Pelicula")
        println("4 - Eliminar Pelicula")
        println("5 - Salir")
        print("Ingrese el numero de la opcion:  ")
        readln().toInt()
    } catch (nfe: NumberFormatException) {
        0
    } catch (e: Exception) {
        0
    }
}

private fun showDirectores(directores: List<Director>) {
    if (directores.isEmpty()) {
        println("No hay directores registrados.")
        return
    }
    for (director in directores.withIndex()) {
        println("${director.index + 1} - ${director.value.nombre} ${director.value.apellido}")
    }
}

private fun showPeliculasByDirector(director: Director) {
    if (director.peliculas.isEmpty()) {
        println("No hay peliculas registradas.")
        return
    }
    for (pelicula in director.peliculas.withIndex()) {
        println("${pelicula.index + 1} - ${pelicula.value.titulo}")
    }
}

private fun leerDirector(): Director? {
    try {
        print("Ingrese el nombre del director:  ")
        val nombre = readlnOrNull()
        print("Ingrese el apellido del director:  ")
        val apellido = readlnOrNull()
        print("Ingrese la nacionalidad del director:  ")
        val nacionalidad = readlnOrNull()
        print("Ingrese la fecha de nacimiento del director(dd/MM/yyyy):  ")
        val fechaNacimiento = convertirFecha(readln())
        return Director(nombre!!, apellido!!, nacionalidad!!, fechaNacimiento)
    } catch (parse: ParseException) {
        println("Formato de fecha incorrecto. Debe ser dd/MM/yyyy")
    } catch (e: Exception) {
        println("Opcion no valida. Error desconocido")
    }
    return null
}

private fun leerPelicula(): Pelicula? {
    try {
        print("Ingrese el nombre de la pelicula:  ")
        val titulo = readlnOrNull()
        print("Ingrese el genero de la pelicula:  ")
        val genero = readlnOrNull()
        print("Ingrese la fecha de estreno de la pelicula(dd/MM/yyyy):  ")
        val fechaEstreno = convertirFecha(readln())
        print("La pelicula se puede ver solo en cines?(True|False):  ")
        val soloEnCines = readlnOrNull()?.toBoolean()
        print("Ingrese el costo de la pelicula:  ")
        val costo = readlnOrNull()?.toDouble()
        return Pelicula(titulo!!, genero!!, fechaEstreno, soloEnCines!!, costo!!)
    } catch (parse: ParseException) {
        println("Formato de fecha incorrecto. Debe ser dd/MM/yyyy")
    } catch (nfe: NumberFormatException) {
        println("Opcion no valida. Solo se aceptan numeros")
    } catch (e: Exception) {
        println("Opcion no valida. Error desconocido")
    }
    return null
}