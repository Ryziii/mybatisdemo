package com.rysiw.demo.decorator;

/**
 * @author Rysiw
 * @date 2022/7/22 15:47
 */
public class BoldDecorator extends NodeDecorator{

    public BoldDecorator(TextNode target) {
        super(target);
    }

    public String getText() {
        System.out.println("b");
        return target.getText();
    }

    public String boldSay(){
        return "boldSay";
    }
}
