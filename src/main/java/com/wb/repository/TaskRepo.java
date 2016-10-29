package com.wb.repository;

import com.wb.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>文件名称：TaskRepo </p>
 * <p>文件描述：</p>
 * <p>版权所有：版权所有(C)2011-2099 </p>
 * <p>公   司：口袋购物 </p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2016/10/29 </p>
 *
 * @author wubin
 */
@Repository
public interface TaskRepo extends CrudRepository<Task, Long>{

    List<Task> findByCntGreaterThan(int cnt);

    @Query("select task from com.wb.entity.Task task order by task.gmtCreate desc")
    List<Task> findOrderByGmtCreateDesc();
}
