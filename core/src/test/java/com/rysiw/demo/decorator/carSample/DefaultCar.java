package com.rysiw.demo.decorator.carSample;

/**
 * @author Rysiw
 * @date 2022/7/22 17:27
 */
public class DefaultCar implements CarIm {

    private String brand;

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }
}
