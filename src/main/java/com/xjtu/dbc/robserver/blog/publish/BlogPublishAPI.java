package com.xjtu.dbc.robserver.blog.publish;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.common.Utils;
import com.xjtu.dbc.robserver.common.model.article.Article;
import com.xjtu.dbc.robserver.common.model.tag.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogPublishAPI {
    @Resource
    private BlogPublishService blogPublishService;

    @Resource
    private CommonService commonService;

    /**
     * 获取该用户所有的tag(tagid)
     */
    @GetMapping("/get/tagList")
    public Result gelAllTagList(@RequestHeader("Token") String token) {
        try {
            int myid = TokenUtils.getUserInfo(token,commonService).getUserid();//当前用户id

            List<Tag> list = blogPublishService.getAllTagListByUserid(myid);
            return Result.successData(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_SYS, "获取Tag列表失败！");
        }
    }

    /**
     * 编辑博客
     * @param dto 随笔数据传输对象，包含新增/被编辑随笔的数据
     * @param token 令牌
     * @return 新增/编辑成功返回data: 随笔id；失败返回fail
     */
    @PostMapping("/publish")
    public Result blogPublish(@RequestBody BlogEditDto dto, @RequestHeader("Token") String token) {
        if (blogPublishService.getUserStatus(dto.getAuthorid()) != 200) {
          return Result.fail(Result.ERR_CODE_BUSINESS, "您当前无法发言！");
        }
        int myid = TokenUtils.getUserInfo(token,commonService).getUserid();//当前用户id
        if (dto.getContent()==null || "".equals(dto.getContent())) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "内容不能为空！");
        }


        /**
         * 首先需要判断是第一次提交还是再次编辑
         */
        if (dto.getArticleid()==null) {
            /**
             * 第一次提交
             */
            // 设置时间选项
            dto.setCreatetime(Utils.getNow());
            dto.setLastmodifytime(dto.getCreatetime());

            // 获取新的articleid
            int articleid = blogPublishService.getNewArticleid();
            dto.setArticleid(articleid);

            // 设置作者authorid
            int authorid = TokenUtils.getUserInfo(token, commonService).getUserid();
            dto.setAuthorid(authorid);

            try {
                blogPublishService.addBlog(dto);
                blogPublishService.addTag(myid,dto);
                blogPublishService.addTagForBlog(myid,dto);
                // 检查博客是否需要审核
                if (dto.getArticlestatus()==401) {
                    return Result.fail(Result.ERR_CODE_BUSINESS, "由于正文字数超过500或包含图片，博客正在审核中！");
                }
                return Result.success("发布成功！", dto.getArticleid());
            } catch (Exception e) {
                e.printStackTrace();
                return Result.fail(Result.ERR_CODE_SYS, "无法发布！");
            }
        } else {
            /**
             * 再次编辑
             */
            // 检查博客是否被隐藏
            if (dto.getArticlestatus()==403) {
                return Result.fail(Result.ERR_CODE_BUSINESS, "博客被隐藏，无法编辑！");
            }
            // 设置最后编辑时间
            dto.setLastmodifytime(Utils.getNow());

            try {
                blogPublishService.updateBlogByArticleid(dto);
                blogPublishService.updateBlogTag(myid,dto);
                // 检查博客是否需要审核
                if (dto.getArticlestatus()==401) {
                    return Result.fail(Result.ERR_CODE_BUSINESS, "由于正文字数超过500或包含图片，博客正在审核中！");
                }
                return Result.success("修改成功！", dto.getArticleid());
            } catch (Exception e) {
                e.printStackTrace();
                return Result.fail(Result.ERR_CODE_SYS, "无法修改！");
            }

        }

    }

    /**
     * 获取博客详情
     */
    @GetMapping("/get/blogDetail")
    public Result getEssayDetail(@RequestParam("articleid") int articleid, @RequestParam("Token") String token) {
        try {
            BlogDetailDto blog = blogPublishService.getBlogDetailByArticleid(articleid);

            if (blog.getArticlestatus()!=401) {
                // 该随笔未发布，需要验证登录状态，未登录状态下或并非作者都不能查看
                try {
                    TokenUtils.verifyToken(token, commonService);
                } catch (Exception e) {
                    e.printStackTrace();
                    return Result.fail(Result.ERR_CODE_BUSINESS, "随笔未发布，请登录！");
                }

                // 虽然该用户登录了，但并非是这篇随笔的作者
                int myid = TokenUtils.getUserInfo(token,commonService).getUserid();//当前用户id;
                if (myid!=blog.getAuthorid()) {
                    return Result.fail(Result.ERR_CODE_BUSINESS, "不能编辑不属于自己的博客！");
                }
            }
            return Result.successData(blog);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_SYS, "系统错误！获取博客信息失败！");
        }
    }

    /**
     * 返回随笔编辑数据传输对象
     * @return 成功返回包含随笔的编辑信息的对象，失败返回fail
     */
    @GetMapping("/get/essayEditDto")
    public Result getEssayEditDto(@RequestParam("articleid") int articleid, @RequestHeader("Token") String token) {
        try {
            BlogEditDto dto= blogPublishService.getBlogEditDtoByArticleid(articleid);

            /*
             * 检查是否是随笔的作者
             */
            int authorid = TokenUtils.getUserInfo(token, commonService).getUserid();
            if (!dto.getAuthorid().equals(authorid)) {
                return Result.fail(Result.ERR_CODE_BUSINESS, "登录无效！");
            }

            // 检查博客是否被隐藏
            if (dto.getArticlestatus()==402) {
                return Result.fail(Result.ERR_CODE_BUSINESS, "博客被隐藏，无法编辑！");
            }

            return Result.successData(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_SYS, "系统错误！获取博客信息失败！");
        }
    }

}

