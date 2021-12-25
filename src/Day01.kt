fun main() {
    fun part1(input: List<String>): Int {
        var increased = 0
        val inputNums = input.map { Integer.parseInt(it) }
        for (i in 0..inputNums.size-2) {
            if (inputNums[i + 1] > inputNums[i]) {
                increased += 1
            }
        }
        return increased
    }

    fun part2(input: List<String>): Int {
        var increased = 0
        val inputNums = input.map { Integer.parseInt(it) }
        for (i in 0..inputNums.size-4) {
            if (inputNums[i + 3] > inputNums[i]) {
                increased += 1
            }
        }
        return increased
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_part2_test")
    println("Test: " + part2(testInput))

    val input = readInput("Day01")
    println("Part 1: " + part1(input))
    println("Part 2: " + part2(input))
}
