package csc3205;
import java.util.ArrayList;
import java.util.Scanner;

public class WeeklyTemperatures {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<String> days = new ArrayList<>();
        ArrayList<Double> temperatures = new ArrayList<>();

        String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday", "Saturday", "Sunday"};

        System.out.println("=== Weekly Temperature Tracker ===");
        System.out.println("Enter the average temperature for each day of the week.");

        for (String day : dayNames) {
            days.add(day);
            temperatures.add(readTemperature(input, day));
        }

        boolean running = true;

        while (running) {
            System.out.println();
            System.out.print("Enter a day (Monday-Sunday), \"week\" for all days, or \"exit\" to quit: ");
            String request = input.nextLine().trim();

            if (request.equalsIgnoreCase("exit")) {
                running = false;
                System.out.println("Goodbye!");
            } else if (request.equalsIgnoreCase("week")) {
                displayWeek(days, temperatures);
            } else {
                int index = findDayIndex(days, request);
                if (index >= 0) {
                    System.out.printf("%s: %.1f degrees%n", days.get(index), temperatures.get(index));
                } else {
                    System.out.println("\"" + request + "\" is not a valid day. Please try again.");
                }
            }
        }

        input.close();
    }

    private static double readTemperature(Scanner input, String day) {
        while (true) {
            System.out.print(day + ": ");
            String entry = input.nextLine().trim();
            try {
                double temperature = Double.parseDouble(entry);
                if (temperature >= -100 && temperature <= 150) {
                    return temperature;
                }
                System.out.println("  Please enter a temperature between -100 and 150.");
            } catch (NumberFormatException e) {
                System.out.println("  Invalid entry. Please enter a number.");
            }
        }
    }

    private static int findDayIndex(ArrayList<String> days, String dayName) {
        for (int i = 0; i < days.size(); i++) {
            if (days.get(i).equalsIgnoreCase(dayName)) {
                return i;
            }
        }
        return -1;
    }

    private static void displayWeek(ArrayList<String> days, ArrayList<Double> temperatures) {
        double total = 0.0;

        System.out.println("--- Weekly Temperatures ---");
        for (int i = 0; i < days.size(); i++) {
            System.out.printf("%-10s %.1f degrees%n", days.get(i), temperatures.get(i));
            total += temperatures.get(i);
        }

        System.out.printf("Weekly average: %.1f degrees%n", total / days.size());
    }
}