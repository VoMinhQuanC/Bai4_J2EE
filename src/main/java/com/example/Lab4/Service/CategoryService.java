package com.example.Lab4.Service;

import com.example.Lab4.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private List<Category> listCategory = new ArrayList<>();

    public CategoryService() {
        // Vẫn giữ 2 cái này để lúc chạy lên có dữ liệu test ngay
        listCategory.add(new Category(1, "Điện thoại"));
        listCategory.add(new Category(2, "Laptop"));
    }

    public List<Category> getAll() {
        return listCategory;
    }

    public Category get(int id) {
        return listCategory.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // --- HÀM MỚI THÊM: Cho phép nhập thêm danh mục ---
    public void add(Category newCategory) {
        // Tự động tăng ID (tìm ID lớn nhất hiện tại + 1)
        int maxId = listCategory.stream()
                .mapToInt(Category::getId)
                .max()
                .orElse(0);
        newCategory.setId(maxId + 1);

        // Thêm vào danh sách
        listCategory.add(newCategory);
    }
}