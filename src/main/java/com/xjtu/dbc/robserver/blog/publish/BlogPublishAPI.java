package com.xjtu.dbc.robserver.blog.publish;

import com.xjtu.dbc.robserver.blog.publish.entity.BlogPublishDto;
import com.xjtu.dbc.robserver.blog.publish.entity.TagDto;
import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.common.Utils;
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
     * 重命名tag
     * @param dto 包含原tag名称与新tag名称
     * @param token 用户令牌,用户获取用户id
     */
    @PostMapping("/tag/rename")
    public Result renameTag(@RequestBody TagDto dto, @RequestHeader("Token") String token) {
        try {
            int u_id = TokenUtils.getUserInfo(token,commonService).getUserid();//当前用户id
            Integer cnt = blogPublishService.getTagCount(dto.getTagNameNew(), u_id);

            if (cnt>0) {
                return Result.fail(Result.ERR_CODE_BUSINESS, "该名称已存在！");
            }

            blogPublishService.renameTag(dto.getTagname(), u_id, dto.getTagNameNew());
            return Result.successMsg("修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_SYS, "修改失败！");
        }
    }

    /**
     * 新增tag
     * @param tag 包含tag信息
     * @param token 用户令牌
     */
    @PostMapping("/tag/add")
    public Result addTag(@RequestBody Tag tag, @RequestHeader("Token") String token) {

        if (tag.getTagname() == null || "".equals(tag.getTagname().trim())) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "Tag名不能为空!");
        }

        try {
            int t_id = blogPublishService.getNewTagId();
            int u_id = TokenUtils.getUserInfo(token,commonService).getUserid();//当前用户id

            if (blogPublishService.getTagCount(tag.getTagname(), u_id)>0) {
                return Result.fail(Result.ERR_CODE_BUSINESS, "该Tag已存在！");
            }

            tag.setTagid(t_id);
            tag.setOwnerid(u_id);

            blogPublishService.addTag(tag);
            return Result.successMsg("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_SYS, "新增tag失败！");
        }
    }

    /**
     * 删除tag, 通过tag名删除
     * @param tag 包含tag名
     * @param token 用户令牌
     */
    @PostMapping("/tag/delete")
    public Result deleteTag(@RequestBody Tag tag, @RequestHeader("Token") String token) {
        if (tag.getTagname() == null || "".equals(tag.getTagname().trim())) {
            return Result.fail(Result.ERR_CODE_BUSINESS, "Tag名不能为空!");
        }

        try {
            int u_id = TokenUtils.getUserInfo(token,commonService).getUserid();//当前用户id

            blogPublishService.deleteTag(tag.getTagname(), u_id);
            return Result.successMsg("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_SYS, "删除Tag失败！");
        }
    }

    /**
     * 编辑博客
     * @param dto 随笔数据传输对象，包含新增/被编辑随笔的数据
     * @param token 令牌
     * @return 新增/编辑成功返回data: 随笔id；失败返回fail
     */
    @PostMapping("/publish")
    public Result blogPublish(@RequestBody BlogPublishDto dto, @RequestHeader("Token") String token) {
        int myid = TokenUtils.getUserInfo(token,commonService).getUserid();//当前用户id
        if (blogPublishService.getUserStatus(myid) != 200) {
          return Result.fail(Result.ERR_CODE_BUSINESS, "您当前无法发言！");
        }
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
            int articleid = blogPublishService.getNewArticleId();
            dto.setArticleid(articleid);

            // 设置作者authorid
            int authorid = TokenUtils.getUserInfo(token, commonService).getUserid();
            dto.setAuthorid(authorid);

            try {
                blogPublishService.addBlog(dto);
                blogPublishService.addTagForBlog(myid,dto);
                // 检查博客是否需要审核
                if (dto.getArticlestatus()!=null && dto.getArticlestatus()==401) {
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
            int blogStatus = blogPublishService.getArticleStatus(dto.getArticleid());
            if (blogStatus==403) {
                return Result.fail(Result.ERR_CODE_BUSINESS, "博客被隐藏，无法编辑！");
            }
            // 设置最后编辑时间
            dto.setLastmodifytime(Utils.getNow());

            try {
                blogPublishService.updateBlogByArticleId(dto);
                blogPublishService.updateBlogTag(myid,dto);
                // 检查博客是否需要审核
                if (blogStatus==401) {
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
     * 返回随笔编辑数据传输对象
     * @return 成功返回包含随笔的编辑信息的对象，失败返回fail
     */
    @GetMapping("/get/essayEditDto")
    public Result getEssayEditDto(@RequestParam("articleid") int articleid, @RequestHeader("Token") String token) {
        try {
            BlogPublishDto dto= blogPublishService.getBlogPublishDtoByArticleId(articleid);

            /*
             * 检查是否是随笔的作者
             */
            int authorid = TokenUtils.getUserInfo(token, commonService).getUserid();
            if (!dto.getAuthorid().equals(authorid)) {
                return Result.fail(Result.ERR_CODE_BUSINESS, "您不是该博客的作者，没有修改权限！");
            }

            // 检查博客是否被隐藏
            if (dto.getArticlestatus()==403) {
                return Result.fail(Result.ERR_CODE_BUSINESS, "博客被隐藏，无法编辑！");
            }

            return Result.successData(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_SYS, "系统错误！获取博客信息失败！");
        }
    }

}

