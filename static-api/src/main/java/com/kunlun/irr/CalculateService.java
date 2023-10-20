package com.kunlun.irr;

import java.util.ArrayList;
import java.util.List;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.irr
 * @author: Xiang想
 * @createTime: 2023-10-11  17:47
 * @description: TODO
 * @version: 1.0
 */
public class CalculateService {

    public List<Double> getIrrValue(IRR irr) throws Exception {
        Double annualPremium = irr.getAnnualPremium();

        Double fixedCash = irr.getFixedCash();

        List<Double> doubleList = new ArrayList<>();
        for (int i = 0; i < irr.getPolicyYear() ; i++) {
            if (i>= irr.getPaymentPeriod() ) {
                doubleList.add(0d);
            }else{
                doubleList.add(annualPremium * -1);
            }
        }
        doubleList.add(fixedCash);
        return doubleList;
    }



}
