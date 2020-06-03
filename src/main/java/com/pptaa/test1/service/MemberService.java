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
	public Member selectMem(Member board) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectMem(board);
	}

	@Override
	public void insertMem(Member board) throws Exception {
		mapper.insertMem(board);
	}

	@Override
	public void deleteMem(Member board) throws Exception {
		mapper.deleteMem(board);

	}

	@Override
	public List<Member> listMem() throws Exception {
		// TODO Auto-generated method stub
		return mapper.listMem();
	}



	
    
}