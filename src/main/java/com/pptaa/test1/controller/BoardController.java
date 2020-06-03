package com.pptaa.test1.controller;

import java.util.List;

import com.pptaa.test1.VO.Board;
import com.pptaa.test1.VO.Paging;
import com.pptaa.test1.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * BoardController
 */
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService service;

    //글쓰기
    @RequestMapping("/write")
    public String writeForm() {
        return "board/write";
    }
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public ModelAndView write(Model model, ModelAndView mav, Board board) throws Exception {

        service.boardInsert(board);
        mav.setViewName("redirect:/board/boardlist?num=1");

        return mav;
    }    
    
    // 게시글 읽기
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String getRead(@RequestParam("board_idx") int board_idx, Model model) throws Exception {
        Board board = service.boardRead(board_idx);
        model.addAttribute("board", board);
        return "board/read";
    }

    // 게시글 수정 페이지
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String getModify(@RequestParam("board_idx") int board_idx, Model model) throws Exception {
        Board board = service.boardRead(board_idx);
        model.addAttribute("board", board);
        return "board/modify";
    }
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String postModify(Board board) throws Exception {
        
        service.boardModify(board);
        return "redirect:/board/read?board_idx="+board.getBoard_idx();
    }


    // 게시글 삭제
    @RequestMapping(value = "/delete")
    public String delete(@RequestParam("board_idx") int board_idx) throws Exception {
        service.boardDelete(board_idx);

        return "redirect:/board/boardlist?num=1" ;
    }

    @RequestMapping("/boardlist")
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
}