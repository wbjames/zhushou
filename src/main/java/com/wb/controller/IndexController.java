package com.wb.controller;

import com.wb.entity.Task;
import com.wb.repository.TaskRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>文件名称：hello </p>
 * <p>文件描述：</p>
 * <p>版权所有：版权所有(C)2011-2099 </p>
 * <p>公   司：口袋购物 </p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2016/10/24 </p>
 *
 * @author wubin
 */
@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    TaskRepo taskRepo;

    @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello World!";


    }

    @RequestMapping("/")
    public String index(Model map) {

        List<Task> taskList = taskRepo.findOrderByGmtCreateDesc();

        // 加入一个属性，用来在模板中读取
        map.addAttribute("tasks", taskList);
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

    @RequestMapping("/addtask")
    public String addTask(Model map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("task", new Task());
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "addtask";
    }

    @RequestMapping("/typeIn")
    public String typeIn(Model model, @ModelAttribute Task task){
        if(!StringUtils.isEmpty(task.getUrl()) && task.getUrl().contains("viewUserId")
                && task.getUrl().contains("suitId") && task.getCnt() < 1000 && task.getCnt() > 0){

            logger.warn("type in task, url:{} , cnt: {}", task.getUrl(), task.getCnt());
            taskRepo.save(task);

            return index(model);
        }else {
            model.addAttribute("msg", "error");
            return "error!";
        }
    }
}
