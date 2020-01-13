package aedgen.forum.forum.Mapper;


import aedgen.forum.forum.Model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into thequestions (title, description, gmt_create, gmt_modified, creator, tag) values (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    public void create(Question question);

    @Select("select * from thequestions limit #{offset}, #{size}")
        //This is the wrong method. While the parameter is not an object, we need to map it
        //List<Question> list(Integer offset, Integer size);
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from thequestions")
    Integer count();
}
