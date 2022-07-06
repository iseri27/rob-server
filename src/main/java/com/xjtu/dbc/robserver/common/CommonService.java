package com.xjtu.dbc.robserver.common;


import com.xjtu.dbc.robserver.common.model.user.User;
import org.springframework.web.multipart.MultipartFile;

public interface CommonService {
    User getUserById(Integer u_id);

    User getUserWithoutPwdById(Integer u_id);

    String getUserAvatarByName(String u_name);

    String selectUserNameById(Integer u_id);

    String uploadImg(MultipartFile multipartFile, String token) throws Exception;

    Integer selectUserCountByName(String u_name);

//    /**
//     * 从数据库的数据字典中获取值的列表
//     * @param dd_type 要获取的数据类型
//     * @return List<String>
//     */
//    List<String> getDataListByKeyFromDataDictionary(String dd_type);

//    /**
//     * 通过请求路径获取权限的id
//     * @param url 请求路径，如login
//     * @return pri_id 权限id
//     */
//    String getPriIdByUrl(String url);
//
//    /**
//     * 获取无需限制的权限的请求路径
//     */
//    List<String> getUnlimitedPrivileges();
//
//    /**
//     * 获取用户是否有权限
//     */
//    Boolean checkPrivilege(String uId, String priId);
//
//    /**
//     * 添加日志(不要使用这个)
//     */
//    void addLog(String uId, String request, Integer statusCode, String logContent);
//
//    /**
//     * 添加日志 - 成功状态 会在日志内容前添加[SUCCESS]
//     * @param clientToken 请求头token,用来获取用户id
//     * @param request 请求路径,用来获取权限id
//     * @param logContent 要写入日志的内容
//     */
//    void addSuccessLog(String clientToken, String request, String logContent);
//
//    /**
//     * 添加日志 - 失败状态 会在日志内容前添加[FAILED]
//     * @param clientToken 请求头token,用来获取用户id
//     * @param request 请求路径,用来获取权限id
//     * @param logContent 要写入日志的内容
//     */
//    void addFailedLog(String clientToken, String request, String logContent);
}
