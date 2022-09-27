package com.rysiw.demo.decorator;

/**
 * @author Rysiw
 * @date 2022/7/22 15:36
 */
public class SpanText implements TextNode{
    private String text;

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return "<s>" + this.text + "</s>";
    }
}
