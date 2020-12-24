package com.course.server.service;

import com.course.server.domain.RoleUser;
import com.course.server.domain.RoleUserExample;
import com.course.server.dto.RoleUserDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.RoleUserMapper;
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
public class RoleUserService {
    @Autowired
    RoleUserMapper roleUserMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        //分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());

        RoleUserExample roleUserExample = new RoleUserExample();
        List<RoleUser> roleUserList = roleUserMapper.selectByExample(roleUserExample);

        PageInfo<RoleUser> pageInfo = new PageInfo<>(roleUserList);
        pageDto.setTotal(pageInfo.getTotal());

        List<RoleUserDto> RoleUserDtoList = new ArrayList<RoleUserDto>();
                for (int i = 0; i < roleUserList.size(); i++) {
                RoleUser roleUser = roleUserList.get(i);
                RoleUserDto roleUserDto = new RoleUserDto();
                BeanUtils.copyProperties(roleUser,roleUserDto);
                RoleUserDtoList.add(roleUserDto);
                }
                pageDto.setList(RoleUserDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(RoleUserDto roleUserDto) {
        RoleUser roleUser = CopyUtil.copy(roleUserDto, RoleUser.class);
        if(StringUtils.isEmpty(roleUserDto.getId())){
        this.insert(roleUser);
        }else {
        this.update(roleUser);
        }
    }

    /**
    * 新增
    */
    private void insert(RoleUser roleUser) {
        roleUser.setId(UuidUtil.getShortUuid());
        roleUserMapper.insert(roleUser);
    }

    /**
    * 更新
    */
    private void update(RoleUser roleUser) {
        roleUserMapper.updateByPrimaryKey(roleUser);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        roleUserMapper.deleteByPrimaryKey(id);
    }
}
