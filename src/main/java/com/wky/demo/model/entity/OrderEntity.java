package com.wky.demo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: wangkunyang
 * @date 2021/08/30 17:07
 */
@Data
@Entity
@Table(name = "tb_order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 主键
     */
    @Column(name = "id", columnDefinition = "int")
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "amount", columnDefinition = "decimal")
    private BigDecimal amount;

    /**
     * 登录密码
     */
    @Column(name = "user_id", columnDefinition = "int")
    private Integer userId;

    /**
     * 是否删除
     */
    @Column(name = "deleted", columnDefinition = "tinyint")
    private Integer deleted;

    /**
     * 创建时间
     */
    @Column(name = "created_at", columnDefinition = "datetime")
    private LocalDateTime createdAt;
    /**
     * 创建人
     */
    @Column(name = "created_by", columnDefinition = "int")
    private Integer createdBy;
    /**
     *
     * 更新时间
     */
    @Column(name = "updated_at", columnDefinition = "datetime")
    private LocalDateTime updatedAt;
    /**
     * 更新人
     */
    @Column(name = "updated_by", columnDefinition = "int")
    private Integer updatedBy;

}
