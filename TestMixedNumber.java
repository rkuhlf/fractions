public class TestMixedNumber {
    public static void main(String[] args) {
        testAddition();
    }

    public static void testNullInputsConstructors() {

    }

    public static void testAddition() {
        MixedNumber m1 = new MixedNumber(-1, -2, 3);
        MixedNumber m2 = new MixedNumber(0, 2, 3);
        System.out.println(m1.add(m2) + " == -1");

        MixedNumber m3 = new MixedNumber(-14, 0, 0);
        MixedNumber m4 = new MixedNumber(14, 0, 0);
        System.out.println(m3.add(m4) + " == 0");

        MixedNumber m5 = new MixedNumber(0, 4, 10);
        MixedNumber m6 = new MixedNumber(-1, 0, 0);
        System.out.println(m5.add(m6) + " == -3/5");

        MixedNumber m7 = new MixedNumber(0, 14, 10);
        MixedNumber m8 = new MixedNumber(-3, 0, 0);
        System.out.println(m7.add(m8) + " == -1 3/5");
    }
}