package com.wb.crontab;

import com.wb.service.IRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * <p>文件名称：GenerateUserTask </p>
 * <p>文件描述：</p>
 * <p>版权所有：版权所有(C)2011-2099 </p>
 * <p>公   司：口袋购物 </p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2016/10/26 </p>
 *
 * @author wubin
 */
@Component
public class GenerateUserTask {

    private static final Logger logger = LoggerFactory.getLogger(GenerateUserTask.class);

    @Autowired
    private IRegisterService iRegisterService;

    @Scheduled(fixedRate = (4*60*1000 + 12234 ))
    public void rigisterUser(){
        Calendar calendar = Calendar.getInstance();
        if(calendar.get(Calendar.HOUR_OF_DAY) > 6 && calendar.get(Calendar.HOUR_OF_DAY) < 24) {
            iRegisterService.register();
        }
    }

}
