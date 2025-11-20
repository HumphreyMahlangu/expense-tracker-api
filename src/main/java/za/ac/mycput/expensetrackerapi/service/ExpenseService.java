package za.ac.mycput.expensetrackerapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.mycput.expensetrackerapi.model.Expense;
import za.ac.mycput.expensetrackerapi.repository.ExpenseRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }


    public BigDecimal calculateTotalExpenses() {
        BigDecimal total = expenseRepository.selectTotalAmount();
        return total != null ? total : BigDecimal.ZERO;
    }
}