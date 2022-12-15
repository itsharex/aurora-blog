  package xyz.xcye.admin.controller;

  import io.swagger.v3.oas.annotations.Operation;
  import io.swagger.v3.oas.annotations.tags.Tag;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.validation.annotation.Validated;
  import org.springframework.web.bind.annotation.*;
  import xyz.xcye.admin.po.Permission;
  import xyz.xcye.admin.pojo.PermissionPojo;
  import xyz.xcye.admin.service.PermissionService;
  import xyz.xcye.core.annotaion.controller.ModifyOperation;
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

@RequestMapping("/admin/permission")
@RestController
@Tag(name = "权限相关的操作")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ModifyOperation
    @Operation(summary = "插入路径权限")
    @PostMapping("/insert")
    public void insertPermission(@Validated({Insert.class, Default.class}) @RequestBody PermissionPojo pojo) {
        permissionService.insertPermission(pojo);
    }

    @ModifyOperation
    @Operation(summary = "修改路径权限信息")
    @PostMapping("/update")
    public int updatePermission(@Validated({Update.class, Default.class}) @RequestBody PermissionPojo pojo) {
        return permissionService.updatePermission(pojo);
    }

    @ModifyOperation
    @Operation(summary = "删除权限")
    @PostMapping("/delete")
    public int deletePermission(@Validated(Delete.class) @RequestBody PermissionPojo pojo) {
        return permissionService.deleteByUid(pojo);
    }

    @SelectOperation
    @Operation(summary = "根据uid查询权限")
    @PostMapping("/queryByUid")
    public Permission queryPermissionByUid(@RequestBody int uid) {
        return permissionService.selectByUid(uid);
    }

    @SelectOperation
    @Operation(summary = "查询满足要求的所有权限信息")
    @PostMapping("/queryAllByCondition")
    public PageData<Permission> queryPermissionServiceByUid(@RequestBody Condition<Long> condition) {
        return permissionService.selectAllPermission(condition);
    }
}
