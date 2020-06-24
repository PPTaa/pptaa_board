package com.pptaa.test1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.pptaa.test1.VO.Board;
import com.pptaa.test1.VO.Member;
import com.pptaa.test1.VO.Paging;
import com.pptaa.test1.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * BoardController
 */
@Controller
public class BoardController {

    @Autowired
    BoardService service;

    //글쓰기
    @RequestMapping("/board/write")
    public String writeForm() {
        return "board/write";
    }
    @RequestMapping(value = "/board/write", method = RequestMethod.POST)
    public ModelAndView write(Model model, ModelAndView mav, Board board) throws Exception {

        service.boardInsert(board);
        mav.setViewName("redirect:/board/boardlist?num=1");

        return mav;
    }    
    
    // 게시글 읽기
    @GetMapping(value = "/board/read")
    public String getRead(@RequestParam("board_idx") int board_idx, Model model) throws Exception {
        Board board = service.boardRead(board_idx);

        
        System.out.println(board);
        System.out.println(board.getDatetime());
        System.out.println(board.getBoard_title());

        service.upViewCnt(board);

        model.addAttribute("board", board);
        return "board/read";
    }

    // 게시글 수정 페이지
    @RequestMapping(value = "/board/modify", method = RequestMethod.GET)
    public String getModify(@RequestParam("board_idx") int board_idx, Model model) throws Exception {
        Board board = service.boardRead(board_idx);
        model.addAttribute("board", board);
        return "board/modify";
    }
    @RequestMapping(value = "/board/modify", method = RequestMethod.POST)
    public String postModify(Board board) throws Exception {
        
        service.boardModify(board);
        return "redirect:/board/read?board_idx="+board.getBoard_idx();
    }


    // 게시글 삭제
    @RequestMapping(value = "/board/delete")
    public String delete(@RequestParam("board_idx") int board_idx) throws Exception {
        service.boardDelete(board_idx);

        return "redirect:/board/boardlist?num=1" ;
    }

    @RequestMapping("/board/boardlist")
    public String boardlistPage (Model model,@RequestParam("num") int num,
        @RequestParam(value = "searchType", required = false, defaultValue = "title") String searchType,
        @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) throws Exception {
        // 게시글 총개수
        int count = service.boardCount();
        // 페이징 클래스
        Paging paging = new Paging();

        paging.setNum(num);
        paging.setCount(count);

        List<Board> listpage=null;
        listpage = service.boardPageSearch(paging.getDisplayPost(), paging.getPostNum(), searchType, keyword);

        model.addAttribute("boardList",listpage);
        model.addAttribute("paging", paging);

        return "board/boardlist";
    }
    // 내 게시글들 확인하기
    @RequestMapping("/myPage/board/myboardlist")
    public String test (Model model,HttpServletRequest request, 
        @RequestParam("num") int num) throws Exception {
        HttpSession session = request.getSession();
        Member member =  (Member) session.getAttribute("info");

        int count = service.myCount(member.getMemberid());

        Paging paging = new Paging();
        paging.setNum(num);
        paging.setCount(count);

        List<Board> boardpage = null;
        boardpage = service.myBoardPage(paging.getDisplayPost(), paging.getPostNum(), member.getMemberid());

        model.addAttribute("boardList", boardpage);
        model.addAttribute("paging", paging);
        
        return "board/boardlist_member";
    }

}