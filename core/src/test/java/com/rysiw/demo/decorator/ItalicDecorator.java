package com.rysiw.demo.decorator;

/**
 * @author Rysiw
 * @date 2022/7/22 15:47
 */
public class ItalicDecorator extends NodeDecorator{

    public ItalicDecorator(TextNode target) {
        super(target);
    }

    public String getText() {
        System.out.println("i");
        return target.getText();
    }

    public String italicSay(){
        return "italicSay";
    }
}
