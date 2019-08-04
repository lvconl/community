package life.community.mapper;

import life.community.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 问题相关mapper
 * @author lvconl
 */
@Mapper
public interface QuestionMapper {
    /**
     * 新增问题
     * @param question 新增问题实例
     */
    @Insert("INSERT INTO question (title, description, tag, gmt_create, gmt_modified, creator) " +
            "VALUES(#{title}, #{description}, #{tag}, #{gmtCreate}, #{gmtModified}, #{creator})")
     void addQuestion(Question question);
}
