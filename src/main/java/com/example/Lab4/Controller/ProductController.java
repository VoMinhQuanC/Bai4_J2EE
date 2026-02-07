package com.example.Lab4.Controller;

import com.example.Lab4.Model.Category;
import com.example.Lab4.Model.Product;
import com.example.Lab4.Service.CategoryService; // Bạn cần tự tạo class này
import com.example.Lab4.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService; // Lưu ý: Code CategoryService chưa có trong ảnh

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("listProduct", productService.getAll());
        return "product/products";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAll());
        return "product/create";
    }

    @PostMapping("/create")
    public String create(@Valid Product newProduct, BindingResult result,
                         @RequestParam("imageProduct") MultipartFile imageProduct, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", newProduct);
            model.addAttribute("categories", categoryService.getAll());
            return "product/create";
        }

        productService.updateImage(newProduct, imageProduct); // Xử lý ảnh

        // Đoạn này trong slide logic hơi lạ (get category by ID rồi set lại),
        // nhưng mình viết đúng theo slide:
        // Lấy ID trực tiếp từ sản phẩm đang tạo (nhờ file Model của bạn có getId() nên lệnh này mới chạy dc)
        Category selectedCategory = categoryService.get(newProduct.getCategory().getId());
         newProduct.setCategory(selectedCategory);

        productService.add(newProduct);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Product find = productService.get(id);
        if (find == null) {
            return "error/404"; // Trang lỗi tùy chỉnh
        }
        model.addAttribute("product", find);
        model.addAttribute("categories", categoryService.getAll());
        return "product/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid Product editProduct,
                       BindingResult result,
                       @RequestParam("imageProduct") MultipartFile imageProduct,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", editProduct);
            model.addAttribute("categories", categoryService.getAll());
            return "product/edit";
        }

        if (imageProduct != null && !imageProduct.isEmpty()) {
            productService.updateImage(editProduct, imageProduct); // Cập nhật ảnh nếu có
        }

        productService.update(editProduct); // Cập nhật sản phẩm
        return "redirect:/products";
    }
}