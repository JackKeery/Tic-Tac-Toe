package tictactoe

import java.util.*

fun main() {

    // Handling the input string
    print("Enter Cells: ")
    val scanner = Scanner(System.`in`)
    val iString: String = scanner.next()
    println("---------")
    println("| ${iString[0]} ${iString[1]} ${iString[2]} |")
    println("| ${iString[3]} ${iString[4]} ${iString[5]} |")
    println("| ${iString[6]} ${iString[7]} ${iString[8]} |")
    println("---------")

    var gameState = ""

    // Creates a 2D array of chars representing iString
    val grid = Array(3) {CharArray(3)}
    var counter = 0
    var noXs = 0
    var noOs = 0
    for (i in 0..2) {
        for (j in 0..2) {
            grid[i][j] = iString[counter]
            counter++
            // Counting the number of Xs and Os
            if (grid[i][j] == 'X') {
                noXs++
            } else if (grid[i][j] == 'O') {
                noOs++
            }
        }
    }

    // Check impossible by diff in no. Xs to Os
    val difXO = noXs - noOs
    if (difXO < -1 || difXO > 1) {
        gameState = "Impossible"
    } else {
        // Check X Win by rows
        for (i in 0..2) {
            for (j in 0..2) {
                if (grid[i][j] != 'X') {
                    break
                }
                if (j == 2) {
                    if (gameState == "O wins") {
                        gameState = "Impossible"
                    } else {
                        gameState = "X wins"
                    }
                    break
                }
            }
        }

        // Check O Win by rows
        for (i in 0..2) {
            for (j in 0..2) {
                if (grid[i][j] != 'O') {
                    break
                }
                if (j == 2) {
                    if (gameState == "X wins") {
                        gameState = "Impossible"
                    } else {
                        gameState = "O wins"
                    }
                    break
                }
            }
        }

        // Check X Win by columns
        for (j in 0..2) {
            for (i in 0..2) {
                if (grid[i][j] != 'X') {
                    break
                }
                if (i == 2) {
                    if (gameState == "O wins") {
                        gameState = "Impossible"
                    } else {
                        gameState = "X wins"
                    }
                    break
                }
            }
        }

        // Check O Win by columns
        for (j in 0..2) {
            for (i in 0..2) {
                if (grid[i][j] != 'O') {
                    break
                }
                if (i == 2) {
                    if (gameState == "X wins") {
                        gameState = "Impossible"
                    } else {
                        gameState = "O wins"
                    }
                    break
                }
            }
        }

        // Check X Win by diagonal
        for (i in 0..2) {
            if (grid[i][i] != 'X') {
                break
            }
            if (i == 2) {
                if (gameState == "O wins") {
                    gameState = "Impossible"
                } else {
                    gameState = "X wins"
                }
            }
        }

        // Check O Win by diagonal
        for (i in 0..2) {
            if (grid[i][i] != 'O') {
                break
            }
            if (i == 2) {
                if (gameState == "X wins") {
                    gameState = "Impossible"
                } else {
                    gameState = "O wins"
                }
            }
        }

        // Check X win by backwards-diagonal
        if (grid[0][2] == 'X' && grid[1][1] == 'X' && grid[2][0] == 'X') {
            if (gameState == "O wins") {
                gameState = "Impossible"
            } else {
                gameState = "X wins"
            }
        }

        // Check O win by backwards-diagonal
        if (grid[0][2] == 'O' && grid[1][1] == 'O' && grid[2][0] == 'O') {
            if (gameState == "X wins") {
                gameState = "Impossible"
            } else {
                gameState = "O wins"
            }
        }

        // Addressing game not finished
        if (gameState == "") {
            for (i in 0..2) {
                for (j in 0..2) {
                    if (grid[i][j] == '_') {
                        gameState = "Game not finished"
                        break
                    }
                }
            }
        }

        // Must be a draw
        if (gameState == "") {
            gameState = "Draw"
        }
    }
    println(gameState)
}

/*
 This function checks to see if the grid has any space left.
 @Param grid (array)
 @Return hasSpace (boolean)

fun gridHasSpace(grid: CharArray): Boolean {
    var hasSpace = false
    for (square in grid) {
        if (square == '_') {
            hasSpace = true
        }
    }
    return hasSpace
}
*/