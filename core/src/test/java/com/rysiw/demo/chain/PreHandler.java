package com.rysiw.demo.chain;

/**
 * @author Rysiw
 * @date 2022/7/23 01:55
 */
//@Component
public class PreHandler extends Handler<ChainEntity>{

    @Override
    public void doHandler(ChainEntity context) {
        System.out.println("pre");
        if(null!=next){
            next.doHandler(context);
        }
    }
}
