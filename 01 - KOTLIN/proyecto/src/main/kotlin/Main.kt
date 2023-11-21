import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    //Variables INMUTABLES (No se reasignan '=')
    val inmutable: String = "Slayther"

    //Mutables (Reasingnables)
    var mutable: String = "Slayther"
    mutable = "Joshua"


    //val > var
    //Tratar de usar val
    //Duck Typing
    var ejemploVariable = "Slayther"
    val ejemploEdad = 20
    ejemploVariable.trim()

    //Variables primitivas
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true
    //Clases Java
    val fechaNacimiento: Date = Date()


    //Estado Civil
    val estadoCivilWhen = 'C';
    when (estadoCivilWhen) {
        ('C') -> {
            println("Casado")
        }

        ('S') -> {
            println("Soltero")
        }

        else -> {
            println("No sabemos")
        }

    }

    //Condicionales
    val esSoltero = (estadoCivilWhen == 'S')
    val coqueteo = if (esSoltero) "Si" else "No"

    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00, 20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00)//Named Parameters
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 20.00)//Named Parameters

    val sumaUno = Suma(1, 1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null, null)
    println(sumaUno)
    println(sumaDos)
    println(sumaTres)
    println(sumaCuatro)
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSuma)


    //Arreglos estaticos
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    println(arregloEstatico)

    //Arreglos dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)


    //FOR EACH -> Unit
    val respuestaForEach: Unit = arregloDinamico
        .forEach { valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }

    arregloDinamico.forEach {
        println("Valor actual iterando forEach: ${it}")
    }

    arregloEstatico.forEachIndexed { indice: Int, actual: Int ->
        println("Valor actual: ${actual} - Indice: ${indice}")
    }

    println(respuestaForEach)


    //MAP -> Muta el arreglo (Cambia el arreglo)
    //1) Enviemos el nuevo valor de la iteracion
    //2) Nos devuelve es un NUEVO ARREGLO con los valores modificados
    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }

    println(respuestaMap)
    val respuestaMapaDos = arregloDinamico.map { it + 15.00 }

    //Filter -> FILTRAR EL ARREGLO
    //1) Devolver una expresion (TRUE o FALSE)
    //2) Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            return@filter valorActual > 5
        }

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }

    println(respuestaFilter)
    println(respuestaFilterDos)
}

//Void -> Unit (Kotlin)
fun imprimirNombre(nombre: String): Unit {
    println("Nombre: $nombre")//Template Strings
}

fun calcularSueldo(
    sueldo: Double //Requerido
    , tasa: Double = 12.00 //Opcional
    , bonoEspecial: Double? = null
)//Opcion null -> nullable
        : Double {
    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) + bonoEspecial
    }
}


abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(uno: Int, dos: Int) {
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

abstract class Numeros(//Constructor primario
    //Ejemplo:
    //uno: Int, (Parametro sin modificador de acceso)
    //private var dos: Int, (Parametro privado)
    //var uno: Int Propiedad de la clase por defecto publica
    protected var numeroUno: Int,
    protected var numeroDos: Int
) {
    init {
        println("Inicializando")
    }
}

class Suma(unoParametro: Int, dosParametro: Int) :
    Numeros(unoParametro, dosParametro) { //Extendiendo y mandando los paramtros (super)
    init {
        this.numeroUno
        this.numeroDos
    }

    constructor(uno: Int?, dos: Int) : this(if (uno == null) 0 else uno, dos)
    constructor(uno: Int, dos: Int?) : this(uno, if (dos == null) 0 else dos)
    constructor(uno: Int?, dos: Int?) : this(if (uno == null) 0 else uno, if (dos == null) 0 else dos)

    public fun sumar(): Int {
        val total: Int = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }

    companion object {
        //Atributos y metodos compartidos entre instancias (Estatico)
        val pi = 3.1416

        fun elevarAlCuadrado(numero: Int): Int {
            return numero * numero
        }

        val historialSuma = arrayListOf<Int>()

        fun agregarHistorial(valorNuevaSuma: Int) {
            historialSuma.add(valorNuevaSuma)
        }
    }
}