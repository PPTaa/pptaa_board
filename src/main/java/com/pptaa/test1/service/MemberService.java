package com.pptaa.test1.service;

import java.util.List;

import com.pptaa.test1.VO.Member;
import com.pptaa.test1.mapper.MemberMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements IMemberService {

    @Autowired
    MemberMapper mapper;

	@Override
	public Member selectMem(Member member) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectMem(member);
	}

	@Override
	public void insertMem(Member member) throws Exception {
		mapper.insertMem(member);
	}

	@Override
	public void deleteMem(Member member) throws Exception {
		mapper.deleteMem(member);

	}

	@Override
	public List<Member> listMem() throws Exception {
		// TODO Auto-generated method stub
		return mapper.listMem();
	}

	@Override
	public Member idcheck(Member member) throws Exception {
		// TODO Auto-generated method stub
		return mapper.idcheck(member);
	}



	
    
}