package com.commerce.controller;

import com.commerce.entity.Product;
import com.commerce.repository.ProductRepository;
import com.commerce.vo.ResponseVo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jinwoo on 2017. 6. 26..
 */
@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;


    @RequestMapping(value="/api/product", method= RequestMethod.GET)
    @ResponseBody
    public ProductListVo join(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pagesize , Model model) {


        ProductListVo productListVo = new ProductListVo();

        PageRequest request = new PageRequest(page - 1, pagesize, Sort.Direction.DESC, "id");
        Page<Product> productPage = productRepository.findAll(request);

        productListVo.setResultcode("200");
        productListVo.setPageSize(pagesize);
        productListVo.setFullListSize(productPage.getTotalPages());
        productListVo.setList(productPage.getContent());

        return productListVo;

    }

    @RequestMapping(value="/api/product/{id}", method= RequestMethod.GET)
    @ResponseBody
    public ProductVo join(@PathVariable Long id , Model model) {
      ProductVo productVo = new ProductVo();

      Product product = productRepository.findOne(id);

      productVo.setName(product.getName());
      productVo.setId(product.getId());
      productVo.setPrice(product.getPrice());
      productVo.setImageUrl(product.getImageFileName());
      productVo.setDescription(product.getDescription());
      productVo.setColor(product.getColor());

      return productVo;

    }



    @Getter
    @Setter
    public static class ProductListVo extends ResponseVo {
        private Integer page;
        private Integer pageSize;
        private Integer fullListSize;
        private List<Product> list;
    }

    @Getter
    @Setter
    public static class ProductVo extends ResponseVo {
        private String message;
        private Long id;
        private String name;
        private Double price;
        private String imageUrl;
        private String description;
        private String color;
    }

}




