import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BudgetServiceTest {
    @Test
    void testZeroBudget() {
        BudgetService budgetService = new BudgetService();
        assertEquals(0, budgetService.totalAmount());
    }
}