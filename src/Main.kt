import java.net.URL

fun search(ur: String): List<String> {
    val url = URL(ur)

    val htmlText = url.readText()

    val regex = """<a\s+(?:[^>]*?\s+)?href="([^"]*)"""".toRegex()
    val matches = regex.findAll(htmlText)

    val links = matches.map { it.groupValues[1] }.toList()
    return links
}



fun way(startPage: String, endPage: String): List<String>? {
    val visited = mutableSetOf<String>()
    val queue = ArrayDeque<Pair<String, List<String>>>()

    queue.add(Pair(startPage, listOf(startPage)))
    visited.add(startPage)

    while (queue.isNotEmpty()) {
        val (currentPage, path) = queue.removeFirst()

        if (currentPage == endPage) {
            return path
        }


        val links = search(currentPage)

        for (link in links) {
            if (link !in visited) {
                visited.add(link)
                queue.add(Pair(link, path + link))
            }
        }
    }

    return null
}


fun main() {
    val startPage = "https://ru.wikipedia.org/wiki/QWERTY"
    val endPage = "https://en.wikipedia.org/wiki/Kotlin_(programming_language)"

    val path = way(startPage, endPage)

    if (path != null) {
        println("Way from $startPage to $endPage:")
        for (page in path) {
            println(page)
        }
    } else {
        println("No path found")
    }
}
