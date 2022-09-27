package com.rysiw.demo.decorator.carSample;

/**
 * @author Rysiw
 * @date 2022/7/22 17:17
 */
public class BMWCar extends CarDecorator{

    public BMWCar(CarIm carIm) {
        super(carIm);
    }

    @Override
    public void setBrand(String brand) {
        brand = self(brand);
        carIm.setBrand(brand);
    }

    private String self(String brand) {
        return "BMW" + brand;
    }


}
