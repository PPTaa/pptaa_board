package com.pptaa.test1.service;

import java.util.List;

import com.pptaa.test1.VO.Board;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface IBoardService {
    public void boardInsert(Board board) throws Exception;
    public Board boardRead(int board_idx) throws Exception;
    public void boardModify(Board board) throws Exception;
    public void boardDelete(int board_idx) throws Exception;
    public int boardCount() throws Exception;
    public List<Board> boardPageSearch (@Param("displayPost") int displayPost ,
        @Param("postNum") int postNum,@Param("searchType") String searchType,
        @Param("keyword") String keyword) throws Exception;
    public int myCount(String memberid) throws Exception;
    public List<Board> myBoardPage(@Param("displayPost") int displayPost ,
        @Param("postNum") int postNum, @Param("memberid") String memberid) throws Exception;
    public void upViewCnt(Board board) throws Exception;
    public int searchCount(@Param("searchType") String searchType, @Param("keyword") String keyword ) throws Exception;
}