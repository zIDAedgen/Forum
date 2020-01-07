package aedgen.forum.forum.DTO;

import aedgen.forum.forum.Model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer ViewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
