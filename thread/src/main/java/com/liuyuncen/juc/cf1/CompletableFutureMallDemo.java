package com.liuyuncen.juc.cf1;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 案例：比价需求 提前问题扫清
 *
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.juc.cf
 * @author: Xiang想
 * @createTime: 2024-08-22  10:53
 * @description: TODO
 * @version: 1.0
 */
public class CompletableFutureMallDemo {

    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dd"),
            new NetMall("tb"),
            new NetMall("pdd"),
            new NetMall("tm"),
            new NetMall("wy")
    );

    /**
     * @description: step by step 一家一家搜
     * @author: Xiang想
     * @date: 2024/8/22 11:09
     * @param: [list, productName]
     * @return: java.util.List<java.lang.String>
     **/
    public static List<String> getPrice(List<NetMall> list, String productName) {
        return list
                .stream()
                .map(netMall ->
                        String.format(productName + "in %s price is %.2f",
                                netMall.getNetMallName(),
                                netMall.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    /**
     * @description: 使用 CompletableFuture 并行获取
     * @author: Xiang想
     * @date: 2024/8/22 11:09
     * @param: [list, productName]
     * @return: java.util.List<java.lang.String>
     **/
    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {
        return list
                .stream()
                .map(netMall ->
                        CompletableFuture.supplyAsync(() ->
                                String.format(productName + " in %s price is %.2f",
                                        netMall.getNetMallName(),
                                        netMall.calcPrice(productName))))
                .collect(Collectors.toList())
                .stream().map(CompletableFuture::join)
                .collect(Collectors
                        .toList());
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (String mysql : getPriceByCompletableFuture(list, "mysql")) {
            System.out.println(mysql);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("--- costTime " + (endTime - startTime) + "ms");
    }
}

class NetMall {
    @Getter
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}
