package com.pptaa.test1.service;

import java.util.List;

import com.pptaa.test1.VO.Member;

public interface IMemberService {
    public Member selectMem(Member member) throws Exception;
    public void insertMem(Member member) throws Exception;
    public void deleteMem(Member member) throws Exception;
    public List<Member> listMem() throws Exception;
    public Member idcheck(Member member) throws Exception;
}