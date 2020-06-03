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
        // TODO Auto-generated method stub
        mapper.boardInsert(board);
    }

    @Override
    public Board boardRead(int board_idx) throws Exception {
        // TODO Auto-generated method stub
        return mapper.boardRead(board_idx);
    }

    @Override
    public void boardModify(Board board) throws Exception {
        // TODO Auto-generated method stub
        mapper.boardModify(board);

    }

    @Override
    public void boardDelete(int board_idx) throws Exception {
        // TODO Auto-generated method stub
        mapper.boardDelete(board_idx);

    }

    @Override
    public int boardCount() throws Exception {
        // TODO Auto-generated method stub
        return mapper.boardCount();
    }

    @Override
    public List<Board> boardPage(int displayPost, int postNum) throws Exception {
        // TODO Auto-generated method stub
        return mapper.boardPage(displayPost, postNum);
    }

    @Override
    public List<Board> boardPageSearch(int displayPost, int postNum, String searchType, String keyword)
            throws Exception {
        // TODO Auto-generated method stub
        
        return mapper.boardPageSearch(displayPost, postNum, searchType, keyword);
    }

   
    
}