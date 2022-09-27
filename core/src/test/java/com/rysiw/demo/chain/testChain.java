package com.rysiw.demo.chain;

/**
 * @author Rysiw
 * @date 2022/7/23 02:00
 */
public class testChain {

    public static void main(String[] args) {
//        builder方式
        Handler.Builder<ChainEntity> builder = new Handler.Builder<>();
        builder.addHandler(new PreHandler()).addHandler(new AHandler()).addHandler(new BHandler()).addHandler(new AfterHandler());
        builder.build().doHandler(new ChainEntity());
        System.out.println();

//        setNext方式
//        Handler<ChainEntity> preHandler = new PreHandler();
//        Handler<ChainEntity> aHandler = new AHandler();
//        preHandler.setNext(aHandler);
//        Handler<ChainEntity> bHandler = new BHandler();
//        aHandler.setNext(bHandler);
//        AfterHandler afterHandler = new AfterHandler();
//        bHandler.setNext(afterHandler);
//        preHandler.doHandler(new ChainEntity());
//        System.out.println();
//        aHandler.doHandler(new ChainEntity());


    }

}
