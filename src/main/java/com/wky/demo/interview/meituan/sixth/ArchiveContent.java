package com.wky.demo.interview.meituan.sixth;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wuming
 * @date 2023/8/22/08/22 20:00
 */
@Data
public class ArchiveContent {

    private Long id;

    private Integer bizType;

    private String content;

    private Integer status;

    private LocalDateTime createTime;

}
