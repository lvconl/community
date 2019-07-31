package life.community.mapper;

import life.community.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lvconl
 * 用户数据库映射
 */
@Mapper
public interface UserMapper {
    /**
     * 新增用户
     * @param user 新增用户实体
     */
    @Insert("INSERT INTO user (name, account_id, token, gmt_create, gmt_modified) VALUES (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified})")
    void addUser(User user);
}
