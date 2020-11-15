package com.course.server.service;

import com.course.server.domain.${Domain};
import com.course.server.domain.${Domain}Example;
import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.${Domain}Mapper;
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
public class ${Domain}Service {
    @Autowired
    ${Domain}Mapper ${domain}Mapper;

    public void list(PageDto pageDto) {
        //分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());

        ${Domain}Example ${domain}Example = new ${Domain}Example();
        ${domain}Example.setOrderByClause("id desc");
        List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);

    PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
        pageDto.setTotal(pageInfo.getTotal());

        List<${Domain}Dto> ${Domain}DtoList = new ArrayList<${Domain}Dto>();
            for (int i = 0; i < ${domain}List.size(); i++) {
            ${Domain} ${domain} = ${domain}List.get(i);
            ${Domain}Dto ${domain}Dto = new ${Domain}Dto();
            BeanUtils.copyProperties(${domain},${domain}Dto);
            ${Domain}DtoList.add(${domain}Dto);
            }
            pageDto.setList(${Domain}DtoList);
            }

            public void save(${Domain}Dto ${domain}Dto) {
            ${Domain} ${domain} = CopyUtil.copy(${domain}Dto, ${Domain}.class);
            if(StringUtils.isEmpty(${domain}Dto.getId())){
            this.insert(${domain});
            }else {
            this.update(${domain});
            }
            }

            private void insert(${Domain} ${domain}) {
            ${domain}.setId(UuidUtil.getShortUuid());
            ${domain}Mapper.insert(${domain});
            }

            private void update(${Domain} ${domain}) {
            ${domain}Mapper.updateByPrimaryKey(${domain});
            }

            public void delete(String id) {
            ${domain}Mapper.deleteByPrimaryKey(id);
            }
            }