package com.wky.demo.interview.meituan.fifth;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 根据所提供的方法，完成题目：
 * 批量根据供应商ID（partnerID）获取供应商下所关联的酒店名称（poiName）以及酒店的联系人姓名（contactName）;
 * 方法1）AService.getHotelByParnterId(List<Long> partnerId),根据供应商ID，返回关联的poiId集合;
 * 方法2）BService.batchGetHotelByPoiId(List<Long> poiIds),批量根据酒店id集合，返回酒店信息集合，酒店信息包含酒店ID，酒店名称；
 * 方法3）CService.batchGetContactBypoiIds(List<Long> poiIds),批量根据酒店id集合，返回酒店下联系人信息集合，酒店联系人信息包含酒店ID(poiID),联系人名称（contactName）;
 * 小贴士：同一个酒店联系人若有多个，任意取一个；默认一次查询可返回数据，无需考虑分批查询；方法中可能因为某些数据问题，导致数据缺失，结合日常工作场景，完整考虑代码健壮性；
 * @author wuming
 * @date 2023/8/22/08/22 19:11
 */
public class Application {

    public static void main(String[] args) {
        // 同一个酒店联系人若有多个，任意取一个；默认一次查询可返回数据，无需考虑分批查询；
        // 方法中可能因为某些数据问题，导致数据缺失，结合日常工作场景，完整考虑代码健壮性；
        // 批量根据供应商ID（partnerID）获取供应商下所关联的酒店名称（poiName）以及酒店的联系人姓名（contactName）;
        AService aService = new AService();
        BService bService = new BService();
        CService cService = new CService();
        List<Long> partnerIds = Arrays.asList(1L, 2L, 3L);
        List<Long> poiIds = aService.getHotelByParnterId(partnerIds);
        // 批量获取酒店信息以及对应的联系人信息
        List<Hotel> hotels = bService.batchGetHotelByPoiId(poiIds);
        List<Contact> contacts = cService.batchGetContactBypoiIds(poiIds);
        Map<Long, List<Contact>> contactMap = contacts.stream().collect(Collectors.groupingBy(Contact::getPoiId));
        List<Result> results = new ArrayList<>();
        for (Hotel hotel : hotels) {
            Result result = new Result();
            result.setPoiName(hotel.getPoiName());
            List<Contact> hotelContacts = contactMap.get(hotel.getPoiId());
            if (CollectionUtils.isNotEmpty(hotelContacts)) {
                result.setContactName(hotelContacts.get(0).getContactName());
            }
            results.add(result);
        }
    }

}
