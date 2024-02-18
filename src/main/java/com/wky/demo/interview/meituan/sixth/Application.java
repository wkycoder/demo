package com.wky.demo.interview.meituan.sixth;

import cn.hutool.json.JSONUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 根据给定的表，设计出一个定时任务的一部分，根据要求遍历表，根据 biz_type ,status ,create_time 进行查询,
 * 要求查询创建时间在当天，且status = 0 ，并且content中A的值等于"招聘"的行，
 * 并调用给出的方法ArchiveService.complete(Long id)方法将status置为1；
 * 小贴士：注意代码健壮性
 * CREATE TABLE `keyctm_archive_content` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
 *   `biz_type` int(10) NOT NULL DEFAULT '0' COMMENT '业务',
 *   `archive_type` varchar(256) DEFAULT NULL COMMENT '档案类型',
 *   `content` json DEFAULT NULL COMMENT '档案内容',
 *   `status` int(10) DEFAULT '1' COMMENT '状态，1正式0草稿',
 *   `creator` int(10) NOT NULL DEFAULT '0' COMMENT '创建人',
 *   `modifier` int(10) NOT NULL DEFAULT '0' COMMENT '修改人',
 *   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 *   `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
 *   PRIMARY KEY (`id`),
 * ) ENGINE=InnoDB AUTO_INCREMENT=233826 DEFAULT CHARSET=utf8mb4 COMMENT='大客档案表'
 *
 * content中数据结构为：
 * {
 * 	"A": "A属性",
 * 	"B": "B属性",
 * 	"C": "C属性"
 * }
 * @author wuming
 * @date 2023/8/22/08/22 19:57
 */
public class Application {

    public static void main(String[] args) {
        ArchiveService archiveService = new ArchiveService();
        List<ArchiveContent> archiveContents = new ArrayList<>();
        for (ArchiveContent archiveContent : archiveContents) {
            Content content = JSONUtil.toBean(archiveContent.getContent(), Content.class);
            if (LocalDate.now().equals(archiveContent.getCreateTime().toLocalDate())
                    && archiveContent.getStatus() == 0
                    && "招聘".equals(content.getA())) {
                archiveService.complete(archiveContent.getId());
            }
        }
    }

}
