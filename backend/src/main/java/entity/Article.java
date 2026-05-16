package entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import controller.annotion.ArtitleStatus;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("article")
@Builder
public class Article {
    @TableId(type = IdType.AUTO)
    private Long id;//主键ID
    @TableField("title")
    private String title;//文章标题
    @TableField("content")
    private String content;//文章内容
    @TableField("cover_img")
    private String coverImg;//封面图像
    @ArtitleStatus
    private String state;//发布状态 已发布|草稿
    @TableField("category_id")
    private Long categoryId;//文章分类id
    @TableField(value = "create_user",fill = FieldFill.INSERT)
    private Long createUser;//创建人ID
    @TableField(value = "update_user",fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;//创建时间
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;//更新时间
}
