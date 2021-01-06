package com.course.server.service;

import com.course.server.domain.Member;
import com.course.server.domain.MemberExample;
import com.course.server.dto.MemberDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.MemberMapper;
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
public class MemberService {
    @Autowired
    MemberMapper memberMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        //分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());

        MemberExample memberExample = new MemberExample();
        List<Member> memberList = memberMapper.selectByExample(memberExample);

        PageInfo<Member> pageInfo = new PageInfo<>(memberList);
        pageDto.setTotal(pageInfo.getTotal());

        List<MemberDto> MemberDtoList = new ArrayList<MemberDto>();
                for (int i = 0; i < memberList.size(); i++) {
                Member member = memberList.get(i);
                MemberDto memberDto = new MemberDto();
                BeanUtils.copyProperties(member,memberDto);
                MemberDtoList.add(memberDto);
                }
                pageDto.setList(MemberDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(MemberDto memberDto) {
        Member member = CopyUtil.copy(memberDto, Member.class);
        if(StringUtils.isEmpty(memberDto.getId())){
        this.insert(member);
        }else {
        this.update(member);
        }
    }

    /**
    * 新增
    */
    private void insert(Member member) {
        Date now = new Date();
        member.setId(UuidUtil.getShortUuid());
        memberMapper.insert(member);
    }

    /**
    * 更新
    */
    private void update(Member member) {
        memberMapper.updateByPrimaryKey(member);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        memberMapper.deleteByPrimaryKey(id);
    }
}
