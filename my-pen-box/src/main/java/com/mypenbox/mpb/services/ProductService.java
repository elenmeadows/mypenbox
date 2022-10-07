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
import java.util.stream.Stream;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public Page<Product> listAll(int pageNum, String sortField, String sortDir, String keyword) {

        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending().and(Sort.by(Sort.Direction.ASC, "id"))
                        : Sort.by(sortField).descending().and(Sort.by(Sort.Direction.DESC, "id")));

        if (keyword != null) {
            return repo.findAll(keyword, pageable);
        }

        return repo.findAll(pageable);
    }

    public List<Product> modalFindAll(String sortField, String sortDir, String keyword) {

        Sort modalSorting = sortDir.equals("asc") ? Sort.by(sortField).ascending().and(Sort.by(Sort.Direction.ASC, "id"))
                        : Sort.by(sortField).descending().and(Sort.by(Sort.Direction.DESC, "id"));

        if (keyword != null) {
            return repo.findAll(keyword, modalSorting);
        }

        return repo.findAll(modalSorting);
    }

    public Product getProductById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public void save(Product product) {
        repo.save(product);
    }
}
