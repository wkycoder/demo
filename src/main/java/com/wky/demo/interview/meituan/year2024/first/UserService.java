package com.wky.demo.interview.meituan.year2024.first;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UserService {


    public Map<Long, String> getUserMap(List<Long> userIds) {
        if (userIds == null || userIds.size() > 50) {
            throw new RuntimeException("userids more than 50");
        }
        Map<Long, String> result = new HashMap();
        for (Long userId : userIds) {
            result.put(userId, "test");
        }
//        try {
//            TimeUnit.MILLISECONDS.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return result;
    }

}
