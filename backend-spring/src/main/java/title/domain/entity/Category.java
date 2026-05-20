package title.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 分类实体类 - 对应数据库category表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("category")
@Builder
public class Category {
    @TableId(type = IdType.AUTO)
    private Long id;//主键ID
    @NotEmpty
    @TableField(value = "category_name")
    private String categoryName;//分类名称
    @NotEmpty
    @TableField(value = "category_alias")
    private String categoryAlias;//分类别名
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;//创建人ID
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;//更新人ID
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;//创建时间
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;//更新时间
}