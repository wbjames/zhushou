package com.wb.service;

import com.wb.entity.User;
import org.springframework.stereotype.Service;

/**
 * <p>文件名称：ICollectSuitSerivce </p>
 * <p>文件描述：点赞</p>
 * <p>版权所有：版权所有(C)2011-2099 </p>
 * <p>公   司：口袋购物 </p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2016/10/29 </p>
 *
 * @author wubin
 */
@Service
public interface ICollectSuitService {

    void collectSuit(User user, String viewUserId, String suitId);
}
