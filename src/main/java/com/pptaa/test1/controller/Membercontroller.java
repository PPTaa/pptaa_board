package com.pptaa.test1.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pptaa.test1.VO.Member;
import com.pptaa.test1.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "/")
public class Membercontroller {

    @Autowired
    MemberService service;

    @RequestMapping("/")
    public String test() {
        return "index";
    }
    @RequestMapping("/main")
    public String main(Model model) throws Exception{
        List<Member> info = service.listMem();
        model.addAttribute("info", info);
        
        return "main";
    }
    
    // 로그인
    @RequestMapping("/selectform")
    public String selectform () {
        return "/member/selectform";
    }
    @RequestMapping(value = "/select", method = RequestMethod.POST )    
    public String selectTest(Member member, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        //pw 인코딩 match
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Member info  = service.selectMem(member);
        boolean matchpw = encoder.matches(member.getMemberpw(), info.getMemberpw());
        
        if (matchpw == true) {
            session.setAttribute("info", info);
            model.addAttribute("list", info);
            System.out.println(info);
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인 성공'); location.href='main';</script>");
            out.flush();
            return "redirect:main";
        } else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('비밀번호를 확인해주세요'); location.href='selectform';</script>");
            out.flush();
            return"redirect:selectform";
        }
    }

    // 회원가입
    @RequestMapping("/insertform")
    public String insertform () {
        return "/member/insertform";
    }
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ModelAndView insert(ModelAndView mav, Member member) throws Exception {
        // pw 암호화 저장 
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodingpw = encoder.encode(member.getMemberpw());
        member.setMemberpw(encodingpw);
        
        service.insertMem(member);

        mav.setViewName("redirect:main");
        return mav;
    }

    // 계정삭제
    @RequestMapping("/myPage/deleteform")
    public String deleteform() {
        return "/member/deleteform";
    }
    @RequestMapping("/myPage/delete")
    public String delete (Member member, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        session.invalidate();
        service.deleteMem(member);
        
        return "redirect:/main";
    }
    
    // 계정 확인
    @ResponseBody
    @RequestMapping("/myPage/deleteconfirm")
    public int deleteConfirm (Member member) throws Exception {
        Member select = service.selectMem(member);
        if (select == null) {
            return 0;
        }
        return 1;
    }

    // 로그아웃
    @RequestMapping(value="/myPage/logout", method=RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/main";
    } 

    //리스트 출력
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public String list(Model model) throws Exception {
        
        List<Member> info = service.listMem();
        model.addAttribute("info", info);
        return "/member/list";
    }

    //아이디 중복확인
    @ResponseBody
    @RequestMapping(value="/idcheck", method=RequestMethod.POST)
    public int idcheck(Model model, Member member) throws Exception {
        System.out.println(member.getMemberid());
        int result = 0;
        Member idcheck = service.idcheck(member);
        if (member.getMemberid() == ""){
            System.out.println("check");
            result = 2;
        } else {
            if (idcheck == null) {
                result = 1;
            }
        }        
        return result;
    }

    //마이페이지
    @RequestMapping(value = "/myPage", method = RequestMethod.GET)
    public String myPage() {
        return "/member/mypage";
    }
    
    //계정 수정
    @RequestMapping(value = "/myPage/updateform")
    public String updateform(){
        return "member/updateform";
    }
    @RequestMapping(value = "/myPage/update")
    public String update(Member member, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        service.updateMem(member);

        session.invalidate();
        
        return "redirect:/main";
    }
}