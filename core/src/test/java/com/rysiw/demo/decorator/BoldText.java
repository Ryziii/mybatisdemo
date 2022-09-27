package com.rysiw.demo.decorator;

/**
 * @author Rysiw
 * @date 2022/7/22 15:37
 */
public class BoldText implements TextNode{
    private String text;

    public BoldText(){};

    public BoldText(String text){
        this.text = text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return "<b>"+ this.text + "</b>";
    }
}
