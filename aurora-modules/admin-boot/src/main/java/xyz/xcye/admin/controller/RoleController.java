package xyz.xcye.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.xcye.admin.po.Role;
import xyz.xcye.admin.pojo.PermissionPojo;
import xyz.xcye.admin.pojo.RolePojo;
import xyz.xcye.admin.service.RoleService;
import xyz.xcye.core.annotaion.controller.ModifyOperation;
import xyz.xcye.core.annotaion.controller.ResponseRealResult;
import xyz.xcye.core.annotaion.controller.SelectOperation;
import xyz.xcye.core.valid.Delete;
import xyz.xcye.core.valid.Insert;
import xyz.xcye.core.valid.Update;
import xyz.xcye.data.entity.Condition;
import xyz.xcye.data.entity.PageData;

import javax.validation.groups.Default;

/**
 * @author qsyyke
 */

@RequestMapping("/admin/role")
@RestController
@Tag(name = "角色相关的操作")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ModifyOperation
    @Operation(summary = "插入角色")
    @PostMapping("/insert")
    public void insertRole(@Validated({Insert.class, Default.class}) @RequestBody RolePojo role) {
        roleService.insertRole(role);
    }

    @ModifyOperation
    @Operation(summary = "修改角色信息")
    @PostMapping("/update")
    public int updateRole(@Validated({Update.class, Default.class}) @RequestBody RolePojo role) {
        return roleService.updateRole(role);
    }

    @ModifyOperation
    @Operation(summary = "删除角色")
    @PostMapping("/delete")
    public int deleteRole(@Validated({Delete.class, Default.class}) @RequestBody RolePojo pojo) {
        return roleService.deleteByUid(pojo);
    }

    @ResponseRealResult
    @Operation(summary = "根据uid查询角色")
    @PostMapping("/queryByUid")
    public Role queryRoleByUid(@RequestBody int uid) {
        return roleService.selectByUid(uid);
    }

    @SelectOperation
    @Operation(summary = "查询满足要求的所有角色信息")
    @PostMapping("/queryByCondition")
    public PageData<Role> queryRoleByUid(@RequestBody Condition<Long> condition) {
        return roleService.selectAllRole(condition);
    }
}
