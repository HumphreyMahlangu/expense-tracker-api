package za.ac.mycput.expensetrackerapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;



@Entity // Tells Spring this class is a database table
@Table(name = "expenses") // Links this to the expenses table
public class Expense {

    @Id //THis is a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID must be auto increamented
    private Long id;

    @NotBlank(message = "Description cannot be empty") // description can't be null or whitespace
    @Column(name = "description", nullable = false) //name must not be null
    private String description;

    @NotNull(message = "Amount cannot be null") // amount must be provided
    @Positive(message = "Amount must be positive") // amount must be > 0
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @NotNull(message = "Date cannot be null") // date must be provided
    @PastOrPresent(message = "Date cannot be in the future") // date must be today or in the past
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "category_id") // This is the foreign key column we created
    private Category category;

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
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}