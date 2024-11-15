import java.util.Scanner;

public class Main {

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];
    private static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            // Start a new game
            clearBoard();
            String currentPlayer = "X"; // X always starts
            boolean gameOver = false;
            int moveCounter = 0;

            while (!gameOver) {
                displayBoard();

                // Get the player's move
                System.out.println("Player " + currentPlayer + ", make your move:");
                int row = SafeInput.getRangedInt(console, "Enter row (1-3): ", 1, 3) - 1;
                int col = SafeInput.getRangedInt(console, "Enter column (1-3): ", 1, 3) - 1;

                // Validate the move
                while (!isValidMove(row, col)) {
                    System.out.println("Invalid move! Try again.");
                    row = SafeInput.getRangedInt(console, "Enter row (1-3): ", 1, 3) - 1;
                    col = SafeInput.getRangedInt(console, "Enter column (1-3): ", 1, 3) - 1;
                }

                // Make the move
                board[row][col] = currentPlayer;
                moveCounter++;

                // Check for win or tie
                if (isWin(currentPlayer)) {
                    displayBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameOver = true;
                } else if (isTie()) {
                    displayBoard();
                    System.out.println("It's a tie!");
                    gameOver = true;
                }

                // Toggle player
                currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
            }

        } while (SafeInput.getYNConfirm(console, "Do you want to play again? (Y/N)"));

        System.out.println("Thanks for playing!");
    }

    // Helper Methods
    private static void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void displayBoard() {
        System.out.println("\nCurrent board:");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j]);
                if (j < COLS - 1) System.out.print("|");
            }
            System.out.println();
            if (i < ROWS - 1) System.out.println("-----");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int i = 0; i < COLS; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        return false;
    }

    private static boolean isTie() {
        // Check if all spaces are filled
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
