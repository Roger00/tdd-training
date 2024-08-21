import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class BudgetRepo {
    private List<Budget> budgets = new ArrayList<>();

    public List<Budget> getAll() {
        return this.budgets;
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }
}
