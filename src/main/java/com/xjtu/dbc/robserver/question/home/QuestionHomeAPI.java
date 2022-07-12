package com.xjtu.dbc.robserver.question.home;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.model.category.Category;
import com.xjtu.dbc.robserver.question.answer.QuestionAnswerService;
import com.xjtu.dbc.robserver.question.home.entity.QuestionDetailsDto;
import com.xjtu.dbc.robserver.question.home.entity.QuestionHomeListDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/qlist")
    public Result getQList(@RequestParam("Number") int Number,@RequestParam("Categoryid") int Categoryid) {
        try{
            int selectid = Number;
            int cid = Categoryid;
            if(selectid == 1){
                List<QuestionHomeListDto> listDto = questionHomeService.getAllQuestionList(cid);
                //获取每条评论的点赞数与点踩数
                for(int i=0; i<listDto.size();i++){
                    listDto.get(i).setTaglist(questionHomeService.getTagListByQuestionid(listDto.get(i).getQuestionid()));
                }
                return Result.success("获取悬赏大厅的全部问题列表成功!",listDto);
            }
            else if(selectid == 2){

                List<QuestionHomeListDto> listDto = questionHomeService.getNotSolveQuestionList(cid);

                //获取每条评论的点赞数与点踩数
                for(int i=0; i<listDto.size();i++){
                    listDto.get(i).setTaglist(questionHomeService.getTagListByQuestionid(listDto.get(i).getQuestionid()));
                }
                return Result.success("获取悬赏大厅的未解决问题列表成功!", listDto);

            }
            else{

                List<QuestionHomeListDto> listDto = questionHomeService.getSolveQuestionList(cid);

                //获取每条评论的点赞数与点踩数
                for(int i=0; i<listDto.size();i++){
                    listDto.get(i).setTaglist(questionHomeService.getTagListByQuestionid(listDto.get(i).getQuestionid()));
                }
                return Result.success("获取悬赏大厅的已解决问题列表成功!", listDto);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_SYS, "未找到匹配项目！");
        }


    }

    @GetMapping("/details")
    public Result getQuestionDetails(@RequestParam("qid") int qid,@RequestParam("uid") int uid) {
        try{
            int userid = uid;
            int questionid = qid;
            QuestionDetailsDto questionDetailsDto= questionHomeService.getQuestionDetails(questionid);
            questionDetailsDto.setTaglist(questionHomeService.getTagListByQuestionid(questionid));
            questionDetailsDto.setVote_type(questionAnswerService.getVoteTypeByU_A_id(userid,questionid));
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
}
