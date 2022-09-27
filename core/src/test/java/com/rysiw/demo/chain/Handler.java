package com.rysiw.demo.chain;

/**
 * @author Rysiw
 * @date 2022/7/23 01:52
 */
public abstract class Handler<T> {

    protected Handler<T> next;

    public void setNext(Handler<T> next) {
        this.next = next;
    }

    public void next(Handler<T> next) {
        this.next = next;
    }

    public abstract void doHandler(T context);

    public static class Builder<T> {
        private Handler<T> realChain;

        private Handler<T> tailIndex;

        public Builder<T> addHandler(Handler<T> handler){
            if(null == this.realChain){
                this.realChain = this.tailIndex = handler;
                return this;
            }
            this.tailIndex.next(handler);
            this.tailIndex = handler;
            return this;
        }

        public Handler<T> build(){
            return this.realChain;
        }
    }
}
