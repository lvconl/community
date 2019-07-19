package life.community.provider;

import com.alibaba.fastjson.JSON;
import life.community.dto.AccessTokenDTO;
import life.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author lvconl
 * github授权用三方工具类
 */
@Component
public class GithubProvider {

    /**
     * 根据用户获得的code获取用户token
     * @param accessTokenDTO  请求用数据传输类
     * @return 获得的token
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json;charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string().split("&")[0].split("=")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据accesstoken获得用户信息
     * @param accessToken 根据code获得的token
     * @return 用户信息
     */
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return JSON.parseObject(response.body().string(), GithubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
