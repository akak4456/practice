fun main(args: Array<String>) {
    val sb = StringBuilder("Kotlin?")
    println(sb.lastChar)
    sb.lastChar = '!'
    println(sb)
}

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }