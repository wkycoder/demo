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
@Table(name = "account")
public class AccountEntity {

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
    @Column(name = "username", columnDefinition = "varchar")
    private String username;

    /**
     * 账号余额
     */
    @Column(name = "balance", columnDefinition = "decimal")
    private BigDecimal balance;

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
    @Column(name = "last_updated_at", columnDefinition = "datetime")
    private LocalDateTime updatedAt;

    /**
     * 更新人
     */
    @Column(name = "last_updated_by", columnDefinition = "int")
    private Integer updatedBy;

}
