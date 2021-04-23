package com.shangma.cn.controller;

import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.common.perm.HasPerm;
import com.shangma.cn.common.utils.TreeUtils;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.domin.criteria.DeptCriteria;
import com.shangma.cn.domin.entity.Dept;
import com.shangma.cn.domin.vo.DeptVo;
import com.shangma.cn.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/14 16:56
 * 文件说明：
 */
@RestController
@RequestMapping("dept")
@RequiredArgsConstructor
public class DeptController extends BaseController {


    private final DeptService deptService;


    @GetMapping
    public AxiosResult<PageResult<DeptVo>> list(DeptCriteria deptCriteria) {
        PageResult<DeptVo> pageResult = deptService.searchPage(deptCriteria);
        return AxiosResult.success(pageResult);
    }

    @GetMapping("{id}")
    public AxiosResult<Map<String, Object>> findById(@PathVariable Long id) {
        Dept byId = deptService.getById(id);
        List<DeptVo> parents = deptService.getSuperByParent(byId.getParentId(), new ArrayList<>());
        //构建Tree
        List<DeptVo> deptVos = TreeUtils.buildTree(parents);
        Map<String, Object> map = new HashMap<>();
        map.put("obj", byId);
        map.put("elements", deptVos);
        return AxiosResult.success(map);
    }





    @PostMapping
    @HasPerm(perm = "dept:add")
    public AxiosResult<Void> add(@RequestBody Dept Dept) {
        return toAxios(deptService.save(Dept));
    }

    @PutMapping
    @HasPerm(perm = "dept:edit")
    public AxiosResult<Void> update(@RequestBody Dept Dept) {
        return toAxios(deptService.update(Dept));
    }


    @DeleteMapping("{id}")
    @HasPerm(perm = "dept:delete")
    public AxiosResult<Void> deleteById(@PathVariable Long id) {
        return toAxios(deptService.deleteSelfAndChildren(id));
    }

    @GetMapping("{id}/children")
    public AxiosResult<List<DeptVo>> getChildrenById(@PathVariable long id) {
        return AxiosResult.success(deptService.getChildrenById(id));
    }



    @GetMapping("findParentByDeptId/{id}")
    public AxiosResult<List<DeptVo>> findParentById(@PathVariable Long id) {
        List<DeptVo> list = deptService.getDeptVoTree(id, new ArrayList<>());
        List<DeptVo> deptVos = TreeUtils.buildTree(list);
        return AxiosResult.success(deptVos);
    }
//    @GetMapping("tree")
//    public AxiosResult<List<DeptVo>> getDeptTree() {
//        List<DeptVo> list = deptService.getDeptTree();
//        return AxiosResult.success(list);
//    }
//
//
//    @GetMapping("root")
//    public AxiosResult<List<DeptVo>> getRootList() {
//        List<DeptVo> list = deptService.getChildrenById(0L);
//        return AxiosResult.success(list);
//    }

}
