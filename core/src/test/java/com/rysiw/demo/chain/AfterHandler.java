package com.rysiw.demo.chain;

/**
 * @author Rysiw
 * @date 2022/7/23 02:00
 */
//@Component
public class AfterHandler extends Handler<ChainEntity>{
    @Override
    public void doHandler(ChainEntity context) {
        System.out.println("after");
        if(null!=next){
            next.doHandler(context);
        }
    }
}
