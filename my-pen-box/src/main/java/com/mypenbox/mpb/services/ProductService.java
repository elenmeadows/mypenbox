package com.mypenbox.mpb.services;

import com.mypenbox.mpb.models.Product;
import com.mypenbox.mpb.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public Page<Product> listAll(int pageNum, String sortField, String sortDir, String keyword) {

        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());

        if (keyword != null) {
            return repo.findAll(keyword, pageable);
        }

        return repo.findAll(pageable);
    }


//    public void save(Product product) {
//        repo.save(product);
//    }
//
//    public Product get(Long id) {
//        return repo.findById(id).get();
//    }
//
//    public void delete(long id) {
//        repo.deleteById(id);
//    }
}
