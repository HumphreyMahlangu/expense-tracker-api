package za.ac.mycput.expensetrackerapi.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.mycput.expensetrackerapi.model.Expense;
import za.ac.mycput.expensetrackerapi.repository.ExpenseRepository;

import java.util.List;

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
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseRepository.save(expense);
    }
}