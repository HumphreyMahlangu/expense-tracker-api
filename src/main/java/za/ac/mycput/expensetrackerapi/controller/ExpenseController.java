package za.ac.mycput.expensetrackerapi.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.mycput.expensetrackerapi.model.Expense;
import za.ac.mycput.expensetrackerapi.service.ExpenseService;
import za.ac.mycput.expensetrackerapi.repository.ExpenseRepository;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/expenses") // Base url for all the mthods in the class
public class ExpenseController {


    @Autowired
    private ExpenseService expenseService;

    //Method that handles Https get requests to find all expenses to "api/expenses"
    @GetMapping("/total")
    public BigDecimal getTotalExpenses() {
        return expenseService.calculateTotalExpenses();
    }
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses(); // Call service
    }

    //method that handles Http POST requests to add expenses to "/api/expenses/"
    @PostMapping
    public Expense createExpense(@Valid @RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Optional<Expense> expense = expenseService.getExpenseById(id);

        if (expense.isPresent()) {
            return ResponseEntity.ok(expense.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id,@Valid @RequestBody Expense expenseDetails) {
        Optional<Expense> optionalExpense = expenseService.getExpenseById(id);

        if (optionalExpense.isPresent()) {
            Expense existingExpense = optionalExpense.get();


            existingExpense.setDescription(expenseDetails.getDescription());
            existingExpense.setAmount(expenseDetails.getAmount());
            existingExpense.setDate(expenseDetails.getDate());


            Expense updatedExpense = expenseService.saveExpense(existingExpense);
            return ResponseEntity.ok(updatedExpense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        Optional<Expense> expense = expenseService.getExpenseById(id);

        if (expense.isPresent()) {
            expenseService.deleteExpense(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}