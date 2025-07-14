import java.util.Scanner;

public class TicTacToeAI {
    static char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    static char human = 'X';
    static char ai = 'O';
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Tic-Tac-Toe | You (X) vs AI (O)");
        printBoard();

        while (true) {
            humanMove();
            printBoard();
            if (winner(human)) { System.out.println("You win!"); break; }
            if (isDraw()) { System.out.println("Draw!"); break; }

            aiMove();
            printBoard();
            if (winner(ai)) { System.out.println("AI wins!"); break; }
            if (isDraw()) { System.out.println("Draw!"); break; }
        }
    }

    static void printBoard() {
        System.out.println(" " + board[0] + "|" + board[1] + "|" + board[2]);
        System.out.println("--+-+--");
        System.out.println(" " + board[3] + "|" + board[4] + "|" + board[5]);
        System.out.println("--+-+--");
        System.out.println(" " + board[6] + "|" + board[7] + "|" + board[8]);
    }

    static boolean winner(char player) {
        int[][] wins = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for (int[] line : wins)
            if (board[line[0]] == player && board[line[1]] == player && board[line[2]] == player) return true;
        return false;
    }

    static boolean isDraw() {
        for (char c : board) if (c == ' ') return false;
        return true;
    }

    static void humanMove() {
        int move;
        while (true) {
            System.out.print("Enter position (1-9): ");
            move = sc.nextInt() - 1;
            sc.nextLine();
            if (move >= 0 && move < 9 && board[move] == ' ') break;
            System.out.println("Invalid!");
        }
        board[move] = human;
    }

    static void aiMove() {
        int bestScore = Integer.MIN_VALUE, move = -1;
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = ai;
                int score = minimax(false);
                board[i] = ' ';
                if (score > bestScore) { bestScore = score; move = i; }
            }
        }
        board[move] = ai;
        System.out.println("AI chooses " + (move+1));
    }

    static int minimax(boolean isMax) {
        if (winner(ai)) return 10;
        if (winner(human)) return -10;
        if (isDraw()) return 0;

        int bestScore = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = isMax ? ai : human;
                int score = minimax(!isMax);
                board[i] = ' ';
                bestScore = isMax ? Math.max(score, bestScore) : Math.min(score, bestScore);
            }
        }
        return bestScore;
    }
}
