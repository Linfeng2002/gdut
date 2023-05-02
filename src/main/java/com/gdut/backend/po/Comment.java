package com.gdut.backend.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 杰哥
 * @since 2023-04-30
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("详细描述")
    @TableField("discription")
    private String discription;

    @ApiModelProperty("创建时间")
    @TableField("gmt_creat")
    private LocalDateTime gmtCreat;

    @ApiModelProperty("更新时间")
    @TableField("gmt_modified")
    private LocalDateTime gmtModified;

    @ApiModelProperty("父类id")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty("一级或二级评论")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("点赞数")
    @TableField("like_count")
    private Integer likeCount;

    @ApiModelProperty("评论数")
    @TableField("comment_count")
    private Integer commentCount;

    @ApiModelProperty("发言人")
    @TableField("creator_id")
    private Integer creatorId;
}
