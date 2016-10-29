package com.wb.repository;

import com.wb.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>文件名称：UserRepo </p>
 * <p>文件描述：</p>
 * <p>版权所有：版权所有(C)2011-2099 </p>
 * <p>公   司：口袋购物 </p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2016/10/26 </p>
 *
 * @author wubin
 */
@Repository
public interface UserRepo extends CrudRepository<User, Long>{

    @Query(value = "select * from USER ORDER BY RAND() LIMIT 1 ", nativeQuery = true)
    User findByRandom();

}
