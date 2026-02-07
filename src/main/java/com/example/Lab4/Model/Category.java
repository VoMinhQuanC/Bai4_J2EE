package com.example.Lab4.Model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Integer id;

    @NotBlank(message = "Tên danh muc không được để trống")
    private String name;
}
