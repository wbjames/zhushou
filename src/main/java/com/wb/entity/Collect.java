package com.wb.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * <p>文件名称：Collect </p>
 * <p>文件描述：</p>
 * <p>版权所有：版权所有(C)2011-2099 </p>
 * <p>公   司：口袋购物 </p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2016/10/29 </p>
 *
 * @author wubin
 */
@Entity
@Table(name = "collect")
@Data
public class Collect {

    @GeneratedValue
    @Id
    private Long id;

    private String userId;

    private String suitId;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

}
