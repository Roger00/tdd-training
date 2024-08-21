import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;


class BudgetServiceTest {
    FakeBudgetRepo budgetRepo = new FakeBudgetRepo();
    BudgetService budgetService = new BudgetService(budgetRepo);

    @Test
    void testZeroBudget() {
        givenBudgets(asList());
        assertEquals(0, budgetService.totalAmount(LocalDate.now(), LocalDate.now()));
    }

    private void givenBudgets(List<Budget> budgets) {
        budgetRepo.setBudgets(budgets);
    }

    @Test
    void testInvalidPeriod(){
        givenBudgets(asList(new Budget("202408", 31000)));
        assertEquals(0, budgetService.totalAmount(LocalDate.of(2024,8,2), LocalDate.of(2024,8,1)));
    }

    @Test
    void testSameMonth(){
        givenBudgets(asList(new Budget("202408", 31000)));
        assertEquals(1000, budgetService.totalAmount(LocalDate.of(2024,8,1), LocalDate.of(2024,8,1)));
    }

    @Test
    void testCross2Months(){
        givenBudgets(asList(new Budget("202408", 31000),new Budget("202409", 60000)));
        assertEquals(3000, budgetService.totalAmount(LocalDate.of(2024,8,31), LocalDate.of(2024,9,1)));
    }

    @Test
    void testCross3Months(){
        givenBudgets(asList(new Budget("202408", 31000),new Budget("202409", 60000),new Budget("202410", 93000)));
        assertEquals(64000, budgetService.totalAmount(LocalDate.of(2024,8,31), LocalDate.of(2024,10,1)));
    }

}