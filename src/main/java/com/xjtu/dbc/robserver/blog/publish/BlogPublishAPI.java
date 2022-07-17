package com.xjtu.dbc.robserver.blog.publish;

import com.xjtu.dbc.robserver.blog.publish.entity.BlogPublishDto;
import com.xjtu.dbc.robserver.common.*;
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
     * @param token 用户令牌
     * @return Result { data: tag id 列表 }
     */
    @GetMapping("/get/tagList")
    public Result gelAllTagList(@RequestHeader("Token") String token) {
        try {
            // 当前用户 ID
            Integer myId = TokenUtils.getUserInfo(token,commonService).getUserid();

            List<Tag> list = blogPublishService.getAllTagListByUserId(myId);
            return Result.successData(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_SYS, "获取Tag列表失败！");
        }
    }

    /**
     * 新增 tag
     * @param tag 包含 tag 信息
     * @param token 用户令牌
     */
    @PostMapping("/tag/add")
    public Result addTag(@RequestBody Tag tag, @RequestHeader("Token") String token) {

        // 检查 tag 名
        if (tag.getTagname() == null || "".equals(tag.getTagname().trim())) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "Tag名不能为空!");
        }

        try {
            // 获取当前用户的 ID
            Integer userId = TokenUtils.getUserInfo(token,commonService).getUserid();

            // 检查 tag 是否已经存在
            if (blogPublishService.getTagCount(tag.getTagname(), userId) > 0) {
                return Result.fail(Result.ERR_CODE_BUSINESS, "该Tag已存在！");
            }

            // 新建 tag
            tag.setOwnerid(userId);
            blogPublishService.addTag(tag);

            return Result.successMsg("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_SYS, "新增tag失败！");
        }
    }

    /**
     * 编辑博客
     * @param blogPublishDto 随笔数据传输对象，包含新增/被编辑随笔的数据
     * @param token 令牌
     * @return 新增/编辑成功返回data: 随笔id；失败返回fail
     */
    @PostMapping("/publish")
    public Result blogPublish(@RequestBody BlogPublishDto blogPublishDto, @RequestHeader("Token") String token) {
        // 获取当前用户的 ID
        Integer authorId = TokenUtils.getUserInfo(token,commonService).getUserid();
        if (blogPublishService.getUserStatus(authorId) != Constants.USER_STATUS_NORMAL) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "您当前无法发言！");
        }

        // 检查标题是否为空
        if (blogPublishDto.getTitle() == null || "".equals(blogPublishDto.getTitle())) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "标题不能为空！");
        }

        // 检查内容是否为空
        if (blogPublishDto.getContent() == null || "".equals(blogPublishDto.getContent())) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "内容不能为空！");
        }

        // 检查是否选择了分区
        if (blogPublishDto.getCategoryid() == null) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "请选择分区!");
        }

        // 判断是第一次提交还是再次编辑
        if (blogPublishDto.getArticleid() == null) {
            // 第一次提交
            // 设置作者 ID
            blogPublishDto.setAuthorid(authorId);

            try {
                blogPublishService.addBlog(blogPublishDto);
                blogPublishService.addTagForBlog(authorId, blogPublishDto);
                // 检查博客是否需要审核
                if (blogPublishDto.getArticlestatus() != null && blogPublishDto.getArticlestatus() == Constants.ARTICLE_STATUS_WAITING_CHECK) {
                    return Result.fail(Result.ERR_CODE_BUSINESS, "博客正在审核中！");
                }
                // 添加发布历史
                commonService.addHistory(authorId, Constants.HISTORY_PUBLISH, blogPublishDto.getArticleid());
                return Result.success("发布成功！", blogPublishDto.getArticleid());
            } catch (Exception e) {
                e.printStackTrace();
                return Result.fail(Result.ERR_CODE_SYS, "系统错误, 无法发布！");
            }
        } else {
            // 再次编辑
            // 检查博客是否被隐藏
            int blogStatus = blogPublishService.getArticleStatus(blogPublishDto.getArticleid());
            if (blogStatus == Constants.ARTICLE_STATUS_HIDDEN) {
                return Result.fail(Result.ERR_CODE_BUSINESS, "博客被隐藏，无法编辑！");
            }

            try {
                blogPublishService.updateBlogByArticleId(blogPublishDto);
                blogPublishService.updateBlogTag(blogPublishDto.getTags(), blogPublishDto.getArticleid());
                // 检查博客是否需要审核
                if (blogStatus == Constants.ARTICLE_STATUS_WAITING_CHECK) {
                    return Result.fail(Result.ERR_CODE_BUSINESS, "由于正文字数超过500或包含图片，博客正在审核中！");
                }
                return Result.success("修改成功！", blogPublishDto.getArticleid());
            } catch (Exception e) {
                e.printStackTrace();
                return Result.fail(Result.ERR_CODE_SYS, "无法修改！");
            }

        }

    }

    /**
     * 返回随笔编辑数据传输对象
     * @return 成功返回包含随笔的编辑信息的对象，失败返回fail
     */
    @GetMapping("/get/articlePublishDto")
    public Result getArticlePublishDto(@RequestParam("articleId") Integer articleId, @RequestHeader("Token") String token) {
        try {
            BlogPublishDto blogPublishDto= blogPublishService.getBlogPublishDtoByArticleId(articleId);

            // 检查是否是博客的作者
            Integer authorId = TokenUtils.getUserInfo(token, commonService).getUserid();
            if (!blogPublishDto.getAuthorid().equals(authorId)) {
                return Result.fail(Result.ERR_CODE_BUSINESS, "您不是该博客的作者，没有修改权限！");
            }

            // 检查博客是否被隐藏
            if (blogPublishDto.getArticlestatus() == Constants.ARTICLE_STATUS_HIDDEN) {
                return Result.fail(Result.ERR_CODE_BUSINESS, "博客被隐藏，无法编辑！");
            }

            return Result.successData(blogPublishDto);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_SYS, "系统错误！获取博客信息失败！");
        }
    }
}

