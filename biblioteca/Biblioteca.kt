package biblioteca

import kotlin.math.min

open class Biblioteca(open var nombre: String, open var direccion: String, open var capacidadEstantes: Int = 50) {
    open var espacioDisponible: Int = capacidadEstantes
        get() = field
        set(value) {
            field = if (value >= 0) value else 0
        }

    open val tipo: String = "Biblioteca General"

    open var prestamos: Int = 0
        get() = field
        protected set(value) {
            field = value
        }

    init {
        println("Inicializando biblioteca: $nombre")
    }

    init {
        println("Capacidad inicial: $capacidadEstantes estantes con $espacioDisponible disponibles")
    }

    constructor(nombre: String, direccion: String, capacidadEstantes: Int, librosIniciales: Int) : this(nombre, direccion, capacidadEstantes) {
        val espacioOcupado = min(librosIniciales, capacidadEstantes)
        espacioDisponible = capacidadEstantes - espacioOcupado
        println("Se han añadido $espacioOcupado libros iniciales")
    }

    // Métodos
    open fun mostrarInfo() {
        println("=== $tipo: $nombre ===")
        println("Dirección: $direccion")
        println("Capacidad total: $capacidadEstantes estantes")
        println("Espacio disponible: $espacioDisponible estantes")
        println("Préstamos realizados: $prestamos")
    }

    fun agregarLibros(cantidad: Int) {
        if (cantidad <= espacioDisponible) {
            espacioDisponible -= cantidad
            println("Se han agregado $cantidad libros")
        } else {
            val agregados = espacioDisponible
            espacioDisponible = 0
            println("Espacio insuficiente. Solo se agregaron $agregados libros")
        }
    }

    fun registrarPrestamo() {
        prestamos++
        println("Préstamo registrado. Total actual: $prestamos")
    }
}

// Subclase que hereda de Biblioteca
class BibliotecaEspecializada(
    override var nombre: String,
    override var direccion: String,
    override var capacidadEstantes: Int,
    val especialidad: String
) : Biblioteca(nombre, direccion, capacidadEstantes) {

    // Propiedades sobrescritas
    override val tipo: String = "Biblioteca Especializada"
    override var espacioDisponible: Int = capacidadEstantes
        get() = super.espacioDisponible
        set(value) {
            field = if (value >= capacidadEstantes * 0.1) value else (capacidadEstantes * 0.1).toInt()
        }

    // Propiedad específica de la subclase
    var certificados: Int = 0

    // Método sobrescrito
    override fun mostrarInfo() {
        super.mostrarInfo()
        println("Especialidad: $especialidad")
        println("Certificados: $certificados")
        println("Nota: Se mantiene al menos el 10% de espacio reservado para obras de referencia")
    }

    // Método específico de la subclase
    fun obtenerCertificacion() {
        certificados++
        println("Nueva certificación obtenida en $especialidad. Total: $certificados")
    }
}

// Clase abstracta
abstract class Recurso {
    abstract val codigo: String
    abstract val titulo: String
    abstract val disponible: Boolean

    abstract fun prestar(): Boolean
    abstract fun devolver(): Boolean
}

// Interfaz
interface Catalogable {
    fun catalogar()
    fun buscarPorTitulo(titulo: String): Boolean
}

// Clases concretas que heredan de la clase abstracta e implementan la interfaz
class Libro(
    override val codigo: String,
    override val titulo: String,
    val autor: String,
    val paginas: Int
) : Recurso(), Catalogable {

    override var disponible: Boolean = true

    override fun prestar(): Boolean {
        return if (disponible) {
            disponible = false
            println("Libro '$titulo' prestado correctamente")
            true
        } else {
            println("El libro '$titulo' no está disponible")
            false
        }
    }

    override fun devolver(): Boolean {
        return if (!disponible) {
            disponible = true
            println("Libro '$titulo' devuelto correctamente")
            true
        } else {
            println("El libro '$titulo' ya estaba en la biblioteca")
            false
        }
    }

    override fun catalogar() {
        println("Catalogando libro: $titulo - $autor ($paginas páginas)")
    }

    override fun buscarPorTitulo(titulo: String): Boolean {
        val coincide = this.titulo.contains(titulo, ignoreCase = true)
        if (coincide) {
            println("Libro encontrado: $codigo - ${this.titulo} - $autor")
        }
        return coincide
    }
}

class Revista(
    override val codigo: String,
    override val titulo: String,
    val numero: Int,
    val mes: String,
    val año: Int
) : Recurso(), Catalogable {

    override var disponible: Boolean = true

    override fun prestar(): Boolean {
        return if (disponible) {
            disponible = false
            println("Revista '$titulo #$numero' prestada correctamente")
            true
        } else {
            println("La revista '$titulo #$numero' no está disponible")
            false
        }
    }

    override fun devolver(): Boolean {
        return if (!disponible) {
            disponible = true
            println("Revista '$titulo #$numero' devuelta correctamente")
            true
        } else {
            println("La revista '$titulo #$numero' ya estaba en la biblioteca")
            false
        }
    }

    override fun catalogar() {
        println("Catalogando revista: $titulo - Nº$numero ($mes $año)")
    }

    override fun buscarPorTitulo(titulo: String): Boolean {
        val coincide = this.titulo.contains(titulo, ignoreCase = true)
        if (coincide) {
            println("Revista encontrada: $codigo - ${this.titulo} #$numero ($mes $año)")
        }
        return coincide
    }
}