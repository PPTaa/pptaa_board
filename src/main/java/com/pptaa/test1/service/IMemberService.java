package com.pptaa.test1.service;

import java.util.List;

import com.pptaa.test1.VO.Member;

public interface IMemberService {
    public Member selectMem(Member board) throws Exception;
    public void insertMem(Member board) throws Exception;
    public void deleteMem(Member board) throws Exception;
    public List<Member> listMem() throws Exception;
}