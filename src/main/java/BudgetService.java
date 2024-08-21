import java.time.LocalDate;
import java.time.Period;
import java.util.stream.Collectors;
import java.util.Map;

public class BudgetService {

    private final BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {

        this.budgetRepo = budgetRepo;
    }

    private boolean isCrossMonths(LocalDate start, LocalDate end) {
        if(start.getMonthValue() != end.getMonthValue() || start.getYear() != end.getYear()) {
            return true;
        }
        return false;
    }

    public int totalAmount(LocalDate start, LocalDate end) {
        if(end.isBefore(start)) {
            return 0;
        }
        if(isCrossMonths(start, end)) {
            Period startMonthPeriod = Period.between(start, start.withDayOfMonth(start.lengthOfMonth()));
            Integer startMonthBudget = getMonthBudget(start);
            Integer startBudget = (startMonthPeriod.getDays()+1) * (startMonthBudget/start.lengthOfMonth());

            Period endMonthPeriod = Period.between(end.withDayOfMonth(1), end);
            Integer endMonthBudget = getMonthBudget(end);
            Integer endBudget = (endMonthPeriod.getDays()+1) * (endMonthBudget/end.lengthOfMonth());

            return startBudget+endBudget;

        }

        Period period = Period.between(start, end);
        Integer monthBudget = getMonthBudget(start);
        return (period.getDays()+1) * (monthBudget/start.lengthOfMonth());
    }

    private Integer getMonthBudget(LocalDate date) {
        Map<String, Integer> map = budgetRepo.getAll().stream().collect(Collectors.toMap(Budget::getYearMonth, Budget::getAmount));
        Integer monthBudget = 0;
        if(!map.isEmpty()){
            monthBudget = map.get(String.format("%04d%02d", date.getYear(), date.getMonthValue()));
        }
        return monthBudget;
    }
}

