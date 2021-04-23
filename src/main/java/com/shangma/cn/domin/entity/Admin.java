package com.shangma.cn.domin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shangma.cn.common.valid.AddGroup;
import com.shangma.cn.common.valid.SexList;
import com.shangma.cn.common.valid.UpdateGroup;
import com.shangma.cn.domin.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author 辉哥
 * @since 2021-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("base_admin")
public class Admin extends BaseEntity {

    /**
     * 管理员名称
     */
    //  @NotBlank  判断的是 null  和"" 和"   "
    //  @NotEmpty  //不能为null  不能为空   可以修饰 字符串 集合 Map 数组
    // @NotNull //判断不为null
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String adminName;

    /**
     * 管理员昵称
     */
    @NotBlank(message = "昵称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String nickName;

    /**
     * 管理员性别 0 : 男   1女     2： 表示未知
     */

    @NotNull(message = "性别必须传递", groups = {AddGroup.class, UpdateGroup.class})
//    @Max(2)
//    @Min(0)
//    @Range(max = 2,min = 0,message = "输入的内容必须是0-2之间")
//    @Size(max = 2,min = 0)

    @SexList(sex = {0, 1, 2}, message = "你输入的必须是0 1 2  输入其他的扯淡", groups = {AddGroup.class, UpdateGroup.class})
    private Integer gender;

    /**
     * 管理员手机
     * /^1[3456789]\d{9}$/
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "1[34578]\\d{9}", message = "请输入正确的手机号", groups = {AddGroup.class, UpdateGroup.class})
    private String adminPhone;

    /**
     * 管理员邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "你输入的不是一个邮箱格式", groups = {AddGroup.class, UpdateGroup.class})
    private String adminEmail;

    /**
     * 管理员家住地址
     */
    @NotBlank(message = "地址不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String adminAddress;

    /**
     * 管理员密码
     */

    private String adminPwd;

    /**
     * 管理员头像
     */
    @URL(message = "头像必须是一个URL地址", groups = {AddGroup.class, UpdateGroup.class})
    private String adminAvatar;

    /**
     * 账户是否可用
     */

    private Boolean isEnable;

    /**
     * 是否是超级管理员
     */

    private Boolean isAdmin;

    /**
     * 所在部门
     */

    private Long deptId;

    /**
     * 重置密码时间
     */
    private LocalDateTime pwdResetTime;
    /**
     * 角色Ids
     */
    transient Set<Long> roleIds;

}
