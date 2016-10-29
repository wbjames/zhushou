package com.wb.crontab;

import com.wb.entity.Collect;
import com.wb.entity.Task;
import com.wb.entity.User;
import com.wb.repository.CollectRepo;
import com.wb.repository.TaskRepo;
import com.wb.repository.UserRepo;
import com.wb.service.ICollectSuitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>文件名称：CollectSuitTask </p>
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
public class CollectSuitTask {

    private static final Logger logger = LoggerFactory.getLogger(CollectSuitTask.class);

    @Autowired
    ICollectSuitService iCollectSuitService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    TaskRepo taskRepo;

    @Autowired
    CollectRepo collectRepo;

    @Scheduled(fixedDelay = 60*1000+123)
    public void collect(){
        List<Task> taskList = taskRepo.findByCntGreaterThan(0);
        for (Task task: taskList
             ) {
            String viewUserId = null;
            String suitId = null;

            String url = task.getUrl();
            String params = url.substring(url.indexOf('?')+1);
            String[] paramArr = params.split("&");
            for (String p: paramArr
                 ) {
                if(!StringUtils.isEmpty(p) && p.startsWith("viewUserId")) {
                    viewUserId = p.substring(p.indexOf("=") + 1);
                }else if(!StringUtils.isEmpty(p) && p.startsWith("suitId")){
                    suitId = p.substring(p.indexOf("=") + 1);
                }
            }

            logger.warn("[taskID: {}]    viewUserId={} & suitId = {}", task.getId(), viewUserId, suitId);

            if(viewUserId != null && suitId != null) {
                User user = userRepo.findByRandom();
                if(user != null) {
                    Collect collect = collectRepo.findByUserIdAndSuitId(user.getUserId(), suitId);
                    if(collect == null) {
                        iCollectSuitService.collectSuit(user, viewUserId, suitId);
                        collect = new Collect();
                        collect.setUserId(user.getUserId());
                        collect.setSuitId(suitId);

                        collectRepo.save(collect);

                        task.setCnt(task.getCnt() - 1);
                        taskRepo.save(task);

                        logger.warn("[taskID: {}] once done! suitId={}   & userId={}", task.getId(), suitId, user.getUserId());
                    }
                }
            }
        }

    }

}
