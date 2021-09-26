import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class theX0Game {
    private static final Scanner SC =new Scanner(System.in);
    private static char[][] FIELD;
    private static final char X_DOT = 'X';
    private static final char O_DOT = 'O';
    private static final char EMPTY_DOT = '•';
    private static final int FIELD_SIZE = 5;

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            System.out.println("__________");
            if (isWin(X_DOT)) {
                System.out.println("Победа");
                break;
            }
            if (isDraw()) {
                break;
            }
            computerTurn();
            printMap();
            System.out.println("__________");
            if (isWin(O_DOT)) {
                System.out.println("Поражение");
                break;
            }
            if (isDraw()) {
                break;
            }
        }
    }
    private static boolean isWin(char dot) {
        for (int i = 0; i < FIELD_SIZE; i++) { //horizontal win check
            int tempValue = 0;
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (FIELD[i][j] == dot) {
                    tempValue = tempValue + 10;
                    if (tempValue == 40) {
                        return true;
                    }
                } else {
                    tempValue = 0;
                }
            }
        }
        for (int i = 0; i < FIELD_SIZE; i++) { //vertical win check
            int tempValue = 0;
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (FIELD[j][i] == dot) {
                    tempValue = tempValue + 10;
                    if (tempValue == 40) {
                        return true;
                    }
                } else {
                    tempValue = 0;
                }
            }
        }
        int tempValue = 0;
        for (int i = 0; i < FIELD_SIZE; i++) { // diagonal check
            if(FIELD[i][i] == dot) {
                tempValue = tempValue + 10;
                if(tempValue == 40){
                    return true;
                }
            }
            else if(FIELD[i][FIELD_SIZE-i-1]== dot){
                tempValue =tempValue +10;
                if(tempValue==40){
                    return true;
                }
            }
        }
        return false;
    }

    private static void humanTurn() {
        int xCoordinate;
        int yCoordinate;
        System.out.println("Введите координаты в формате \"x пробел y\"");
        do {
            xCoordinate = -1;
            yCoordinate = -1;

            if (SC.hasNextInt()) {
                xCoordinate = SC.nextInt();
            }
            if (SC.hasNextInt()) {
                yCoordinate = SC.nextInt();
            }
            SC.nextLine();
        } while (!isValidHumanTurn(xCoordinate, yCoordinate));
    }

    private static void computerTurn() {
        int xCoordinate;
        int yCoordinate;
        Random random = new Random();
        do {
            xCoordinate = random.nextInt(FIELD_SIZE);
            yCoordinate = random.nextInt(FIELD_SIZE);
        } while (!isValidComputerTurn(xCoordinate, yCoordinate));
    }

    private static boolean isValidHumanTurn(int xCoordinate, int yCoordinate) {
        if (xCoordinate < 1 || yCoordinate < 1 ||
                xCoordinate > FIELD_SIZE || yCoordinate > FIELD_SIZE) {
            System.out.println("Вы ввели неправильные координаты. Введите координаты в формате \"x пробел y\"");
            return false;
        }

        if (FIELD[xCoordinate - 1][yCoordinate - 1] == EMPTY_DOT) {
            FIELD[xCoordinate - 1][yCoordinate - 1] = X_DOT;
            return true;
        }
        System.out.println("Вы ввели неправильные координаты. Введите координаты в формате \"x пробел y\"");
        return false;
    }

    private static boolean isValidComputerTurn(int xCoordinate, int yCoordinate) {
        if (FIELD[xCoordinate][yCoordinate] == EMPTY_DOT) {
            FIELD[xCoordinate][yCoordinate] = O_DOT;
            return true;
        }
        return false;
    }

    private static void printMap() {
        for (int i = 0; i < FIELD.length; i++) {
            for (int j = 0; j < FIELD[i].length; j++) {
                System.out.print(FIELD[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void initMap() {
        FIELD = new char[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < FIELD_SIZE; i++) {
            Arrays.fill(FIELD[i], EMPTY_DOT);
        }
    }

    private static boolean isDraw() {
        for (int i = 0; i < FIELD.length; i++) {
            for (int j = 0; j < FIELD[i].length; j++) {
                if (FIELD[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        System.out.println("Ничья");
        return true;
    }
}