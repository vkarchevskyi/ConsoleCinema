package cinema;
import java.util.Scanner;

public class Cinema {
    private static final int CHEAP_TICKET = 8;
    private static final int EXPENSIVE_TICKET = 10;
    private static void printCinema(int SEATS, int ROWS, char[][] seats) {
        System.out.println("Cinema:");
        for (int j = 0; j <= SEATS; j++) {
            System.out.print(j == 0 ? " " : j);
            System.out.print(" ");
        }

        for (int i = 0; i < ROWS; i++) {
            System.out.println();
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SEATS; j++) {
                System.out.print(seats[i][j] + " ");
            }
        }
        System.out.println();
    }
    private static int intInput(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            if (input.matches("\\d+")) {
                return Integer.parseInt(input);
            }
            System.out.println("Wrong input!");
        }
    }
    private static int intInput(Scanner scanner, int smallerBorder) {
        while (true) {
            int input = intInput(scanner);
            if (input >= smallerBorder) {
                return input;
            } else {
                System.out.println("Wrong input!");
            }
        }
    }
    private static int intInput(Scanner scanner, int smallerBorder, int biggerBorder) {
        while (true) {
            int input = intInput(scanner);
            if (input >= smallerBorder && input <= biggerBorder) {
                return input;
            } else {
                System.out.println("Wrong input!");
            }
        }
    }
    private static int buyTicket(final int SEATS, final int ROWS, char[][] seats, Scanner scanner) {
        int ticketPrice = 0;
        System.out.println("Enter a row number:");
        final int ROW = intInput(scanner, 1, ROWS);
        System.out.println("Enter a seat number in that row:");
        final int SEAT = intInput(scanner, 1, SEATS);

        if (ROWS * SEATS <= 60) {
            ticketPrice = EXPENSIVE_TICKET;
        } else {
            if (ROW <= ROWS/2) {
                ticketPrice = EXPENSIVE_TICKET;
            } else {
                ticketPrice = CHEAP_TICKET;
            }
        }

        if (seats[ROW - 1][SEAT - 1] == 'B') {
            System.out.println("\nThat ticket has already been purchased!\n");
            return 0;
        } else {
            seats[ROW - 1][SEAT - 1] = 'B';
            System.out.printf("%nTicket price: $%d%n%n",ticketPrice);
            return ticketPrice;
        }
    }
    private static void printStatistics(int SEATS, int ROWS, char[][] seats, int totalIncome, int soldTickets, int currentIncome) {
        System.out.printf("Number of purchased tickets: %d\n" +
                "Percentage: %.2f%%\n" + 
                "Current income: $%d\n" +
                "Total income: $%d\n", soldTickets, (double) soldTickets / (SEATS * ROWS) * 100, currentIncome, totalIncome);
    }
    public static void main(String[] args) {
        boolean isWorking = true;
        int soldTickets = 0;
        int currentIncome = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        final int ROWS = intInput(scanner, 1);
        System.out.println("Enter the number of seats in each row:");
        final int SEATS = intInput(scanner, 1);

        int totalIncome = 0;
        char[][] seats = new char[ROWS][SEATS];

        if (ROWS * SEATS <= 60) {
            totalIncome = EXPENSIVE_TICKET * (ROWS * SEATS);
        } else {
            totalIncome = EXPENSIVE_TICKET * (ROWS / 2) * SEATS;
            totalIncome += CHEAP_TICKET * (ROWS - ROWS / 2) * SEATS;
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < SEATS; j++) {
                seats[i][j] = 'S';
            }
        }

        while (isWorking) {
            System.out.print("1. Show the seats\n" + 
                    "2. Buy a ticket\n" + 
                    "3. Statistics\n" +
                    "0. Exit\n");

            int number = intInput(scanner);
            switch (number) {
                case 1:
                    printCinema(SEATS, ROWS, seats);
                    break;
                case 2:
                    int ticketPrice = 0;
                    do {
                        ticketPrice = buyTicket(SEATS, ROWS, seats, scanner);
                    } while (ticketPrice == 0);
                    soldTickets++;
                    currentIncome += ticketPrice;
                    break;
                case 3:
                    printStatistics(SEATS, ROWS, seats, totalIncome, soldTickets, currentIncome);
                    break;
                case 0:
                    isWorking = false;
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }
}