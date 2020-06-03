package com.pptaa.test1.VO;

import lombok.Data;

@Data
public class Board {
    private int board_idx;
    private String board_title;
    private String board_writer;
    private String board_content;
    private String datetime; 
    private int viewcnt;
}