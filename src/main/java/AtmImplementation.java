import java.util.List;

public class AtmImplementation {
    static Atm atm;
    static boolean once = false;

    public static void defaultInstantiation() {
        if (!once) {
            atm = new Atm();
            once = true;
        }
    }

    public static void customInstantiation(List<Integer> billsAmount) {
        if (!once) {
            atm = new Atm(billsAmount.get(0), billsAmount.get(1), billsAmount.get(2), billsAmount.get(3), billsAmount.get(4));
            once = true;
        }
    }

    public static void retreatMoney(int amount) {
        atm.retreatMoney(amount);
    }
}
