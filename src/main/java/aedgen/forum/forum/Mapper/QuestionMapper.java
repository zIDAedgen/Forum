package aedgen.forum.forum.Mapper;


import aedgen.forum.forum.Model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("insert into questions (title, description, gmt_create, gmt_modified, post_man, tag) values (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    public void create(Question question);
}
