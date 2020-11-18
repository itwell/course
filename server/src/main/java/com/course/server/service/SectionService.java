package com.course.server.service;

import com.course.server.domain.Section;
import com.course.server.domain.SectionExample;
import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.enums.SectionChargeEnum;
import com.course.server.mapper.SectionMapper;
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
import java.util.Date;

/**
 * @author itwell
 * @date 2020-11-11 16:39
 */
@Service
public class SectionService {
    @Autowired
    SectionMapper sectionMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        //分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());

        SectionExample sectionExample = new SectionExample();
                sectionExample.setOrderByClause("sort asc");
        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);

        PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        pageDto.setTotal(pageInfo.getTotal());

        List<SectionDto> SectionDtoList = new ArrayList<SectionDto>();
                for (int i = 0; i < sectionList.size(); i++) {
                Section section = sectionList.get(i);
                SectionDto sectionDto = new SectionDto();
                BeanUtils.copyProperties(section,sectionDto);
                SectionDtoList.add(sectionDto);
                }
                pageDto.setList(SectionDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(SectionDto sectionDto) {
        Section section = CopyUtil.copy(sectionDto, Section.class);
        if(StringUtils.isEmpty(sectionDto.getId())){
        this.insert(section);
        }else {
        this.update(section);
        }
    }

    /**
    * 新增
    */
    private void insert(Section section) {
        Date now = new Date();
        section.setCreatedAt(now);
        section.setUpdatedAt(now);
        section.setId(UuidUtil.getShortUuid());
        section.setCharge(SectionChargeEnum.CHARGE.getCode());
        sectionMapper.insert(section);
    }

    /**
    * 更新
    */
    private void update(Section section) {
                section.setUpdatedAt(new Date());
        sectionMapper.updateByPrimaryKey(section);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        sectionMapper.deleteByPrimaryKey(id);
    }
}
