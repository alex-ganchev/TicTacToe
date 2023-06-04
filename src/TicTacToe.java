import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static int determinationFirstPlayer(){
        Random rnd = new Random();
        return rnd.nextInt(2) + 1;
    }
    public static void printTicTacToeBoard(String[][] matrix){
        System.out.println("\t\t\t0\t1\t2");
        for (int i = 0; i < matrix.length; i++) {
            System.out.println("\t\t   --- --- ---");
            System.out.print("\t\t" + i + " | ");
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println("\t\t   --- --- ---");
    }
    public static boolean validateInputs (int coordinates,String[][] ticTacToeBoard){
        if ((coordinates == 0 || coordinates == 1 || coordinates == 2 || coordinates == 10 || coordinates == 11
                || coordinates == 12 || coordinates == 20 || coordinates == 21 || coordinates == 22)
                && ticTacToeBoard[coordinates / 10][coordinates % 10].equals(" "))
        {
            return true;
        } else {
            return false;
        }
    }
    public static String validateWin (String[][] ticTacToeBoard, String mark){
        byte countRows = 0;
        byte countCol = 0;
        byte countDiagRight = 0;
        byte countDiagLeft = 0;
        byte countEmpty = 0;
        for (int i = 0; i < ticTacToeBoard.length; i++) {
            int k = ticTacToeBoard.length-1;
            for (int j = 0; j < ticTacToeBoard[0].length; j++) {
                if (ticTacToeBoard[i][j].equals(mark)){
                    countRows++;
                }
                if (ticTacToeBoard[j][i].equals(mark)){
                    countCol++;
                }
                if (ticTacToeBoard[j][j].equals(mark)){
                    countDiagRight++;
                }
                if (ticTacToeBoard[j][k--].equals(mark)){
                    countDiagLeft++;
                }
                if (ticTacToeBoard[i][j].equals(" ")){
                    countEmpty++;
                }
            }
            if (countRows == 3 || countCol == 3 || countDiagRight == 3 || countDiagLeft == 3){
                return "win";
            }
            countRows = 0;
            countDiagRight = 0;
            countDiagLeft = 0;
            countCol = 0;
        }
        if (countEmpty == 0){
            return "draw";
        }
        return "";
    }
    public static void playGame(){
        Scanner scanner = new Scanner(System.in);
        String[][] ticTacToeBoard = {
                {" "," "," "},
                {" "," "," "},
                {" "," "," "}
        };
        String mark;
        String player;
        int coordinates;
        boolean turn = true;
        System.out.println("********************************************************");
        System.out.println("*****************  ИГРА НА МОРСКИ ШАХ  *****************");
        System.out.println("********************************************************");
        System.out.println("Координатите се подават под формата на двуцифрено число. \nДесетиците са редовете, а единиците са колоните. ");
        System.out.print("Въведете име за първи играч : ");
        String player1Name = scanner.next();
        System.out.print("Въведете име за втори играч : ");
        String player2Name = scanner.next();
        if (determinationFirstPlayer() == 1){
            player = player1Name;
            mark = "O";
        }else{
            player = player2Name;
            mark = "X";
        }
        System.out.println("Заровете са хвърлени, избора е направен!");
        System.out.println("Първи играта започва " + player + "!");
        printTicTacToeBoard(ticTacToeBoard);
        while (true) {
            do {
                System.out.print(mark + " - " + player + ", моля въведи" + (turn?" ":" коректни ") + "координати : ");
                coordinates = scanner.nextInt();
                turn = validateInputs(coordinates,ticTacToeBoard);
            } while (!turn);
            switch (coordinates){
                case 0 : ticTacToeBoard[0][0] = mark; break;
                case 1 : ticTacToeBoard[0][1] = mark; break;
                case 2 : ticTacToeBoard[0][2] = mark; break;
                case 10 : ticTacToeBoard[1][0] = mark; break;
                case 11 : ticTacToeBoard[1][1] = mark; break;
                case 12 : ticTacToeBoard[1][2] = mark; break;
                case 20 : ticTacToeBoard[2][0] = mark; break;
                case 21 : ticTacToeBoard[2][1] = mark; break;
                case 22 : ticTacToeBoard[2][2] = mark; break;
            }
            printTicTacToeBoard(ticTacToeBoard);
            if (validateWin(ticTacToeBoard,mark).equals("win")){
                System.out.println("\tПОЗДРАВЛЕНИЯ! " + player + " ПОБЕДИ !!!");
                break;
            } else if (validateWin(ticTacToeBoard,mark).equals("draw")) {
                System.out.println("\tИграта завърши без победител!");
                break;
            }
            if (mark.equals("O")){
                mark = "X";
                player = player2Name;
            }
            else {
                mark = "O";
                player = player1Name;
            }
        }
    }
    public static void main(String[] args) {
        playGame();
    }
}
