package com.pptaa.test1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.pptaa.test1.VO.Member;
import com.pptaa.test1.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "/")
public class Membercontroller {

    @Autowired
    MemberService service;
    
    @RequestMapping("/")
    public String main(Model model) throws Exception{
        List<Member> info = service.listMem();
        model.addAttribute("info", info);
        
        return "main";
    }
    
    // 로그인
    @RequestMapping("/selectform")
    public String selectform () {
        return "selectform";
    }
    @RequestMapping(value = "/select", method = RequestMethod.POST )
    public String selectTest(Member board, Model model, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        
        Member info  = service.selectMem(board);

        session.setAttribute("info", info);
        model.addAttribute("list", info);
        System.out.println(info);

        return "redirect:/";
    }
    // 회원가입
    @RequestMapping("/insertform")
    public String insertform () {
        return "insertform";
    }
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ModelAndView insert(ModelAndView mav, Member board) throws Exception {

        service.insertMem(board);

        mav.setViewName("redirect:/");
        return mav;
    }
    // 계정삭제
    @RequestMapping("/deleteform")
    public String deleteform() {
        return "deleteform";
    }
    @RequestMapping("/delete")
    public String delete (Member board, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        session.invalidate();
        service.deleteMem(board);
        return "redirect:/";
    }
    // 로그아웃
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    } 
    //리스트 출력
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public String list(Model model) throws Exception {
        
        List<Member> info = service.listMem();
        model.addAttribute("info", info);
        return "list";
    }
}