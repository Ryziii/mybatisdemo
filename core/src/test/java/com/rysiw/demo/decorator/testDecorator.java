package com.rysiw.demo.decorator;

import com.rysiw.demo.decorator.carSample.AUDICar;
import com.rysiw.demo.decorator.carSample.BMWCar;
import com.rysiw.demo.decorator.carSample.CarIm;
import com.rysiw.demo.decorator.carSample.Person;

/**
 * @author Rysiw
 * @date 2022/7/22 15:38
 */
public class testDecorator {

    public static void main(String[] args) {
        TextNode textNode = null;
        textNode = new SpanText();
        textNode = new BoldText(textNode.getText());
        textNode.setText("123");
        System.out.println(textNode.getText());

        TextNode textNode1 = null;
        textNode1 = new ItalicDecorator(new BoldDecorator(new SpanText()));
        textNode1.setText("123");
//        System.out.println(((ItalicDecorator) textNode1).italicSay());
//        System.out.println(((BoldDecorator) textNode1).boldSay());
        System.out.println(textNode1.getText());

        CarIm carIm = new AUDICar(new BMWCar(new Person()));
        carIm.setBrand("a");
        System.out.println();
        System.out.println(carIm.getBrand());
    }
}
