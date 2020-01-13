package aedgen.forum.forum.DTO;

import lombok.Data;

import java.util.List;
@Data

public class PageDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages;

}
