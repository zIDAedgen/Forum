package aedgen.forum.forum.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data

public class PageDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    //private List<Integer> pages;
    private List<Integer> pages = new ArrayList<>();


    public void pageProcessing(Integer totalCounte, Integer page, Integer size) {
        Integer totalPage;
        if (totalCounte % size == 0) {
            totalPage = totalCounte / size;
        } else {
            totalPage = totalCounte / size + 1;
        }


        pages.add(page);
        for (int i = 0; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(page - i, 0);
            }

            if (page + i <= totalCounte) {
                pages.add(page + 1);
            }
        }
        //Whether display the previousPage
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        //Whether display the nextPage
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        //Whether display the firstPage
        if(pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        //Whether display the lastPage
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
