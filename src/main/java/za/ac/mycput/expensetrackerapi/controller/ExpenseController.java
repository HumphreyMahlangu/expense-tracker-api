package za.ac.mycput.expensetrackerapi.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.mycput.expensetrackerapi.model.Expense;
import za.ac.mycput.expensetrackerapi.repository.ExpenseRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses") // Base url for all the mthods in the class
public class ExpenseController {


    @Autowired
    private ExpenseRepository expenseRepository;

    //Method that handles Https get requests to find all expenses to "api/expenses"
    @GetMapping
    public List<Expense> getAllExpenses() {
        // I use a built in .finndAll() method
        return expenseRepository.findAll();
    }

    //method that handles Http POST requests to add expenses to "/api/expenses/"
    @PostMapping
    public Expense createExpense(@Valid @RequestBody Expense expense) {
        return expenseRepository.save(expense);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        // Find the expense by its ID
        Optional<Expense> expense = expenseRepository.findById(id);

        // checking if the expense is found
        if (expense.isPresent()) {
            return ResponseEntity.ok(expense.get()); // Return 200 OK with the expense
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found
        }
    }


    //handles Https PUTT requests to update the expenses
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id,@Valid @RequestBody Expense expenseDetails) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);

        if (optionalExpense.isPresent()) {
            Expense existingExpense = optionalExpense.get();

            // Update the fields of the existing expense
            existingExpense.setDescription(expenseDetails.getDescription());
            existingExpense.setAmount(expenseDetails.getAmount());
            existingExpense.setDate(expenseDetails.getDate());

            // Save the updated expense back to the database
            Expense updatedExpense = expenseRepository.save(existingExpense);
            return ResponseEntity.ok(updatedExpense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Delete method to handle delete HTTP requests to "/api/exenses/{id}"
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);

        if (optionalExpense.isPresent()) {
            //if the expense exists it gets deleted
            expenseRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Return 204 No Content
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}