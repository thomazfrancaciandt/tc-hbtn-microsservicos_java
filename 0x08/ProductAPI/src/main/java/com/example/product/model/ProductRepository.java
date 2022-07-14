package com.example.product.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final List<Product> list = new ArrayList<>();

    public List<Product> getAllProducts() {
        return list;
    }

    public Product getProductById(Long id) {
        List<Product> result = list.stream().filter(product -> product.getId().equals(id)).collect(Collectors.toList());
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public void addProduct(Product s) {
        if (!list.contains(s)) {
            list.add(s);
        }
    }

    public void updateProduct(Product s) {
        Product productId = getProductById((long) Math.toIntExact(s.getId()));
        if (list.contains(s)) {
            int index = list.indexOf(productId);
            list.set(index, s);
        }
    }

    public void removeProduct(Product s) {
        Product productId = getProductById((long) Math.toIntExact(s.getId()));
        if (list.contains(s)) {
            list.remove(productId);
        }
    }

    public void addList(List<Product> listOfProducts) {
        List<List<Product>> newlist = new ArrayList<>();
        if (!newlist.contains(listOfProducts)) {
            newlist.add(listOfProducts);
        }
    }
}