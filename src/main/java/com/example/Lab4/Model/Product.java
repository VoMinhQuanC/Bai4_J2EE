package com.example.Lab4.Model;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @Length(min = 0, max = 200, message = "Tên hình ảnh không được quá 200 kí tự")
    private String image;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @Min(value = 1, message = "Giá sản phẩm không được nhỏ hơn 1")
    @Max(value = 999999999, message = "Giá sản phẩm không được lớn hơn 999999999")
    private Long price;

    private Category category;
}
