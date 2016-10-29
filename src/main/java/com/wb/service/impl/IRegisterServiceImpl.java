package com.wb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wb.entity.User;
import com.wb.repository.UserRepo;
import com.wb.service.IRegisterService;
import com.wb.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * <p>文件名称：RegisterServiceImpl </p>
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
public class IRegisterServiceImpl implements IRegisterService {

    private static final Logger logger = LoggerFactory.getLogger(IRegisterServiceImpl.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public void register() {

        final String url = "http://api.ichuanyi.com/api.php";
        RestTemplate restTemplate = new RestTemplate();

        StringBuilder sb = new StringBuilder();
        sb.append("clientVersion=10.3.0")
                .append("&clientVersion=10.3.0")
                .append("&deviceType=1")
                .append("&email=" + StringUtils.getRandomString(44) + "%40cyzs.com")
                .append("&fromPageId=0")
                .append("&idfa=" + (StringUtils.getRandomString(8) + "-" +
                        StringUtils.getRandomString(4) + "-" +
                        StringUtils.getRandomString(4) + "-" +
                        StringUtils.getRandomString(4) + "-" +
                        StringUtils.getRandomString(12)).toUpperCase())
                .append("&isAutoRegister=1")
                .append("&method=user.register")
                .append("&openUDID="+StringUtils.getRandomString(40))
                .append("&password=" +  StringUtils.getRandomString(6))
                .append("&username=" + StringUtils.getRandomString(10))
                .append("&version=10.2");


        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setConnection("keep-alive");
        headers.set("User-Agent", "CYZS/10.3.0 (iPhone; iOS 10.0.2; Scale/2.00)");
        headers.set("Accept-Language", "en;q=1, zh-Hans-CN;q=0.9");
        //headers.set("Accept-Charset", "utf-8");
       // headers.set("Accept-Encoding", "gzip, deflate");

        HttpEntity<String> entity = new HttpEntity<String>(sb.toString(), headers);

        logger.warn("[register info] == {}", sb.toString());
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        logger.warn("[register response] == {}", responseEntity.getBody());
        if(responseEntity.getStatusCode() == HttpStatus.OK){
            JSONObject result = JSONObject.parseObject(responseEntity.getBody());
            if(result.containsKey("result") && result.getInteger("result") == 0) {
                JSONObject data = result.getJSONObject("data");

                User user = new User();
                user.setUserId(data.getString("userId"));
                user.setSession(data.getString("session"));
                user.setExtra(data.toJSONString());

                userRepo.save(user);
            }

        }



    }


}
