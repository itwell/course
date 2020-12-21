package com.course.server.service;

import com.course.server.domain.Role;
import com.course.server.domain.RoleExample;
import com.course.server.dto.RoleDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.RoleMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author itwell
 * @date 2020-11-11 16:39
 */
@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        //分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());

        RoleExample roleExample = new RoleExample();
        List<Role> roleList = roleMapper.selectByExample(roleExample);

        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        pageDto.setTotal(pageInfo.getTotal());

        List<RoleDto> RoleDtoList = new ArrayList<RoleDto>();
                for (int i = 0; i < roleList.size(); i++) {
                Role role = roleList.get(i);
                RoleDto roleDto = new RoleDto();
                BeanUtils.copyProperties(role,roleDto);
                RoleDtoList.add(roleDto);
                }
                pageDto.setList(RoleDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(RoleDto roleDto) {
        Role role = CopyUtil.copy(roleDto, Role.class);
        if(StringUtils.isEmpty(roleDto.getId())){
        this.insert(role);
        }else {
        this.update(role);
        }
    }

    /**
    * 新增
    */
    private void insert(Role role) {
        role.setId(UuidUtil.getShortUuid());
        roleMapper.insert(role);
    }

    /**
    * 更新
    */
    private void update(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        roleMapper.deleteByPrimaryKey(id);
    }
}
