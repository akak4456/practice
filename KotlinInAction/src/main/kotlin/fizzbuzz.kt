import java.lang.Exception
import java.util.TreeMap

fun main(args: Array<String>) {
   read()
}

fun read(): Int? {
    try {
        return 10
    }
    catch (e: Exception) {
        return null
    }
    finally {
        println("finally called")
    }
}