package puzzle;
public class LongDivision {
    public static void main(String[] args) {
        final long MICROS_PER_DAY = 24L * 60L * 60L * 1000L * 1000L;
        final long MILLIS_PER_DAY = 24L * 60L * 60L * 1000L;

        // HINT: 1000
        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);
    }
}
