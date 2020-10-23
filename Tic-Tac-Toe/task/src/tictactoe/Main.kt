package tictactoe

import java.util.*

fun main() {
    // Handling the input string
    print("Enter Cells: ")
    var board: String = readLine()!!
    printBoard(board)
    var status = checkWin(board)
    println(status)
    // Handling user move
    var validCoord = false
    var col = 0
    var row = 0
    while (!validCoord) {
        println("Make a move in the format 'Column Row': ")
        val coord = readLine()!!
//        println("entered coordinates are: ${coord[0]}, ${coord[2]}")
        if (!coord[0].isDigit() || !coord[2].isDigit()) {
            println("You should enter numbers!")
        } else if (coord[0].toString().toInt() > 3 || coord[0].toString().toInt() < 1 || coord[2].toString().toInt() > 3 || coord[2].toString().toInt() < 1) {
            println("Coordinates should be from 1 to 3!")
        } else if (cellOccupied(board, coord)) {
            println("This cell is occupied! Choose another one!")
        } else {
            validCoord = true
            col = coord[0].toString().toInt()
            row = coord[2].toString().toInt()
        }
    }
//    println("column: $col row: $row")
    board = updateBoard(col, row, board)
//    println(board)
    printBoard(board)
}

/*
  This method updates the current board with the inputted user's move
  @param col: Int - Column of the selected move
  @param row: Int - Row of the selected move
  @param board: String - Current board
  @return board: String - String for the updated board
 */
fun updateBoard(col: Int, row: Int, board: String): String {
    // Converts board from string format to 2d array
    val boardArray = Array(3) { CharArray(3) }
    var counter = 0
    var noXs = 0
    var noOs = 0
    var newBoard = ""
    for (i in 0..2) {
        for (j in 0..2) {
            boardArray[i][j] = board[counter]
            counter++
            // Counting the number of Xs and Os
            if (boardArray[i][j] == 'X') {
                noXs++
            } else if (boardArray[i][j] == 'O') {
                noOs++
            }
        }
    }
    boardArray[col-1][row-1] = 'X'
    for (i in boardArray.indices) {
        for (j in 0..2) {
            newBoard += (boardArray[i][j].toString())
        }
    }
    return newBoard //FIX ME
}

/*
  Checks whether there is already an existing letter at the provided coordinates
  @param board: String - current board
  @param coord: String - provided coordinates
  @return true/false - if the cell is occupied true, otherwise false
 */
fun cellOccupied(board: String, coord: String): Boolean {
    // Converts board from string format to 2d array
    val boardArray = Array(3) { CharArray(3) }
    var counter = 0
    var noXs = 0
    var noOs = 0
    for (i in 0..2) {
        for (j in 0..2) {
            boardArray[i][j] = board[counter]
            counter++
            // Counting the number of Xs and Os
            if (boardArray[i][j] == 'X') {
                noXs++
            } else if (boardArray[i][j] == 'O') {
                noOs++
            }
        }
    }
    if (boardArray[coord[0].toString().toInt()-1][coord[2].toString().toInt()-1] != '_') {
        return true
    }
    return false
}

/*
  Displays the board for the current input string
  @param board: String - current board to be printed
 */
fun printBoard(board: String) {
    println("---------")
    println("| ${board[0]} ${board[1]} ${board[2]} |")
    println("| ${board[3]} ${board[4]} ${board[5]} |")
    println("| ${board[6]} ${board[7]} ${board[8]} |")
    println("---------")
}

/*
  This function takes in the current board of the board and determines the current game state.
  @param boardString: String - current board
  @return gameState: String - current game state
 */
fun checkWin(boardString: String): String {
    var gameState = ""

    // Creates a 2D array of chars representing the input string
    val board = Array(3) { CharArray(3) }
    var counter = 0
    var noXs = 0
    var noOs = 0
    for (i in 0..2) {
        for (j in 0..2) {
            board[i][j] = boardString[counter]
            counter++
            // Counting the number of Xs and Os
            if (board[i][j] == 'X') {
                noXs++
            } else if (board[i][j] == 'O') {
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
                if (board[i][j] != 'X') {
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
                if (board[i][j] != 'O') {
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
                if (board[i][j] != 'X') {
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
                if (board[i][j] != 'O') {
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
            if (board[i][i] != 'X') {
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
            if (board[i][i] != 'O') {
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
        if (board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X') {
            if (gameState == "O wins") {
                gameState = "Impossible"
            } else {
                gameState = "X wins"
            }
        }

        // Check O win by backwards-diagonal
        if (board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O') {
            if (gameState == "X wins") {
                gameState = "Impossible"
            } else {
                gameState = "O wins"
            }
        }

        // Addressing game not finished
        if (gameState == "" && boardHasSpace(board)) {
            gameState = "Game not finished"
        }

        // Must be a draw
        if (gameState == "") {
            gameState = "Draw"
        }
    }
    return(gameState)
}

/*
 This function checks to see if the board has any space left.
 @param board (array)
 @return hasSpace (boolean)
*/
fun boardHasSpace(board: Array<CharArray>): Boolean {
    var hasSpace = false
    for (i in 0..2) {
        for (j in 0..2) {
            if (board[i][j] == '_') {
                hasSpace = true
            }
        }
    }
    return hasSpace
}