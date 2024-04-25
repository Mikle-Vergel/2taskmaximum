import java.net.URL

fun main() {
    val url = URL("https://ru.wikipedia.org/wiki/QWERTY")

    val htmlText = url.readText()

    val regex = """<a\s+(?:[^>]*?\s+)?href="([^"]*)"""".toRegex()
    val matches = regex.findAll(htmlText)

    val links = matches.map { it.groupValues[1] }.toList()

    links.forEach {
        println(it)
    }

}