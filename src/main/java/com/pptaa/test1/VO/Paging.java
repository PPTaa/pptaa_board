package com.pptaa.test1.VO;

import lombok.Data;

@Data
public class Paging {
    private int count;
    private int num;
    private int postNum = 10;
    private int pageNum;
    private int displayPost;
    private int pageNum_count = 10;
    private int endPageNum;
    private int startPageNum;

    private boolean prev;
    private boolean next;

    public void setCount(int count) {
        this.count = count;
        dataCalc();
    }    
    public void dataCalc(){
        // 표시되는 페이지 번호중 마지막 번호
        endPageNum = (int) Math.ceil((double)num / (double)pageNum_count) * pageNum_count;

        // 표시되는 페이지 번호중 첫번째 번호
        startPageNum = endPageNum - (pageNum_count-1);
        // 마지막 페이지 번호 재계산
        int endPageNum_tmp = (int) (Math.ceil((double)count / (double)pageNum_count));
        if (endPageNum > endPageNum_tmp) {
            endPageNum = endPageNum_tmp;            
        }
        prev = startPageNum == 1 ? false:true;
        next = endPageNum * pageNum_count >= count ? false:true;
        // 출력할 게시물
        displayPost = (num - 1)*postNum;
    }
    
    

}