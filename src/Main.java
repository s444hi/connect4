import java.util.Scanner;

/**
 * @author saahi venkatraghavan
 */
public class Main {
    public static void printBoard(int board[][]) {
        for (int i = 0; i < 6; i++) {
            System.out.println();
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j] + " ");
            }
        }
        System.out.println("");
        System.out.println("\n1 2 3 4 5 6 7");
    }

    public static int playerMove(int playerNum) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("player " + playerNum + ", enter your move (column 1-7): ");
        int move = scanner.nextInt();
        scanner.nextLine();
        return move - 1;
    }

    public static int pieceDrop(int[][] board, int column, int playerNum) {
        for (int row = 5; row >= 0; row--) {
            if (board[row][column] == 0) {
                board[row][column] = playerNum;
                return row;
            }
        }
        return -1;
    }
    public static boolean isWin(int[][] board, int playerNum, int row, int column) {
        for (int c = 0; c <= 3; c++) {
            // horizonal
            if (column - c >= 0 && column - c + 3 < 7) {
                if (board[row][column - c] == playerNum && board[row][column - c + 1] == playerNum
                        && board[row][column - c + 2] == playerNum
                        && board[row][column - c + 3] == playerNum) {
                    return true;
                }
            }
        }

        // vertivkl
        for (int r = 0; r <= 3; r++) {
            if (row - r >= 0 && row - r + 3 < 6) {
                if (board[row - r][column] == playerNum && board[row - r + 1][column] == playerNum
                        && board[row - r + 2][column] == playerNum
                        && board[row - r + 3][column] == playerNum) {
                    return true;
                }
            }
        }

        // diag right
        for (int r = 0; r <= 3; r++) {
            for (int c = 0; c <= 3; c++) {
                if (row - r >= 0 && row - r + 3 < 6 && column - c >= 0 && column - c + 3 < 7) {
                    if (board[row - r][column - c] == playerNum && board[row - r + 1][column - c + 1] == playerNum
                            && board[row - r + 2][column - c + 2] == playerNum
                            && board[row - r + 3][column - c + 3] == playerNum) {
                        return true;
                    }
                }
            }
        }
        // diag left
        for (int r = 0; r <= 3; r++) {
            for (int c = 0; c <= 3; c++) {
                if (row - r >= 0 && row - r + 3 < 6 && column + c <= 6 && column + c - 3 >= 0) {
                    if (board[row - r][column + c] == playerNum && board[row - r + 1][column + c - 1] == playerNum
                            && board[row - r + 2][column + c - 2] == playerNum
                            && board[row - r + 3][column + c - 3] == playerNum) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

        public static void main (String[] args) {
            Scanner scanner = new Scanner(System.in);
            // 7x6
            int[][] board = new int[6][7];
            int currentPlayer = 1;
            boolean gameIsWon = false;

            while (!gameIsWon) {
                printBoard(board);
                int move = playerMove(currentPlayer);
                int row = pieceDrop(board, move, currentPlayer);

                if (row == -1) {
                    System.out.println("the column is full.");
                } else if (isWin(board, currentPlayer, row, move)) {
                    printBoard(board);
                    System.out.println("player " + currentPlayer + " wins!");
                    gameIsWon = true;
                } else {
                    if (currentPlayer == 1) {
                        currentPlayer = 2;
                    } else {
                        currentPlayer = 1;
                    }
                }
            }
        }
    }