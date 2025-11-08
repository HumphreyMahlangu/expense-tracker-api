package za.ac.mycput.expensetrackerapi.model;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity // Tells Spring this class is a database table
@Table(name = "expenses") // Links this to the expenses table
public class Expense {

    @Id //THis is a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID must be auto increamented
    private Long id;

    @Column(name = "description", nullable = false) //name must not be null
    private String description;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    //deafault constructor
    public Expense() {
    }


    public Expense(String description, BigDecimal amount, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}