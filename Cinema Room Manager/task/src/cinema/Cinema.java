package cinema;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Cinema {

   static int rowNum;
   static int placeNum;
    static int numMenu = 1; // выбранный номер основного меню

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:"); // считываем размеры зала
        int row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int place = scanner.nextInt();
        String[][] hall = new String[row][place]; // создаем пустой массив, модель зала
        int[][] hallPrice = new int[row][place]; // создаем пустой массив, модель стоимости зала
        for (int i = 0; i < row; i++) { // заполняем массив буквами S - это свободные места
            for (int j = 0; j < place; j++) {
                hall[i][j] = "S";
                if (row * place < 60) {
                    hallPrice[i][j] = 10;
                } else if (i + 1 <= row / 2) {
                    hallPrice[i][j] = 10;;
                } else {
                    hallPrice[i][j] = 8;;
                }
            }
        }
        int i = 0;
        while (i <= row * place && numMenu != 0) { //основной цикл, идут запросы пока в зале есть пустые места

            // обращение к ф-и вызова менюб получение номера меню
            numMenu = cinemaMenu(scanner);

            switch (numMenu) {
                case 1: { // печатаем схему зала
                    hallMapPrinting(row, place, hall, hallPrice);
                    break;
                }
                case 2: { // покупаем билет
                    buyTicket(row, place, hall, scanner);
                    i++;
                    break;
                }
                case 3: { //блок статистики
                    salesStatistics(row, place, hall, hallPrice);
                    break;
                }
            }
        }
    }

    private static void salesStatistics(int row, int place, String[][] hall, int[][] hallPrice) { // статистика продаж
        int numberOfSeatsSold = 0; // количество проданных мест
        int revenueFromTicketsSold = 0; // доход от проданных билетов
        int plannedIncomeFromTheEntireHall= 0; // планируемый доход от всего зала
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < place; j++) {
                if (hall[i][j] == "B") {
                    numberOfSeatsSold = numberOfSeatsSold + 1;
                    revenueFromTicketsSold = revenueFromTicketsSold + hallPrice[i][j];
                }
                plannedIncomeFromTheEntireHall = plannedIncomeFromTheEntireHall + hallPrice[i][j];
            }
        }
        double percent = (numberOfSeatsSold * 100.00 / (row * place));
        String result = String.format("%.2f", percent);
        System.out.println("Number of purchased tickets: " + numberOfSeatsSold);
        System.out.println("Percentage: " + result +"%");
        System.out.println("Current income: $" + revenueFromTicketsSold);
        System.out.println("Total income: $" + plannedIncomeFromTheEntireHall);
    }

    private static int cinemaMenu(Scanner scanner) {  // ф-я печати меню
        System.out.printf("%n1. Show the seats%n2. Buy a ticket%n3. Statistics%n0. Exit%n");
        numMenu = scanner.nextInt();
        return numMenu;
    }
    private static void hallMapPrinting(int row, int place, String[][] hall, int[][] hallPrice) {  // ф-я печати схемы зала
        System.out.println("\nCinema:");
        for (int i = 0; i <= place; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        for (int i = 0; i < row; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < place; j++) {
                System.out.print(hall[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static void buyTicket(int row, int place, String[][] hall, Scanner scanner) {
        boolean coordinates;
        boolean freePlace;

        // запрос координат места
        Cinema.rowNum = requestRowNumber(scanner);
        Cinema.placeNum = requestSeatNumber(scanner);
        // проверка выхода за пределы масива
        coordinates = arrayOutOfBounds(row, place, scanner);
        // проверка свободного места в ряду
        freePlace = freeSpaceInARow(row, place,  coordinates, scanner, hall);
        // отмечаем в схеме - место продано
        hall[rowNum - 1][placeNum - 1] = "B";
        // расчет стоимомсти билета по введенным координатам
        if (row * place < 60) {
            System.out.println("\nTicket price: $10");
        } else if (rowNum <= row / 2) {
            System.out.println("\nTicket price: $10");
        } else {
            System.out.println("\nTicket price: $8");
        }
        }

    private static boolean freeSpaceInARow(int row, int place, boolean coordinates, Scanner scanner, String[][] hall) {
        while (hall[rowNum - 1][placeNum - 1] == "B" && coordinates == true) {
            System.out.println("\nThat ticket has already been purchased!");
            Cinema.rowNum = requestRowNumber(scanner);
            placeNum = requestSeatNumber(scanner);
            coordinates = arrayOutOfBounds(row, place, scanner);
        }
        return true;
    }

    private static boolean arrayOutOfBounds(int row, int place, Scanner scanner) {
        boolean i = true;
        while (i == true) {
            if (Cinema.rowNum < 1 || Cinema.rowNum > row || Cinema.placeNum < 1 || Cinema.placeNum > place) {
                System.out.println("\nWrong input!");
                Cinema.rowNum = requestRowNumber(scanner);
                Cinema.placeNum = requestSeatNumber(scanner);
            } else {
                i = false;
            }
        }
        return true;
    }

    private static int requestRowNumber(Scanner scanner) {
        System.out.printf("%nEnter a row number:%n");
        Cinema.rowNum = scanner.nextInt();
        return Cinema.rowNum;
    }

    private static int requestSeatNumber(Scanner scanner) {
        System.out.println("Enter a seat number in that row:");
        Cinema.placeNum = scanner.nextInt();
        return Cinema.placeNum;
    }
}