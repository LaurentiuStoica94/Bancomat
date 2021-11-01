import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtmTest {
    @Test
    @DisplayName("Check default stock")
    void checkDefaultStock() {
        Atm atm = new Atm();
        assertEquals(50, atm.getOneHundredBillsNumber());
        assertEquals(50, atm.getFiftyBillsNumber());
        assertEquals(100, atm.getTenBillsNumber());
        assertEquals(100, atm.getOneBillsNumber());
        assertEquals(100, atm.getOneBillsNumber());
        assertEquals(9100, atm.getTotal());
    }

    @Test
    @DisplayName("Check custom stock")
    void checkCustomStock() {
        Atm atm = new Atm(10, 20, 30, 40, 50);
        assertEquals(10, atm.getOneHundredBillsNumber());
        assertEquals(20, atm.getFiftyBillsNumber());
        assertEquals(30, atm.getTenBillsNumber());
        assertEquals(40, atm.getFiveBillsNumber());
        assertEquals(50, atm.getOneBillsNumber());
        assertEquals(2550, atm.getTotal());
    }

    @Test
    @DisplayName("Check one single bill amount result")
    void checkSingleBillOutput() {
        Atm atm = new Atm();
        atm.retreatMoney(100);
        atm.retreatMoney(50);
        atm.retreatMoney(10);
        atm.retreatMoney(5);
        atm.retreatMoney(1);
        assertEquals(49, atm.getOneHundredBillsNumber());
        assertEquals(49, atm.getFiftyBillsNumber());
        assertEquals(99, atm.getTenBillsNumber());
        assertEquals(99, atm.getFiveBillsNumber());
        assertEquals(99, atm.getOneBillsNumber());
    }


    @Test
    @DisplayName("Tests with combinations of one and enough money")
    void checkOutputForOneCombinations() {
        Atm atm = new Atm();
        atm.retreatMoney(1);
        assertEquals(99, atm.getOneBillsNumber());

        atm.retreatMoney(2);
        assertEquals(97, atm.getOneBillsNumber());

        assertEquals(50, atm.getOneHundredBillsNumber());
        assertEquals(50, atm.getFiftyBillsNumber());
        assertEquals(100, atm.getTenBillsNumber());
        assertEquals(100, atm.getFiveBillsNumber());
    }

    @Test
    @DisplayName("Tests with combinations of one, five and enough money")
    void checkOutputForOneFiveCombinations() {
        Atm atm = new Atm();
        atm.retreatMoney(5);
        assertEquals(99, atm.getFiveBillsNumber());

        atm.retreatMoney(6);
        assertEquals(98, atm.getFiveBillsNumber());
        assertEquals(99, atm.getOneBillsNumber());

        atm.retreatMoney(7);
        assertEquals(97, atm.getFiveBillsNumber());
        assertEquals(97, atm.getOneBillsNumber());

        assertEquals(50, atm.getOneHundredBillsNumber());
        assertEquals(50, atm.getFiftyBillsNumber());
        assertEquals(100, atm.getTenBillsNumber());
    }

    @Nested
    @DisplayName("Tests with combinations of one, five, ten and enough money")
    class OneFiveTen {
        @Test
        @DisplayName("Tests with combinations of ten")
        void checkTenCombinations() {
            Atm atm = new Atm();
            atm.retreatMoney(10);
            assertEquals(99, atm.getTenBillsNumber());

            atm.retreatMoney(30);
            assertEquals(96, atm.getTenBillsNumber());

            assertEquals(50, atm.getOneHundredBillsNumber());
            assertEquals(50, atm.getFiftyBillsNumber());
            assertEquals(100, atm.getFiveBillsNumber());
            assertEquals(100, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests with combinations of ten and five")
        void checkTenAndFiveCombinations() {
            Atm atm = new Atm();
            atm.retreatMoney(15);
            assertEquals(99, atm.getTenBillsNumber());
            assertEquals(99, atm.getFiveBillsNumber());

            atm.retreatMoney(25);
            assertEquals(97, atm.getTenBillsNumber());
            assertEquals(98, atm.getFiveBillsNumber());

            assertEquals(50, atm.getOneHundredBillsNumber());
            assertEquals(50, atm.getFiftyBillsNumber());
            assertEquals(100, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests with combinations of ten and one")
        void checkTenAndOneCombinations() {
            Atm atm = new Atm();

            atm.retreatMoney(11);
            assertEquals(99, atm.getTenBillsNumber());
            assertEquals(99, atm.getOneBillsNumber());

            atm.retreatMoney(23);
            assertEquals(97, atm.getTenBillsNumber());
            assertEquals(96, atm.getOneBillsNumber());

            assertEquals(50, atm.getOneHundredBillsNumber());
            assertEquals(50, atm.getFiftyBillsNumber());
            assertEquals(100, atm.getFiveBillsNumber());
        }

        @Test
        @DisplayName("Tests with combinations of ten, five and one")
        void checkTenFiveOneCombinations() {
            Atm atm = new Atm();

            atm.retreatMoney(16);
            assertEquals(99, atm.getTenBillsNumber());
            assertEquals(99, atm.getFiveBillsNumber());
            assertEquals(99, atm.getOneBillsNumber());

            atm.retreatMoney(27);
            assertEquals(97, atm.getTenBillsNumber());
            assertEquals(98, atm.getFiveBillsNumber());
            assertEquals(97, atm.getOneBillsNumber());

            assertEquals(50, atm.getOneHundredBillsNumber());
            assertEquals(50, atm.getFiftyBillsNumber());
        }
    }

    @Nested
    @DisplayName("Tests with combinations of one, five, ten, fifty and enough money")
    class OneFiveTenFifty {
        @Test
        @DisplayName("Tests with combinations of fifty and ten or five or one")
        void checkFiftyAndTenOrFiveOrOneCombinations() {
            Atm atm = new Atm();

            atm.retreatMoney(50);
            assertEquals(49, atm.getFiftyBillsNumber());

            atm.retreatMoney(51);
            assertEquals(48, atm.getFiftyBillsNumber());
            assertEquals(99, atm.getOneBillsNumber());

            atm.retreatMoney(52);
            assertEquals(47, atm.getFiftyBillsNumber());
            assertEquals(97, atm.getOneBillsNumber());

            atm.retreatMoney(55);
            assertEquals(46, atm.getFiftyBillsNumber());
            assertEquals(99, atm.getFiveBillsNumber());

            atm.retreatMoney(60);
            assertEquals(45, atm.getFiftyBillsNumber());
            assertEquals(99, atm.getTenBillsNumber());

            atm.retreatMoney(70);
            assertEquals(44, atm.getFiftyBillsNumber());
            assertEquals(97, atm.getTenBillsNumber());
            assertEquals(99, atm.getFiveBillsNumber());
            assertEquals(97, atm.getOneBillsNumber());
            assertEquals(50, atm.getOneHundredBillsNumber());
        }

        @Test
        @DisplayName("Tests with combinations of fifty, ten and five or one")
        void checkFiftyTenAndFiveOrOneCombinations() {
            Atm atm = new Atm();

            atm.retreatMoney(61);
            assertEquals(49, atm.getFiftyBillsNumber());
            assertEquals(99, atm.getTenBillsNumber());
            assertEquals(99, atm.getOneBillsNumber());

            atm.retreatMoney(83);
            assertEquals(48, atm.getFiftyBillsNumber());
            assertEquals(96, atm.getTenBillsNumber());
            assertEquals(96, atm.getOneBillsNumber());

            atm.retreatMoney(63);
            assertEquals(47, atm.getFiftyBillsNumber());
            assertEquals(95, atm.getTenBillsNumber());
            assertEquals(93, atm.getOneBillsNumber());

            atm.retreatMoney(71);
            assertEquals(46, atm.getFiftyBillsNumber());
            assertEquals(93, atm.getTenBillsNumber());
            assertEquals(92, atm.getOneBillsNumber());

            atm.retreatMoney(65);
            assertEquals(45, atm.getFiftyBillsNumber());
            assertEquals(92, atm.getTenBillsNumber());
            assertEquals(99, atm.getFiveBillsNumber());

            atm.retreatMoney(75);
            assertEquals(44, atm.getFiftyBillsNumber());
            assertEquals(90, atm.getTenBillsNumber());
            assertEquals(98, atm.getFiveBillsNumber());
            assertEquals(92, atm.getOneBillsNumber());
            assertEquals(50, atm.getOneHundredBillsNumber());
        }

        @Test
        @DisplayName("Tests with combinations of fifty, five and one")
        void checkFiftyFiveAndOneCombinations() {
            Atm atm = new Atm();

            atm.retreatMoney(56);
            assertEquals(49, atm.getFiftyBillsNumber());
            assertEquals(99, atm.getFiveBillsNumber());
            assertEquals(99, atm.getOneBillsNumber());

            atm.retreatMoney(59);
            assertEquals(48, atm.getFiftyBillsNumber());
            assertEquals(98, atm.getFiveBillsNumber());
            assertEquals(95, atm.getOneBillsNumber());
            assertEquals(100, atm.getTenBillsNumber());
            assertEquals(50, atm.getOneHundredBillsNumber());
        }

        @Test
        @DisplayName("Tests with combinations of fifty, ten, five and one")
        void checkFiftyTenFiveAndOneCombinations() {
            Atm atm = new Atm();

            atm.retreatMoney(66);
            assertEquals(49, atm.getFiftyBillsNumber());
            assertEquals(99, atm.getTenBillsNumber());
            assertEquals(99, atm.getFiveBillsNumber());
            assertEquals(99, atm.getOneBillsNumber());

            atm.retreatMoney(99);
            assertEquals(48, atm.getFiftyBillsNumber());
            assertEquals(95, atm.getTenBillsNumber());
            assertEquals(98, atm.getFiveBillsNumber());
            assertEquals(95, atm.getOneBillsNumber());

            atm.retreatMoney(67);
            assertEquals(47, atm.getFiftyBillsNumber());
            assertEquals(94, atm.getTenBillsNumber());
            assertEquals(97, atm.getFiveBillsNumber());
            assertEquals(93, atm.getOneBillsNumber());

            atm.retreatMoney(86);
            assertEquals(46, atm.getFiftyBillsNumber());
            assertEquals(91, atm.getTenBillsNumber());
            assertEquals(96, atm.getFiveBillsNumber());
            assertEquals(92, atm.getOneBillsNumber());
            assertEquals(50, atm.getOneHundredBillsNumber());
        }
    }

    @Nested
    @DisplayName("Tests with combinations of one, five, ten, fifty,one hundred and enough money")
    class OneFiveTenFiftyOneHundred {
        @Test
        @DisplayName("Tests with combinations of one hundred and fifty or ten or five or one")
        void checkOneHundredAndFiftyOrTenOrFiveOrOneCombinations() {
            Atm atm = new Atm();
            atm.retreatMoney(100);
            assertEquals(49, atm.getOneHundredBillsNumber());

            atm.retreatMoney(500);
            assertEquals(44, atm.getOneHundredBillsNumber());

            atm.retreatMoney(101);
            assertEquals(43, atm.getOneHundredBillsNumber());
            assertEquals(99, atm.getOneBillsNumber());

            atm.retreatMoney(704);
            assertEquals(36, atm.getOneHundredBillsNumber());
            assertEquals(95, atm.getOneBillsNumber());

            atm.retreatMoney(102);
            assertEquals(35, atm.getOneHundredBillsNumber());
            assertEquals(93, atm.getOneBillsNumber());

            atm.retreatMoney(105);
            assertEquals(34, atm.getOneHundredBillsNumber());
            assertEquals(99, atm.getFiveBillsNumber());

            atm.retreatMoney(305);
            assertEquals(31, atm.getOneHundredBillsNumber());
            assertEquals(98, atm.getFiveBillsNumber());

            atm.retreatMoney(110);
            assertEquals(30, atm.getOneHundredBillsNumber());
            assertEquals(99, atm.getTenBillsNumber());

            atm.retreatMoney(120);
            assertEquals(29, atm.getOneHundredBillsNumber());
            assertEquals(97, atm.getTenBillsNumber());

            atm.retreatMoney(310);
            assertEquals(26, atm.getOneHundredBillsNumber());
            assertEquals(96, atm.getTenBillsNumber());

            atm.retreatMoney(150);
            assertEquals(25, atm.getOneHundredBillsNumber());
            assertEquals(49, atm.getFiftyBillsNumber());

            atm.retreatMoney(950);
            assertEquals(16, atm.getOneHundredBillsNumber());
            assertEquals(48, atm.getFiftyBillsNumber());
            assertEquals(96, atm.getTenBillsNumber());
            assertEquals(98, atm.getFiveBillsNumber());
            assertEquals(93, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests with combinations of one hundred, one and fifty or ten or five")
        void checkOneHundredOneAndFiftyOrTenOrFiveCombinations() {
            Atm atm = new Atm();
            atm.retreatMoney(106);
            assertEquals(49, atm.getOneHundredBillsNumber());
            assertEquals(99, atm.getFiveBillsNumber());
            assertEquals(99, atm.getOneBillsNumber());

            atm.retreatMoney(107);
            assertEquals(48, atm.getOneHundredBillsNumber());
            assertEquals(98, atm.getFiveBillsNumber());
            assertEquals(97, atm.getOneBillsNumber());

            atm.retreatMoney(206);
            assertEquals(46, atm.getOneHundredBillsNumber());
            assertEquals(97, atm.getFiveBillsNumber());
            assertEquals(96, atm.getOneBillsNumber());

            atm.retreatMoney(111);
            assertEquals(45, atm.getOneHundredBillsNumber());
            assertEquals(99, atm.getTenBillsNumber());
            assertEquals(95, atm.getOneBillsNumber());

            atm.retreatMoney(112);
            assertEquals(44, atm.getOneHundredBillsNumber());
            assertEquals(98, atm.getTenBillsNumber());
            assertEquals(93, atm.getOneBillsNumber());

            atm.retreatMoney(231);
            assertEquals(42, atm.getOneHundredBillsNumber());
            assertEquals(95, atm.getTenBillsNumber());
            assertEquals(92, atm.getOneBillsNumber());

            atm.retreatMoney(151);
            assertEquals(41, atm.getOneHundredBillsNumber());
            assertEquals(49, atm.getFiftyBillsNumber());
            assertEquals(91, atm.getOneBillsNumber());

            atm.retreatMoney(851);
            assertEquals(33, atm.getOneHundredBillsNumber());
            assertEquals(48, atm.getFiftyBillsNumber());
            assertEquals(90, atm.getOneBillsNumber());

            atm.retreatMoney(154);
            assertEquals(32, atm.getOneHundredBillsNumber());
            assertEquals(47, atm.getFiftyBillsNumber());
            assertEquals(86, atm.getOneBillsNumber());
            assertEquals(95, atm.getTenBillsNumber());
            assertEquals(97, atm.getFiveBillsNumber());
        }

        @Test
        @DisplayName("Tests with combinations of one hundred, five and fifty or ten")
        void checkOneHundredFiveAndTenOrFiftyCombinations() {
            Atm atm = new Atm();

            atm.retreatMoney(115);
            assertEquals(49, atm.getOneHundredBillsNumber());
            assertEquals(99, atm.getTenBillsNumber());
            assertEquals(99, atm.getFiveBillsNumber());

            atm.retreatMoney(125);
            assertEquals(48, atm.getOneHundredBillsNumber());
            assertEquals(97, atm.getTenBillsNumber());
            assertEquals(98, atm.getFiveBillsNumber());

            atm.retreatMoney(515);
            assertEquals(43, atm.getOneHundredBillsNumber());
            assertEquals(96, atm.getTenBillsNumber());
            assertEquals(97, atm.getFiveBillsNumber());

            atm.retreatMoney(155);
            assertEquals(42, atm.getOneHundredBillsNumber());
            assertEquals(49, atm.getFiftyBillsNumber());
            assertEquals(96, atm.getFiveBillsNumber());

            atm.retreatMoney(255);
            assertEquals(40, atm.getOneHundredBillsNumber());
            assertEquals(48, atm.getFiftyBillsNumber());
            assertEquals(96, atm.getTenBillsNumber());
            assertEquals(95, atm.getFiveBillsNumber());
            assertEquals(100, atm.getOneBillsNumber());

        }

        @Test
        @DisplayName("Tests with combinations of one hundred, ten and fifty")
        void checkOneHundredTenAndFiftyCombinations() {
            Atm atm = new Atm();

            atm.retreatMoney(160);
            assertEquals(49, atm.getOneHundredBillsNumber());
            assertEquals(49, atm.getFiftyBillsNumber());
            assertEquals(99, atm.getTenBillsNumber());

            atm.retreatMoney(170);
            assertEquals(48, atm.getOneHundredBillsNumber());
            assertEquals(48, atm.getFiftyBillsNumber());
            assertEquals(97, atm.getTenBillsNumber());

            atm.retreatMoney(260);
            assertEquals(46, atm.getOneHundredBillsNumber());
            assertEquals(47, atm.getFiftyBillsNumber());
            assertEquals(96, atm.getTenBillsNumber());
            assertEquals(100, atm.getFiveBillsNumber());
            assertEquals(100, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests with combinations of one hundred, ten, five and one")
        void checkOneHundredTenFiveAndOneCombinations() {
            Atm atm = new Atm();

            atm.retreatMoney(116);
            assertEquals(49, atm.getOneHundredBillsNumber());
            assertEquals(99, atm.getTenBillsNumber());
            assertEquals(99, atm.getFiveBillsNumber());
            assertEquals(99, atm.getOneBillsNumber());

            atm.retreatMoney(119);
            assertEquals(48, atm.getOneHundredBillsNumber());
            assertEquals(98, atm.getTenBillsNumber());
            assertEquals(98, atm.getFiveBillsNumber());
            assertEquals(95, atm.getOneBillsNumber());

            atm.retreatMoney(136);
            assertEquals(47, atm.getOneHundredBillsNumber());
            assertEquals(95, atm.getTenBillsNumber());
            assertEquals(97, atm.getFiveBillsNumber());
            assertEquals(94, atm.getOneBillsNumber());

            atm.retreatMoney(316);
            assertEquals(44, atm.getOneHundredBillsNumber());
            assertEquals(50, atm.getFiftyBillsNumber());
            assertEquals(94, atm.getTenBillsNumber());
            assertEquals(96, atm.getFiveBillsNumber());
            assertEquals(93, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests with combinations of one hundred, fifty, five and one")
        void checkOneHundredFiftyFiveAndOneCombinations() {
            Atm atm = new Atm();

            atm.retreatMoney(156);
            assertEquals(49, atm.getOneHundredBillsNumber());
            assertEquals(49, atm.getFiftyBillsNumber());
            assertEquals(99, atm.getFiveBillsNumber());
            assertEquals(99, atm.getOneBillsNumber());

            atm.retreatMoney(159);
            assertEquals(48, atm.getOneHundredBillsNumber());
            assertEquals(48, atm.getFiftyBillsNumber());
            assertEquals(98, atm.getFiveBillsNumber());
            assertEquals(95, atm.getOneBillsNumber());

            atm.retreatMoney(456);
            assertEquals(44, atm.getOneHundredBillsNumber());
            assertEquals(47, atm.getFiftyBillsNumber());
            assertEquals(100, atm.getTenBillsNumber());
            assertEquals(97, atm.getFiveBillsNumber());
            assertEquals(94, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests with combinations of one hundred, fifty, ten and one")
        void checkOneHundredFiftyTenAndOneCombinations() {
            Atm atm = new Atm();

            atm.retreatMoney(161);
            assertEquals(49, atm.getOneHundredBillsNumber());
            assertEquals(49, atm.getFiftyBillsNumber());
            assertEquals(99, atm.getTenBillsNumber());
            assertEquals(99, atm.getOneBillsNumber());

            atm.retreatMoney(162);
            assertEquals(48, atm.getOneHundredBillsNumber());
            assertEquals(48, atm.getFiftyBillsNumber());
            assertEquals(98, atm.getTenBillsNumber());
            assertEquals(97, atm.getOneBillsNumber());

            atm.retreatMoney(171);
            assertEquals(47, atm.getOneHundredBillsNumber());
            assertEquals(47, atm.getFiftyBillsNumber());
            assertEquals(96, atm.getTenBillsNumber());
            assertEquals(96, atm.getOneBillsNumber());

            atm.retreatMoney(661);
            assertEquals(41, atm.getOneHundredBillsNumber());
            assertEquals(46, atm.getFiftyBillsNumber());
            assertEquals(95, atm.getTenBillsNumber());
            assertEquals(100, atm.getFiveBillsNumber());
            assertEquals(95, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests with combinations of one hundred, fifty, ten and five")
        void checkOneHundredFiftyTenAndFiveCombinations() {
            Atm atm = new Atm();

            atm.retreatMoney(165);
            assertEquals(49, atm.getOneHundredBillsNumber());
            assertEquals(49, atm.getFiftyBillsNumber());
            assertEquals(99, atm.getTenBillsNumber());
            assertEquals(99, atm.getFiveBillsNumber());

            atm.retreatMoney(185);
            assertEquals(48, atm.getOneHundredBillsNumber());
            assertEquals(48, atm.getFiftyBillsNumber());
            assertEquals(96, atm.getTenBillsNumber());
            assertEquals(98, atm.getFiveBillsNumber());

            atm.retreatMoney(1065);
            assertEquals(38, atm.getOneHundredBillsNumber());
            assertEquals(47, atm.getFiftyBillsNumber());
            assertEquals(95, atm.getTenBillsNumber());
            assertEquals(97, atm.getFiveBillsNumber());
            assertEquals(100, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests with combinations of one hundred, fifty, ten, five and one")
        void checkOneHundredFiftyTenFiveAndOneCombinations() {
            Atm atm = new Atm();

            atm.retreatMoney(166);
            assertEquals(49, atm.getOneHundredBillsNumber());
            assertEquals(49, atm.getFiftyBillsNumber());
            assertEquals(99, atm.getTenBillsNumber());
            assertEquals(99, atm.getFiveBillsNumber());
            assertEquals(99, atm.getOneBillsNumber());

            atm.retreatMoney(167);
            assertEquals(48, atm.getOneHundredBillsNumber());
            assertEquals(48, atm.getFiftyBillsNumber());
            assertEquals(98, atm.getTenBillsNumber());
            assertEquals(98, atm.getFiveBillsNumber());
            assertEquals(97, atm.getOneBillsNumber());

            atm.retreatMoney(176);
            assertEquals(47, atm.getOneHundredBillsNumber());
            assertEquals(47, atm.getFiftyBillsNumber());
            assertEquals(96, atm.getTenBillsNumber());
            assertEquals(97, atm.getFiveBillsNumber());
            assertEquals(96, atm.getOneBillsNumber());

            atm.retreatMoney(366);
            assertEquals(44, atm.getOneHundredBillsNumber());
            assertEquals(46, atm.getFiftyBillsNumber());
            assertEquals(95, atm.getTenBillsNumber());
            assertEquals(96, atm.getFiveBillsNumber());
            assertEquals(95, atm.getOneBillsNumber());

            atm.retreatMoney(1099);
            assertEquals(34, atm.getOneHundredBillsNumber());
            assertEquals(45, atm.getFiftyBillsNumber());
            assertEquals(91, atm.getTenBillsNumber());
            assertEquals(95, atm.getFiveBillsNumber());
            assertEquals(91, atm.getOneBillsNumber());
        }
    }

    @Nested
    @DisplayName("Check transactions when bills stock are incomplete")
    class CheckTransactionsWithIncompleteBillsStock {
        @Test
        @DisplayName("Tests 1")
        void test1() {
            Atm atm = new Atm(9, 13, 10, 0, 0);
            atm.retreatMoney(1650);
            assertEquals(0, atm.getOneHundredBillsNumber());
            assertEquals(0, atm.getFiftyBillsNumber());
            assertEquals(0, atm.getTenBillsNumber());
            assertEquals(0, atm.getFiveBillsNumber());
            assertEquals(0, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests 2")
        void test2() {
            Atm atm = new Atm(0, 0, 0, 0, 10);
            atm.retreatMoney(6);
            assertEquals(0, atm.getOneHundredBillsNumber());
            assertEquals(0, atm.getFiftyBillsNumber());
            assertEquals(0, atm.getTenBillsNumber());
            assertEquals(0, atm.getFiveBillsNumber());
            assertEquals(4, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests 3")
        void test3() {
            Atm atm = new Atm(1, 10, 1, 1, 1);
            atm.retreatMoney(300);
            assertEquals(0, atm.getOneHundredBillsNumber());
            assertEquals(6, atm.getFiftyBillsNumber());
            assertEquals(1, atm.getTenBillsNumber());
            assertEquals(1, atm.getFiveBillsNumber());
            assertEquals(1, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests 4")
        void test4() {
            Atm atm = new Atm(1, 2, 8, 3, 5);
            atm.retreatMoney(300);
            assertEquals(0, atm.getOneHundredBillsNumber());
            assertEquals(0, atm.getFiftyBillsNumber());
            assertEquals(0, atm.getTenBillsNumber());
            assertEquals(0, atm.getFiveBillsNumber());
            assertEquals(0, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests 5")
        void test5() {
            Atm atm = new Atm(1, 1, 0, 10, 1);
            atm.retreatMoney(20);
            assertEquals(1, atm.getOneHundredBillsNumber());
            assertEquals(1, atm.getFiftyBillsNumber());
            assertEquals(0, atm.getTenBillsNumber());
            assertEquals(6, atm.getFiveBillsNumber());
            assertEquals(1, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests 6")
        void test6() {
            Atm atm = new Atm(1, 1, 10, 0, 10);
            atm.retreatMoney(37);
            assertEquals(1, atm.getOneHundredBillsNumber());
            assertEquals(1, atm.getFiftyBillsNumber());
            assertEquals(7, atm.getTenBillsNumber());
            assertEquals(0, atm.getFiveBillsNumber());
            assertEquals(3, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests 7")
        void test7() {
            Atm atm = new Atm(1, 0, 10, 0, 1);
            atm.retreatMoney(70);
            assertEquals(1, atm.getOneHundredBillsNumber());
            assertEquals(0, atm.getFiftyBillsNumber());
            assertEquals(3, atm.getTenBillsNumber());
            assertEquals(0, atm.getFiveBillsNumber());
            assertEquals(1, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests 8")
        void test8() {
            Atm atm = new Atm(1, 0, 5, 2, 10);
            atm.retreatMoney(70);
            assertEquals(1, atm.getOneHundredBillsNumber());
            assertEquals(0, atm.getFiftyBillsNumber());
            assertEquals(0, atm.getTenBillsNumber());
            assertEquals(0, atm.getFiveBillsNumber());
            assertEquals(0, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests 9")
        void test9() {
            Atm atm = new Atm(0, 5, 10, 0, 10);
            atm.retreatMoney(325);
            assertEquals(0, atm.getOneHundredBillsNumber());
            assertEquals(0, atm.getFiftyBillsNumber());
            assertEquals(3, atm.getTenBillsNumber());
            assertEquals(0, atm.getFiveBillsNumber());
            assertEquals(5, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests 10")
        void test10() {
            Atm atm = new Atm(4, 3, 10, 10, 15);
            atm.retreatMoney(715);
            assertEquals(0, atm.getOneHundredBillsNumber());
            assertEquals(0, atm.getFiftyBillsNumber());
            assertEquals(0, atm.getTenBillsNumber());
            assertEquals(0, atm.getFiveBillsNumber());
            assertEquals(0, atm.getOneBillsNumber());
        }
    }

    @Nested
    @DisplayName("Negative tests")
    class CheckNegativeTests {
        @Test
        @DisplayName("Tests 1")
        void test1() {
            Atm atm = new Atm();
            atm.retreatMoney(0);
            assertEquals(50, atm.getOneHundredBillsNumber());
            assertEquals(50, atm.getFiftyBillsNumber());
            assertEquals(100, atm.getTenBillsNumber());
            assertEquals(100, atm.getFiveBillsNumber());
            assertEquals(100, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests 2")
        void test2() {
            Atm atm = new Atm();
            atm.retreatMoney(-1);
            assertEquals(50, atm.getOneHundredBillsNumber());
            assertEquals(50, atm.getFiftyBillsNumber());
            assertEquals(100, atm.getTenBillsNumber());
            assertEquals(100, atm.getFiveBillsNumber());
            assertEquals(100, atm.getOneBillsNumber());
        }

        @Test
        @DisplayName("Tests 3")
        void test3() {
            Atm atm = new Atm();
            atm.retreatMoney(-50);
            assertEquals(50, atm.getOneHundredBillsNumber());
            assertEquals(50, atm.getFiftyBillsNumber());
            assertEquals(100, atm.getTenBillsNumber());
            assertEquals(100, atm.getFiveBillsNumber());
            assertEquals(100, atm.getOneBillsNumber());
        }
    }
}
