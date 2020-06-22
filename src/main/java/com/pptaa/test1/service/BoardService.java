package com.pptaa.test1.service;

import java.util.List;

import com.pptaa.test1.VO.Board;
import com.pptaa.test1.mapper.BoardMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService implements IBoardService {

    @Autowired
    BoardMapper mapper;

    @Override
    public void boardInsert(Board board) throws Exception {
        mapper.boardInsert(board);
    }

    @Override
    public Board boardRead(int board_idx) throws Exception {
        return mapper.boardRead(board_idx);
    }

    @Override
    public void boardModify(Board board) throws Exception {
        mapper.boardModify(board);

    }

    @Override
    public void boardDelete(int board_idx) throws Exception {
        mapper.boardDelete(board_idx);

    }

    @Override
    public int boardCount() throws Exception {
        return mapper.boardCount();
    }

    @Override
    public List<Board> myBoardPage(int displayPost, int postNum, String memberid) throws Exception {
        return mapper.myBoardPage(displayPost, postNum, memberid);
    }

    @Override
    public List<Board> boardPageSearch(int displayPost, int postNum, String searchType, String keyword)
            throws Exception {
        
        return mapper.boardPageSearch(displayPost, postNum, searchType, keyword);
    }

    @Override
    public int myCount(String memberid) throws Exception {
        return mapper.myCount(memberid);
    }


   
    
}