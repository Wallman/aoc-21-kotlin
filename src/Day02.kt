fun main() {
    fun part1(input: List<String>): Int {
        var forward = 0
        var depth = 0

        for (row in input) {
            val command = row.substringBefore(' ')
            val value = row.substringAfter(' ').toInt()

            if (command == "forward") {
                forward += value
            } else if (command == "down") {
                depth += value
            } else if (command == "up") {
                depth -= value
            }
        }

        return forward*depth
    }

    fun part2(input: List<String>): Int {
        var forward = 0
        var depth = 0
        var aim = 0

        for (row in input) {
            val command = row.substringBefore(' ')
            val value = row.substringAfter(' ').toInt()

            if (command == "forward") {
                forward += value
                depth += (aim * value)
            } else if (command == "down") {
                aim += value
            } else if (command == "up") {
                aim -= value
            }
        }

        return forward*depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    println("Test part1: " + part1(testInput))
    println("Test part2: " + part2(testInput))

    val input = readInput("Day02")
    println("Part 1: " + part1(input))
    println("Part 2: " + part2(input))
}
