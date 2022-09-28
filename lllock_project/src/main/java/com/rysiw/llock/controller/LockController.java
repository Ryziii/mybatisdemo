package com.rysiw.llock.controller;

import com.rysiw.llock.pojo.Stock;
import com.rysiw.llock.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Rysiw
 * @date 2022/9/28 09:57
 */
@RestController
@RequestMapping("/lock")
public class LockController {

    @Resource
    private ProductService productService;

    @GetMapping("/get")
    public String getStock(){
        return productService.buyProduct();
    }

}
