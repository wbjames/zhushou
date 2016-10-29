package com.wb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wb.entity.User;
import com.wb.service.ICollectSuitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * <p>文件名称：ICollectSuitServiceImpl </p>
 * <p>文件描述：</p>
 * <p>版权所有：版权所有(C)2011-2099 </p>
 * <p>公   司：口袋购物 </p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2016/10/29 </p>
 *
 * @author wubin
 */
@Component
public class ICollectSuitServiceImpl implements ICollectSuitService {

    private static final Logger logger = LoggerFactory.getLogger(ICollectSuitServiceImpl.class);

    @Override
    public void collectSuit(User user, String viewUserId, String suitId) {
        final String url = "http://api.ichuanyi.com/api.php";
        RestTemplate restTemplate = new RestTemplate();
        // deviceType=1&fromPageId=0&method=suit.collectSuit&
        // session=Ag0DAwdRCFsJAksLAQpRBQNXBVYOA1EFDAcEDAADUFBQCwdRClcEAgkDAk4DDFFRBARfCQkG&
        // suitId=1450169231&userId=1416449804&version=10.2&viewUserId=1382105947

        StringBuilder sb = new StringBuilder();
        sb.append("deviceType=1")
                .append("&method=suit.collectSuit")
                .append("&session=" + user.getSession())
                .append("&suitId=" + suitId)
                .append("&version=10.2")
                .append("&userId=" + user.getUserId())
                .append("&viewUserId=" + viewUserId);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setConnection("keep-alive");
        headers.set("User-Agent", "CYZS/10.3.0 (iPhone; iOS 10.0.2; Scale/2.00)");
        headers.set("Accept-Language", "en;q=1, zh-Hans-CN;q=0.9");

        HttpEntity<String> entity = new HttpEntity<String>(sb.toString(), headers);

        logger.warn("[collect suit info] == {}", sb.toString());
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        logger.warn("[collect suit response] == {}", responseEntity.getBody());

        if(responseEntity.getStatusCode() == HttpStatus.OK){
            JSONObject result = JSONObject.parseObject(responseEntity.getBody());
            if(result.containsKey("result") && result.getInteger("result") == 0) {
                //Ok
            }

        }

    }



}
