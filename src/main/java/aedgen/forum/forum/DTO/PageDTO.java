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


    public void pageProcessing(Integer totalCount, Integer page, Integer size) {
        this.page = page;
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }


        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i );
            }

            if (page + i <= totalPage) {
                pages.add(page + i);
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
