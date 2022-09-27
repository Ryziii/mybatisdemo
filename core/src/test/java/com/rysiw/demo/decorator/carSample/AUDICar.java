package com.rysiw.demo.decorator.carSample;

/**
 * @author Rysiw
 * @date 2022/7/22 17:19
 */
public class AUDICar extends CarDecorator{

    public AUDICar(CarIm carIm) {
        super(carIm);
    }

    @Override
    public void setBrand(String brand) {
        brand = self(brand);
        carIm.setBrand(brand);
    }

    private String self(String brand) {
        return "AUDI" + brand;
    }

}
