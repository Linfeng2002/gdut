package com.gdut.backend.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 杰哥
 * @since 2023-05-01
 */
@Data
@Accessors(chain = true)
@TableName("question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id，主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("父类id")
    @TableField("creator_id")
    private Integer creatorId;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("具体内容")
    @TableField("discription")
    private String discription;

    @ApiModelProperty("游览数")
    @TableField("viewCount")
    private Integer viewCount;

    @ApiModelProperty("点赞数")
    @TableField("likeCount")
    private Integer likeCount;

    @ApiModelProperty("评论数")
    @TableField("commentCount")
    private Integer commentCount;

    @ApiModelProperty("创建时间")
    @TableField("gmtCreat")
    private LocalDateTime gmtCreat;

    @ApiModelProperty("更新时间")
    @TableField("gmtModified")
    private LocalDateTime gmtModified;

    @ApiModelProperty("帖子分区")
    @TableField("zone")
    private Integer zone;
}
