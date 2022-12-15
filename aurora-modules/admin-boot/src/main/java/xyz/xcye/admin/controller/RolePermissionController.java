package xyz.xcye.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.xcye.admin.dto.RolePermissionDTO;
import xyz.xcye.admin.po.Role;
import xyz.xcye.admin.pojo.PermissionRelationshipPojo;
import xyz.xcye.admin.service.PermissionRelationService;
import xyz.xcye.core.annotaion.controller.ModifyOperation;
import xyz.xcye.core.annotaion.controller.SelectOperation;
import xyz.xcye.core.entity.R;
import xyz.xcye.core.enums.ResponseStatusCodeEnum;
import xyz.xcye.data.entity.Condition;

import java.util.List;

/**
 * 角色权限信息控制器
 * @author qsyyke
 * @date Created in 2022/5/4 22:43
 */

@Tag(name = "角色权限信息控制器")
@RestController
@RequestMapping("/admin/permissionRelation")
public class RolePermissionController {

    @Autowired
    private PermissionRelationService permissionRelationService;

    @SelectOperation
    @Operation(summary = "根据用户uid，加载该用户所拥有的角色权限关系")
    @PostMapping("/queryByUserUid")
    public List<RolePermissionDTO> loadPermissionByUserUid(@RequestBody long userUid) {
        return permissionRelationService.loadPermissionByUserUid(userUid);
    }

    @SelectOperation
    @PostMapping("/queryByCondition")
    @Operation(summary = "加载所有的角色权限关系，只返回该角色存在权限部分，如果某个角色没有赋予权限，则不返回")
    public List<RolePermissionDTO> loadAllRolePermission(@RequestBody Condition<Long> condition) {
        return permissionRelationService.loadAllRolePermission(condition);
    }

    @SelectOperation
    @PostMapping("/userRole/queryByUsername")
    @Operation(summary = "根据用户名，获取该用户所拥有的所有角色")
    public List<Role> loadAllRoleByUsername(@RequestBody String username) {
        return permissionRelationService.loadAllRoleByUsername(username);
    }

    @SelectOperation
    @Operation(summary = "根据用户名，加载该用户所拥有的角色权限关系，此接口和loadPermissionByUserUid返回的数据一样")
    @PostMapping("/username/queryByUsername")
    public List<RolePermissionDTO> loadPermissionByUsername(@RequestBody String username) {
        return permissionRelationService.loadPermissionByUsername(username);
    }

    @SelectOperation
    @PostMapping("/roleName/queryByRoleName")
    @Operation(summary = "根据角色名称，加载对应的角色-权限信息")
    public List<RolePermissionDTO> loadPermissionByRoleName(@RequestBody String roleName) {
        return permissionRelationService.loadPermissionByRoleName(roleName);
    }

    @SelectOperation
    @PostMapping("/role/queryPermissionPath")
    @Operation(summary = "根据permissionPath，查询哪些角色和角色可以可以访问")
    public List<RolePermissionDTO> queryRoleByPermissionPath(@RequestBody String permissionPath) {
        return permissionRelationService.queryRoleByPermissionPath(permissionPath);
    }

    @ModifyOperation
    @Operation(summary = "批量为多个用户增加角色")
    @PostMapping("/insertUserRoleBatch")
    public R insertUserRoleBatch(@RequestBody PermissionRelationshipPojo pojo) {
        return R.success(ResponseStatusCodeEnum.SUCCESS.getCode(),
                ResponseStatusCodeEnum.SUCCESS.getMessage(),
                "为" + permissionRelationService.insertUserRoleBatch(pojo) + "个用户增加了角色", true);
    }

    @ModifyOperation
    @Operation(summary = "批量为多个角色增加权限")
    @PostMapping("/insertRolePermissionBatch")
    public R insertRolePermissionBatch(@RequestBody PermissionRelationshipPojo pojo) {
        return R.success(ResponseStatusCodeEnum.SUCCESS.getCode(),
                ResponseStatusCodeEnum.SUCCESS.getMessage(),
                "添加成功数" + permissionRelationService.insertRolePermissionBatch(pojo), true);
    }

    @ModifyOperation
    @Operation(summary = "为某个用户删除多个角色")
    @PostMapping("/deleteUserRoleBatch")
    public int deleteUserRoleBatch(@RequestBody PermissionRelationshipPojo pojo) {
        return permissionRelationService.deleteUserRoleBatch(pojo);
    }

    @ModifyOperation
    @Operation(summary = "修改某个用户的角色")
    @PostMapping("/updateUserRole")
    public int updateUserRole(@RequestBody PermissionRelationshipPojo pojo) {
        return permissionRelationService.updateUserRole(pojo);
    }

    @ModifyOperation
    @Operation(summary = "删除某个角色的多个权限")
    @PostMapping("/deleteRolePermissionBatch")
    public int deleteRolePermissionBatch(@RequestBody PermissionRelationshipPojo pojo) {
        return permissionRelationService.deleteRolePermissionBatch(pojo);
    }

    @ModifyOperation
    @Operation(summary = "更新某个角色的权限")
    @PostMapping("/updateRolePermission")
    public int updateRolePermission(@RequestBody PermissionRelationshipPojo pojo) {
        return permissionRelationService.updateRolePermission(pojo);
    }
}
