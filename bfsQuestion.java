import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private boolean gameFinished;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        gameFinished = false;
        initializeBoard();
    }

    private void initializeBoard() {
        char cellNum = '1';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = cellNum++;
            }
        }
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
            return true;
        }

        return false;
    }

    public void makeMove(int cell) {
        if (cell < 1 || cell > 9) {
            System.out.println("Invalid move! Cell number must be between 1 and 9.");
            return;
        }

        int row = (cell - 1) / 3;
        int col = (cell - 1) % 3;

        if (board[row][col] == 'X' || board[row][col] == 'O') {
            System.out.println("Invalid move! Cell already occupied.");
            return;
        }

        board[row][col] = currentPlayer;

        if (checkWin()) {
            gameFinished = true;
            System.out.println("Player " + currentPlayer + " wins!");
        } else if (isBoardFull()) {
            gameFinished = true;
            System.out.println("It's a draw!");
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic Tac Toe!");

        while (!game.gameFinished) {
            game.printBoard();

            char currentPlayer = game.currentPlayer;
            System.out.println("Player " + currentPlayer + ", enter your move (1-9):");

            int cell = scanner.nextInt();

            game.makeMove(cell);
        }

        game.printBoard();
        scanner.close();
    }
}
