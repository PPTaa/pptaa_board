package com.pptaa.test1.mapper;

import java.util.List;

import com.pptaa.test1.VO.Member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public Member selectMem(Member board) throws Exception;
    public void insertMem(Member board) throws Exception;
    public void deleteMem(Member board) throws Exception;
    public List<Member> listMem() throws Exception;
}