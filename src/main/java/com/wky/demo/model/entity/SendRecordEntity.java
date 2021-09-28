package com.wky.demo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author: wangkunyang
 * @date 2021/08/30 17:07
 */
@Data
@Entity
@Table(name = "send_record")
public class SendRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 主键
     */
    @Column(name = "id", columnDefinition = "int")
    private Integer id;

    /**
     * 消息接收者
     */
    @Column(name = "receiver", columnDefinition = "varchar")
    private String receiver;

    /**
     * 消息内容
     */
    @Column(name = "content", columnDefinition = "varchar")
    private String content;

    /**
     * 失败信息
     */
    @Column(name = "fail_msg", columnDefinition = "varchar")
    private String failMsg;

    /**
     * 状态，0表示失败，1表示成功
     */
    @Column(name = "status", columnDefinition = "int")
    private Integer status;

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
