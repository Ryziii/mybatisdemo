package com.rysiw.demo.decorator;

/**
 * @author Rysiw
 * @date 2022/7/22 15:44
 */
public abstract class NodeDecorator implements TextNode{

    protected final TextNode target;

    public NodeDecorator(TextNode target) {
        this.target = target;
    }

    public void setText(String text) {
        this.target.setText(text);
    }
}
