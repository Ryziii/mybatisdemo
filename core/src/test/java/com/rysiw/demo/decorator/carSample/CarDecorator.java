package com.rysiw.demo.decorator.carSample;

/**
 * @author Rysiw
 * @date 2022/7/22 17:15
 */
public abstract class CarDecorator implements CarIm{

    protected CarIm carIm;

    protected CarDecorator(CarIm carIm) {
        this.carIm = carIm;
    }

    @Override
    public String getBrand(){
        return carIm.getBrand();
    }
}
