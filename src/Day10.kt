import java.util.*

fun main() {
    val testInput = readInput("Day10_test")
    println("Test part1: " + part1(testInput))
    println("Test part2: " + part2(testInput))

    val input = readInput("Day10")
    println("Part 1: " + part1(input))
    println("Part 2: " + part2(input))
}

fun part1(input: List<String>): Int {
    var score = 0
    for (line in input) {
        score += getScore(line)
    }
    return score
}

fun part2(input: List<String>): Long {
    val scores = input.filter { !it.isCorrupt() }
        .map { getClosingString(it) }
        .map { getClosingStringScore(it) }
        .sorted()

    return scores[scores.size / 2]
}

fun getClosingStringScore(closingString: String): Long {
    return closingString.map { translate(it) }
        .map { it.getClosingStringScore() }
        .reduce { acc, i -> (acc * 5) + i }
}

fun getClosingString(incompleteString: String): String {
    val parts = incompleteString.map { translate(it) }
    val stack = Stack<ChunkPart>()

    for (part in parts) {
        when (part.operation) {
            Operation.OPEN -> stack.push(part)
            Operation.CLOSE -> stack.pop()
        }
    }

    var closingString = ""
    while (stack.isNotEmpty()) {
        closingString += stack.pop().type.getClosingChar()
    }
    return closingString
}

fun String.isCorrupt(): Boolean {
    return getScore(this) > 0
}

fun getScore(line: String): Int {
    val parts = line.map { translate(it) }
    val stack = Stack<ChunkPart>()

    for (part in parts) {
        when (part.operation) {
            Operation.OPEN -> stack.push(part)
            Operation.CLOSE -> {
                val openingPart = stack.pop()
                if (openingPart.type != part.type) {
                    return part.getScore()
                }
            }
        }
    }
    return 0
}

fun ChunkPart.getScore(): Int {
    return when (this.type) {
        ChunkType.PARENTHESIS -> 3
        ChunkType.BRACKET -> 57
        ChunkType.CURLY -> 1197
        ChunkType.ANGLE -> 25137
    }
}

fun ChunkPart.getClosingStringScore(): Long {
    return when (this.type) {
        ChunkType.PARENTHESIS -> 1
        ChunkType.BRACKET -> 2
        ChunkType.CURLY -> 3
        ChunkType.ANGLE -> 4
    }
}

fun translate(char: Char): ChunkPart {
    return when (char) {
        '(' -> ChunkPart(ChunkType.PARENTHESIS, Operation.OPEN)
        ')' -> ChunkPart(ChunkType.PARENTHESIS, Operation.CLOSE)
        '[' -> ChunkPart(ChunkType.BRACKET, Operation.OPEN)
        ']' -> ChunkPart(ChunkType.BRACKET, Operation.CLOSE)
        '{' -> ChunkPart(ChunkType.CURLY, Operation.OPEN)
        '}' -> ChunkPart(ChunkType.CURLY, Operation.CLOSE)
        '<' -> ChunkPart(ChunkType.ANGLE, Operation.OPEN)
        '>' -> ChunkPart(ChunkType.ANGLE, Operation.CLOSE)
        else -> throw IllegalArgumentException()
    }
}

class ChunkPart(val type: ChunkType, val operation: Operation)

fun ChunkType.getClosingChar(): Char {
    return when (this) {
        ChunkType.PARENTHESIS -> ')'
        ChunkType.BRACKET -> ']'
        ChunkType.CURLY -> '}'
        ChunkType.ANGLE -> '>'
    }
}

enum class ChunkType {
    PARENTHESIS,
    BRACKET,
    CURLY,
    ANGLE
}

enum class Operation {
    OPEN,
    CLOSE
}
