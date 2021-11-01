import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AtmFunctionality {
    public static final int DEFAULT = 1;
    public static final int CUSTOM = 2;
    private static final int EXIST_VALUE = -1;
    private static final String INVALID_INPUT = "Invalid input type (must be an integer)";
    static Scanner input = new Scanner(System.in);

    public static void startAtm() {
        System.out.printf("You just started the ATM, please decide how do you want to initialize it:%n1 for default ATM initialization%n2 for custom ATM initialization%n");
        int option = processInput();
        initializeAtm(option);
    }

    private static void initializeAtm(int option) {
        if (Objects.equals(option, DEFAULT)) {
            AtmImplementation.defaultInstantiation();
        } else if (Objects.equals(option, CUSTOM)) {
            AtmImplementation.customInstantiation(processCustomInitializationInput());
        }
    }

    private static List<Integer> processCustomInitializationInput() {
        List<Integer> billsAmounts = new ArrayList<>();
        billsAmounts.add(numberOfBillsOf(Bills.ONE_HUNDRED));
        billsAmounts.add(numberOfBillsOf(Bills.FIFTY));
        billsAmounts.add(numberOfBillsOf(Bills.TEN));
        billsAmounts.add(numberOfBillsOf(Bills.FIVE));
        billsAmounts.add(numberOfBillsOf(Bills.ONE));
        return billsAmounts;
    }

    private static Integer numberOfBillsOf(int billValue) {
        System.out.printf("Please insert the number of bills with value %d%n", billValue);
        do {
            try {
                int value = input.nextInt();
                if (isPositive(value)) {
                    return value;
                }
                System.out.println("Not a valid option, please insert a positive value");
            } catch (InputMismatchException ex) {
                System.out.println(INVALID_INPUT);
                input.nextLine();
            }
        } while (true);
    }

    private static boolean isPositive(int value) {
        return value >= 0;
    }

    private static int processInput() {
        do {
            try {
                int value = input.nextInt();
                if (isDefaultOrCustom(value)) {
                    return value;
                }
                System.out.println("Not a valid option, please choose between 1 or 2");
            } catch (InputMismatchException ex) {
                System.out.println(INVALID_INPUT);
                input.nextLine();
            }
        } while (true);
    }

    private static boolean isDefaultOrCustom(int value) {
        return value == DEFAULT || value == CUSTOM;
    }

    static void retreatMoney() {
        do {
            int amount = processAmount();
            if (Objects.equals(amount, EXIST_VALUE)) {
                return;
            }
            AtmImplementation.retreatMoney(amount);
        } while (true);


    }

    private static int processAmount() {
        do {
            try {
                System.out.printf("Please insert the amount that you need%n");
                int amount = input.nextInt();
                if (Objects.equals(amount, EXIST_VALUE) || amount > 0) {
                    return amount;
                }
                System.out.printf("Keep in mind that the amount needs to be positive%n");
            } catch (InputMismatchException e) {
                System.out.println(INVALID_INPUT);
            }
        } while (true);
    }

    static void exit() {
        System.out.println("Thank you for using our ATM. Have a nice day!");
    }
}
