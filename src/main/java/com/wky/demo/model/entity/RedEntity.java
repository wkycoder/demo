package com.wky.demo.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: wangkunyang
 * @date 2021/08/30 17:07
 */
@Data
@Entity
@Table(name = "red")
public class RedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 主键
     */
    @Column(name = "id", columnDefinition = "int")
    private Integer id;

    /**
     * 总金额
     */
    @Column(name = "total_amount", columnDefinition = "int")
    private Integer totalAmount;

    /**
     * 剩余金额
     */
    @Column(name = "remaining_amount", columnDefinition = "int")
    private Integer remainingAmount;

}
