// Archivo: Main.kt
package biblioteca

fun main() {
    println("=== SISTEMA DE GESTIÓN DE BIBLIOTECA ===\n")

    // Crear instancia de Biblioteca usando el constructor principal
    val bibliotecaMunicipal = Biblioteca("Biblioteca Municipal", "Calle Principal 123", 200)
    bibliotecaMunicipal.mostrarInfo()

    println("\n--- Operaciones en Biblioteca Municipal ---")
    bibliotecaMunicipal.agregarLibros(50)
    bibliotecaMunicipal.registrarPrestamo()
    bibliotecaMunicipal.registrarPrestamo()
    bibliotecaMunicipal.mostrarInfo()

    // Crear instancia de Biblioteca usando el constructor secundario
    println("\n--- Creando nueva biblioteca con constructor secundario ---")
    val bibliotecaUniversitaria = Biblioteca("Biblioteca Universitaria", "Campus Norte", 300, 150)
    bibliotecaUniversitaria.mostrarInfo()

    // Crear instancia de una subclase
    println("\n--- Creando biblioteca especializada (subclase) ---")
    val bibliotecaMedica = BibliotecaEspecializada(
        "Biblioteca Médica Central",
        "Av. de la Salud 45",
        100,
        "Medicina"
    )

    bibliotecaMedica.mostrarInfo()
    bibliotecaMedica.obtenerCertificacion()
    bibliotecaMedica.agregarLibros(95) // Intentamos ocupar el 95% del espacio
    bibliotecaMedica.mostrarInfo()

    // Trabajar con la clase abstracta e interfaces
    println("\n--- Gestión de recursos (clases abstractas e interfaces) ---")
    gestionarRecursos()

    println("\n=== FIN DEL PROGRAMA ===")
}

fun gestionarRecursos() {
    // Crear instancias de clases que heredan de Recurso e implementan Catalogable
    val libro1 = Libro("L001", "Don Quijote de la Mancha", "Miguel de Cervantes", 863)
    val libro2 = Libro("L002", "Cien años de soledad", "Gabriel García Márquez", 471)
    val revista1 = Revista("R001", "National Geographic", 245, "Marzo", 2023)

    // Lista de recursos
    val recursos = arrayOf<Recurso>(libro1, libro2, revista1)

    // Lista de elementos catalogables
    val catalogables = arrayOf<Catalogable>(libro1, libro2, revista1)

    println("Catalogando todos los recursos:")
    for (item in catalogables) {
        item.catalogar()
    }

    println("\nBuscando recursos por título:")
    var encontrados = 0
    val terminoBusqueda = "de"
    for (item in catalogables) {
        if (item.buscarPorTitulo(terminoBusqueda)) {
            encontrados++
        }
    }
    println("Se encontraron $encontrados recursos con '$terminoBusqueda' en el título")

    println("\nRealizando préstamos:")
    libro1.prestar()
    revista1.prestar()
    libro1.prestar() // Intentar prestar un libro ya prestado

    println("\nRealizando devoluciones:")
    libro1.devolver()
    libro2.devolver() // Intentar devolver un libro que no estaba prestado
}