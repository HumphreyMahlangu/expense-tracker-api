package za.ac.mycput.expensetrackerapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.mycput.expensetrackerapi.model.Category;
import za.ac.mycput.expensetrackerapi.repository.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/api/categories") // Bas url
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // get all categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // create a new category
    @PostMapping
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryRepository.save(category);
    }

    // delete teh category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}