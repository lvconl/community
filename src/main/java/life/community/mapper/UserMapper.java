package life.community.mapper;

import life.community.entity.User;
import org.apache.ibatis.annotations.*;

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

    /**
     * 根据token查询用户
     * @param token 用户的token
     * @return 查询到的用户信息
     */
    @Select("SELECT * FROM user WHERE token=#{token}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "accountId", column = "account_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "token", column = "token"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified")
    })
    User queryUserByToken(@Param("token") String token);
}
