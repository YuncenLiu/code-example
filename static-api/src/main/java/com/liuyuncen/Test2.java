package com.liuyuncen;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2023-10-12  18:08
 * @description: TODO
 * @version: 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        ExpUser e1 = new ExpUser("a", "b");
        ExpUser e2 = new ExpUser("b", "c");
        ExpUser e3 = new ExpUser("a", "c");
        List<ExpUser> expUsers = Arrays.asList(e1, e2, e3);

//        CopyOnWriteArrayList<ExpUser> users = new CopyOnWriteArrayList<>(expUsers);
//
//        // 存放重复的 userId
//        ConcurrentMap<String,ExpUser> map = new ConcurrentHashMap<>();
//        users.forEach(u -> {
//            if (!map.containsKey(u.getUserId())) {
//                map.put(u.getUserId(),u);
//            }else{
//                String oldOrgName = map.get(u.getUserId()).getOrgName();
//                u.setOrgName(oldOrgName + "," + u.getOrgName());
//                map.put(u.getUserId(),u);
//            }
//        });
//
//        map.forEach((k,v) -> {
//            System.out.println("k = " + k + ", v = " + v.toString());
//        });


        List<ExpUser> mergedUsers = expUsers.stream()
                .collect(Collectors.groupingBy(ExpUser::getUserId))
                .entrySet().stream()
                .map(entry -> new ExpUser(
                        entry.getKey(),
                        String.join(",", entry.getValue().stream().map(ExpUser::getOrgName).collect(Collectors.toSet()))
                ))
                .collect(Collectors.toList());

        for (ExpUser mergedUser : mergedUsers) {
            System.out.println(mergedUser.toString());
        }
    }
}

class ExpUser{
    private String userId;
    private String orgName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public ExpUser(String userId, String orgName) {
        this.userId = userId;
        this.orgName = orgName;
    }

    @Override
    public String toString() {
        return "ExpUser{" +
                "userId='" + userId + '\'' +
                ", orgName='" + orgName + '\'' +
                '}';
    }
}
