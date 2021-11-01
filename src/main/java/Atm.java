import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Getter
public class Atm {
    private static final int MAXIMUM_AMOUNT_ALLOWED = 4_000;

    private static final String INSUFFICIENT_CREDIT = "INSUFFICIENT CREDIT";
    private static final String INSUFFICIENT_BILLS = "INSUFFICIENT BILLS";
    private static final String NEGATIVE_AMOUNT_NOT_ALLOWED = "NEGATIVE AMOUNT NOT ALLOWED";
    private static final String ILLEGAL_AMOUNT = "ILLEGAL AMOUNT";
    private static final String ZERO_AMOUNT = "ATM HAS NO MONEY LEFT";
    private static final String NOT_ENOUGH_BILLS = "ATM HAS NOT ENOUGH BILLS FROM EACH KIND TO EXTRACT YOUR AMOUNT";

    private static int initialOneHundredAmount = 50;
    private static int initialFiftyAmount = 50;

    private int oneHundredBillsNumber;
    private int fiftyBillsNumber;
    private int tenBillsNumber;
    private int fiveBillsNumber;
    private int oneBillsNumber;
    private int total;

    public Atm(int oneHundredBillsNumber, int fiftyBillsNumber, int tenBill, int fiveBill, int oneBill) {
        this.oneHundredBillsNumber = oneHundredBillsNumber;
        this.fiftyBillsNumber = fiftyBillsNumber;
        this.tenBillsNumber = tenBill;
        this.fiveBillsNumber = fiveBill;
        this.oneBillsNumber = oneBill;
        this.total = calculateTotal();
        initializeBills(oneHundredBillsNumber, fiftyBillsNumber);
    }

    private void initializeBills(int oneHundredBillsNumber, int fiftyBillsNumber) {
        initialOneHundredAmount = oneHundredBillsNumber;
        initialFiftyAmount = fiftyBillsNumber;
    }

    public Atm() {
        this.oneHundredBillsNumber = 50;
        this.fiftyBillsNumber = 50;
        this.tenBillsNumber = 100;
        this.fiveBillsNumber = 100;
        this.oneBillsNumber = 100;
        this.total = calculateTotal();
    }

    public void retreatMoney(int amount) {
        if (isTheInitialCheckFailed(amount)) {
            return;
        }
        Map<Integer, Integer> amountInBills = calculateAmountInBills(amount);
        if (nonNull(amountInBills)) {
            reduceBillsStocksAndTotal(amount, amountInBills);
            printAmountRetreatedInBills(amountInBills);
            checkIfAnyNotificationIsNeeded(amount);
        } else {
            System.out.println(NOT_ENOUGH_BILLS);
        }
    }

    private void checkIfAnyNotificationIsNeeded(int amount) {
        checkIfClientNotificationNeeded(amount);
        checkIfAtmNotificationNeeded();
    }

    private void checkIfAtmNotificationNeeded() {
        if (hasInitialValueZero()) {
            return;
        }
        if (isBelowTen()) {
            AtmNotification.sendNotificationBelowTenPercent();
        } else if (isBelowTwenty()) {
            AtmNotification.sendNotificationBelowTwentyPercent();
        }
        if (isBelowFifteen()) {
            AtmNotification.sendNotificationBelowFifteenPercent();
        }
    }

    private boolean isBelowFifteen() {
        return (double) getFiftyBillsNumber() / initialFiftyAmount * 100 < 20;
    }

    private boolean isBelowTwenty() {
        return (double) getOneHundredBillsNumber() / initialOneHundredAmount * 100 < 20;
    }

    private boolean isBelowTen() {
        return (double) getOneHundredBillsNumber() / initialOneHundredAmount * 100 < 10;
    }

    private boolean hasInitialValueZero() {
        return Objects.equals(initialOneHundredAmount, 0) || Objects.equals(initialFiftyAmount, 0);
    }

    private void checkIfClientNotificationNeeded(int amount) {
        if (isNeededToInformTheClient(amount)) {
            ClientNotification.sendNotification(amount);
        }
    }

    private boolean isNeededToInformTheClient(int amount) {
        return amount > 200;
    }

    private void printAmountRetreatedInBills(Map<Integer, Integer> amountInBills) {
        System.out.println("The amount drawn consists of: ");
        if (amountInBills.containsKey(Bills.ONE_HUNDRED) && amountInBills.get(Bills.ONE_HUNDRED) != 0)
            System.out.printf("100 x %d%n", amountInBills.get(Bills.ONE_HUNDRED));
        if (amountInBills.containsKey(Bills.FIFTY) && amountInBills.get(Bills.FIFTY) != 0)
            System.out.printf("50 x %d%n", amountInBills.get(Bills.FIFTY));
        if (amountInBills.containsKey(Bills.TEN) && amountInBills.get(Bills.TEN) != 0)
            System.out.printf("10 x %d%n", amountInBills.get(Bills.TEN));
        if (amountInBills.containsKey(Bills.FIVE) && amountInBills.get(Bills.FIVE) != 0)
            System.out.printf("5 x %d%n", amountInBills.get(Bills.FIVE));
        if (amountInBills.containsKey(Bills.ONE) && amountInBills.get(Bills.ONE) != 0)
            System.out.printf("1 x %d%n", amountInBills.get(Bills.ONE));

    }

    private void reduceBillsStocksAndTotal(int amount, Map<Integer, Integer> amountInBills) {
        oneHundredBillsNumber -= amountInBills.getOrDefault(Bills.ONE_HUNDRED, 0);
        fiftyBillsNumber -= amountInBills.getOrDefault(Bills.FIFTY, 0);
        tenBillsNumber -= amountInBills.getOrDefault(Bills.TEN, 0);
        fiveBillsNumber -= amountInBills.getOrDefault(Bills.FIVE, 0);
        oneBillsNumber -= amountInBills.getOrDefault(Bills.ONE, 0);
        total -= amount;
    }

    private boolean isTheInitialCheckFailed(int amount) {
        return isTotalZero() || isNotEnoughMoney(amount) || isAmountNegative(amount) || isMoreThanMaximumAmount(amount);
    }

    private boolean isTotalZero() {
        if (Objects.equals(getTotal(), 0)) {
            System.out.println(ZERO_AMOUNT);
            return true;
        }
        return false;
    }

    private boolean isMoreThanMaximumAmount(int amount) {
        if (amount > MAXIMUM_AMOUNT_ALLOWED) {
            System.out.println(ILLEGAL_AMOUNT);
            return true;
        }
        return false;
    }

    private boolean isAmountNegative(int amount) {
        if (amount < 0) {
            System.out.println(NEGATIVE_AMOUNT_NOT_ALLOWED);
            return true;
        }
        return false;
    }

    private boolean isNotEnoughMoney(int amount) {
        if (total < amount) {
            System.out.println(INSUFFICIENT_CREDIT);
            return true;
        }
        return false;
    }

    private Map<Integer, Integer> calculateAmountInBills(int amount) {
        Map<Integer, Integer> map = new HashMap<>();
        if (isASingleBillAndIsAvailable(amount, map)) {
            return map;
        }
        if (isAmountAtLeast(amount, Bills.ONE_HUNDRED)) {
            if (amount > oneHundredBillTotal()) {
                map.put(Bills.ONE_HUNDRED, oneHundredBillsNumber);
                amount -= oneHundredBillTotal();
            } else {
                int bills = amount / Bills.ONE_HUNDRED;
                map.put(Bills.ONE_HUNDRED, bills);
                amount -= bills * Bills.ONE_HUNDRED;

            }
        }
        if (isAmountAtLeast(amount, Bills.FIFTY)) {
            if (amount > fiftyBillTotal()) {
                map.put(Bills.FIFTY, fiftyBillsNumber);
                amount -= fiftyBillTotal();
            } else {
                int bills = amount / Bills.FIFTY;
                map.put(Bills.FIFTY, bills);
                amount -= bills * Bills.FIFTY;
            }
        }
        if (isAmountAtLeast(amount, Bills.TEN)) {
            if (amount > tenBillTotal()) {
                map.put(Bills.TEN, tenBillsNumber);
                amount -= tenBillTotal();
            } else {
                int bills = amount / Bills.TEN;
                map.put(Bills.TEN, bills);
                amount -= bills * Bills.TEN;
            }
        }
        if (isAmountAtLeast(amount, Bills.FIVE)) {
            if (amount > fiveBillTotal()) {
                map.put(Bills.FIVE, fiveBillsNumber);
                amount -= fiveBillTotal();
            } else {
                int bills = amount / Bills.FIVE;
                map.put(Bills.FIVE, bills);
                amount -= bills * Bills.FIVE;
            }
        }
        if (isAmountAtLeast(amount, Bills.ONE)) {
            if (amount > oneBillTotal()) {
                map.put(Bills.ONE, oneBillsNumber);
                amount -= oneBillTotal();
            } else {
                int bills = amount / Bills.ONE;
                map.put(Bills.ONE, bills);
                amount -= bills * Bills.ONE;
            }
        }
        if (amount != 0) {
            System.out.println(INSUFFICIENT_BILLS);
            return null;
        }
        return map;
    }

    private boolean isASingleBillAndIsAvailable(int amount, Map<Integer, Integer> map) {
        if (isASingleBill(amount) && isAnAvailableBill(amount)) {
            map.put(amount, 1);
            return true;
        }
        return false;
    }

    private boolean isASingleBill(int amount) {
        return amount == Bills.ONE_HUNDRED || amount == Bills.FIFTY || amount == Bills.TEN || amount == Bills.FIVE || amount == Bills.ONE;
    }

    private boolean isAnAvailableBill(int bill) {
        switch (bill) {
            case Bills.ONE_HUNDRED:
                return oneHundredBillsNumber > 0;
            case Bills.FIFTY:
                return fiftyBillsNumber > 0;
            case Bills.TEN:
                return tenBillsNumber > 0;
            case Bills.FIVE:
                return fiveBillsNumber > 0;
            case Bills.ONE:
                return oneBillsNumber > 0;
            default:
                return false;
        }
    }

    private boolean isAmountAtLeast(int amount, int value) {
        return amount >= value;
    }

    private int calculateTotal() {
        return oneHundredBillTotal() + fiftyBillTotal() + tenBillTotal() + fiveBillTotal() + oneBillTotal();
    }

    private int oneHundredBillTotal() {
        return this.oneHundredBillsNumber * Bills.ONE_HUNDRED;
    }

    private int fiftyBillTotal() {
        return this.fiftyBillsNumber * Bills.FIFTY;
    }

    private int tenBillTotal() {
        return this.tenBillsNumber * Bills.TEN;
    }

    private int fiveBillTotal() {
        return this.fiveBillsNumber * Bills.FIVE;
    }

    private int oneBillTotal() {
        return this.oneBillsNumber * Bills.ONE;
    }
}

