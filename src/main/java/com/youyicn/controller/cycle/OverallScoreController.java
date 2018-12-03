package com.youyicn.controller.cycle;

import com.github.pagehelper.PageInfo;
import com.youyicn.entity.LoginSession;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.CycleScoreModel;
import com.youyicn.service.cycle.IOverallScoreService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Title overallScoreController
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author wangtan
 * @Version 1.0.0
 * @Create 2018/11/26 19:42
 */
@Controller
public class OverallScoreController {

    @Autowired
    private IOverallScoreService overallScoreServiceImpl;

    //进入360互评首页，返回评分模板列表
    @RequestMapping("/overallScore/index.htm")
    public String scoreIndex(HttpServletResponse response, HttpServletRequest request, ModelMap model, String type, String li, String div, Integer current) {

        //封装页面信息参数
        String userName = request.getParameter("userName");
        model.put("userName", userName);
        model.put("type", type);
        model.put("li", li);
        model.put("div", div);

        //获取模板列表
        List<CycleScoreModel> allScoreModelList = overallScoreServiceImpl.findAllScoreModelList(current);
        PageInfo<CycleScoreModel> page = new PageInfo<>(allScoreModelList);

        model.put("page", page);
        return "/overallScore/index";
    }


    /** 
     *@title  
     *@description 添加评分模板
     *@param  [response, request, model, type, li, div, scoreModel]
     *@return  java.lang.String
     *@author  wangtan
     *@createDate  2018/12/3 11:47
    */
    @RequestMapping(value="/overallScore/addScoreModel.htm",method = RequestMethod.POST)
    public String addScoreModel(HttpServletResponse response, HttpServletRequest request, ModelMap model, String type, String li, String div, CycleScoreModel scoreModel,String[] itemNames,String[] itemScores){
        model.put("type", type);
        model.put("li", li);
        model.put("div", div);

        //判断关键字段是否为Null
        if(StringUtils.isBlank(scoreModel.getModelName())|| itemScores.length ==0 || itemNames.length == 0 || itemScores.length != itemNames.length){
            model.addAttribute("requestMessage", "模板名称及评分项不能为空！");
            return "/overallScore/index";
        }
        LoginSession<User> loginSession = (LoginSession<User>) request.getSession().getAttribute("loginSession");
        scoreModel.setUserId(loginSession.getUserID());
        String scoreItem = potScoreItem(itemNames, itemScores);
        scoreModel.setScoreItem(scoreItem);
        overallScoreServiceImpl.addScoreModel(scoreModel);
        model.addAttribute("requestMessage", "添加评分模板成功！");
        return "/overallScore/index";
    }

    /** 
     *@title  
     *@description 封装评分项
     *@param  [itemNames, itemScores]
     *@return  java.lang.String
     *@author  wangtan
     *@createDate  2018/12/3 16:44
    */
    private String potScoreItem(String[] itemNames,String[] itemScores){
        StringBuilder scoreItemBuilder = new StringBuilder();
        if(itemScores.length!= itemNames.length){
            return null;
        }
        Integer len = itemNames.length-1;
        while (len < 0){
            scoreItemBuilder.append(itemNames[len]).append(":").append(itemScores[len]);
            --len;
        }
        return scoreItemBuilder.toString();
    }
}
