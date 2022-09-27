package com.rysiw.demo.decorator.carSample;

/**
 * @author Rysiw
 * @date 2022/7/22 17:11
 */
public class Person implements CarIm{

    private String carBrand;


    @Override
    public void setBrand(String brand) {
        this.carBrand = brand;
    }

    @Override
    public String getBrand() {
        return this.carBrand;
    }

}
