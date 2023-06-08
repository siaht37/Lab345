package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.services.BookService;
import com.example.demo.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("/add")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, Model model) {
        //Trường hợp có lỗi ràng buộc thì trả lại view add
        if (bindingResult.hasErrors()) {
            model.addAttribute("category", new Category());
            return "category/add";
        }
        categoryService.addCategory(category);
        return"redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        Category category = categoryService.getCategoriesById(id);
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable("id") Long id, Model model) {
        Category editCategory = categoryService.getCategoriesById(id);
        if(editCategory !=null){
            model.addAttribute("category", editCategory);
            return "category/edit";
        }else {
            return "not-found";
        }

    }



    @PostMapping("/edit")
    public String editCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, Model model) {
        // Trường hợp có lỗi ràng buộc thì trả lại view edit
        if (bindingResult.hasErrors()) {
            model.addAttribute("category", new Category());
            return "category/edit";
        }
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }
}
