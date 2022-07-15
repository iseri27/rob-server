package com.xjtu.dbc.robserver.question.home;

import com.github.pagehelper.PageInfo;
import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.common.model.category.Category;
import com.xjtu.dbc.robserver.common.page.PageParam;
import com.xjtu.dbc.robserver.manage.sensitive.SensitiveService;
import com.xjtu.dbc.robserver.question.answer.QuestionAnswerService;
import com.xjtu.dbc.robserver.question.home.entity.QuestionDetailsDto;
import com.xjtu.dbc.robserver.question.home.entity.QuestionHomeListDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question/home")
public class QuestionHomeAPI {

    @Resource
    private CommonService commonService;

    @Resource
    private  QuestionHomeService questionHomeService;

    @Resource
    private QuestionAnswerService questionAnswerService;

    @Resource
    private SensitiveService sensitiveService;

    @GetMapping("/qlist")
    public Result getQList(PageParam pageParam, @RequestParam("Number") int Number, @RequestParam("Categoryid") int Categoryid,@RequestHeader("Token") String token) {
        try{
            int selectid = Number;
            int cid = Categoryid;
            int userid = TokenUtils.getUserInfo(token,commonService).getUserid();
            if(selectid == 1){
                //获取全部问题
                Map<String, Object> questionListPage = questionHomeService.getAllQuestionList(pageParam,cid, userid);
                List<QuestionHomeListDto> questionHomeListDtoList = (List<QuestionHomeListDto>) questionListPage.get("list");
                for (QuestionHomeListDto questionHomeListDto: questionHomeListDtoList) {
                    if(questionHomeListDto.getQuestiontitle() != null){
                        questionHomeListDto.setQuestiontitle(sensitiveService.filter(questionHomeListDto.getQuestiontitle(),'*'));
                    }
                }
                return Result.successData(questionListPage);
            }
            else if(selectid == 2){
                //获取未解决问题

                Map<String, Object> questionListPage = questionHomeService.getNotSolveQuestionList(pageParam,cid, userid);
                List<QuestionHomeListDto> questionHomeListDtoList = (List<QuestionHomeListDto>) questionListPage.get("list");
                for (QuestionHomeListDto questionHomeListDto: questionHomeListDtoList) {
                    if(questionHomeListDto.getQuestiontitle() != null){
                        questionHomeListDto.setQuestiontitle(sensitiveService.filter(questionHomeListDto.getQuestiontitle(),'*'));
                    }
                }
                return Result.successData(questionListPage);
            }
            else{
                //获取已解决问题
                Map<String, Object> questionListPage = questionHomeService.getSolveQuestionList(pageParam,cid, userid);
                List<QuestionHomeListDto> questionHomeListDtoList = (List<QuestionHomeListDto>) questionListPage.get("list");
                for (QuestionHomeListDto questionHomeListDto: questionHomeListDtoList) {
                    if(questionHomeListDto.getQuestiontitle() != null){
                        questionHomeListDto.setQuestiontitle(sensitiveService.filter(questionHomeListDto.getQuestiontitle(),'*'));
                    }
                }
                return Result.successData(questionListPage);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_SYS, "未找到匹配项目！");
        }
    }

    @GetMapping("/details")
    public Result getQuestionDetails(@RequestParam("qid") int qid,@RequestHeader("Token") String token) {
        try{
            int userid = TokenUtils.getUserInfo(token,commonService).getUserid();
            int questionid = qid;
            QuestionDetailsDto questionDetailsDto= questionHomeService.getQuestionDetails(questionid);
            questionDetailsDto.setTaglist(questionHomeService.getTagListByQuestionid(questionid));
            questionDetailsDto.setVote_type(questionAnswerService.getVoteTypeByU_A_id(userid,questionid));
            questionDetailsDto.setQuestiontitle(sensitiveService.filter(questionDetailsDto.getQuestiontitle(),'*'));
            questionDetailsDto.setQuestioncontent(sensitiveService.filter(questionDetailsDto.getQuestioncontent(), '*'));
            return Result.success("查找悬赏详情成功",questionDetailsDto);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_BUSINESS, "查找悬赏详情失败！");
        }
    }

    @GetMapping("/category")
    public Result getCategory(){
        try{
            List<Category> categoryList = questionHomeService.getCategory();
            return Result.success("查询分区成功！",categoryList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_BUSINESS, "查询分区失败！");
        }

    }

//    @GetMapping("/preview")//待实现
//    public Result tagList(QuestionPreviewDto dto){
//        Map<String,Object> page = questionHomeService.getQuestionList(dto);
//        return Result.success("查询悬赏成功！",page);
//    }
    @GetMapping("/search")
    public Result search(PageParam pageParam,@RequestParam("str") String str,@RequestHeader("Token") String token){
        try{
            int userid = TokenUtils.getUserInfo(token,commonService).getUserid();
            String string = str;
            Map<String, Object> questionListPage = questionHomeService.searchQuestionList(pageParam,string,userid);
            List<QuestionHomeListDto> questionHomeListDtoList = (List<QuestionHomeListDto>) questionListPage.get("list");
            for (QuestionHomeListDto questionHomeListDto: questionHomeListDtoList) {
                if(questionHomeListDto.getQuestiontitle() != null){
                    questionHomeListDto.setQuestiontitle(sensitiveService.filter(questionHomeListDto.getQuestiontitle(),'*'));
                }
            }
            return Result.successData(questionListPage);

        }catch(Exception e){
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_BUSINESS, "搜索失败！");
        }

    }

}
