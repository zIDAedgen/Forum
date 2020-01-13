package aedgen.forum.forum.service;


import aedgen.forum.forum.DTO.QuestionDTO;
import aedgen.forum.forum.Mapper.QuestionMapper;
import aedgen.forum.forum.Mapper.UserMapper;
import aedgen.forum.forum.Model.Question;
import aedgen.forum.forum.Model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired(required = false)
    private QuestionMapper questionMapper;
    @Autowired(required = false)
    private UserMapper userMapper;


    public PageDTO list(Integer page, Integer size) {

        //size*(page - 1)
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PageDTO pageDTO = new PageDTO();
        for (Question question : questions) {
            System.out.println("Creator");
            System.out.println(question.getCreator());
            User user = userMapper.findById(question.getCreator());
            //exchange question to DTO
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageDTO.setQuestionnarire(questionDTOList);
        Integer totalCounte = questionMapper.count();
        pageDTO.pageProcessing(totalCounte, page, size );

        return pageDTO;
    }
}
