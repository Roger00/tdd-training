public class Budget {
    private final String yearMonth;
    private final int amount;

    public String getYearMonth() {
        return yearMonth;
    }

    public int getAmount() {
        return amount;
    }

    public Budget(String yearMonth, int amount) {
        this.yearMonth = yearMonth;
        this.amount = amount;
    }
}
