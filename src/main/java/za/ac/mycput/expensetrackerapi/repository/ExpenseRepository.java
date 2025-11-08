package za.ac.mycput.expensetrackerapi.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.mycput.expensetrackerapi.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}