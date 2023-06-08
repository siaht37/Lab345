package com.example.demo.entity;

import com.example.demo.validator.annotation.ValidCategoryId;
import com.example.demo.validator.annotation.ValidUserId;
import com.example.demo.validator.annotation.ValidUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name ="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Tiêu đề không được để trống")
    @Size(max = 50, min =1, message = "Tiêu đề phải ít hơn 50 ký tự")
    private String title;

    private String author;

    @NotNull(message = "Price is required")
    private Double price;

    @ManyToOne
    @JoinColumn(name="category_id")
    @ValidCategoryId
    private Category category;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    @ValidUserId
    private User user;
}
