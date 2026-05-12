package pojo;



import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.conditions.update.Update;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
@Builder
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;//主键ID

    @TableField(value = "username")
    private String userName;//用户名

    @JsonIgnore
    @TableField(value = "password")
    private String password;//密码

//    @NotEmpty @Pattern(regexp = "^\\S{1,10}$")    @NotEmpty
    @TableField(value = "nickname")
    private String nickName;//昵称
    @Email
    @TableField(value = "email")
    private String email;//邮箱
    @TableField(value = "user_pic")
    private String userPic;//用户头像地址

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间

}
