package com.youyicn.controller.cycle;

import com.youyicn.entity.LoginSession;
import com.youyicn.entity.RspInfo;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.CycleScore;
import com.youyicn.service.cycle.IScoreService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title OverallScoreController
 * @Description 360评分管理
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author wangtan
 * @Version 1.0.0
 * @Create 2018/12/14 10:34
 */
@Controller
public class OverallScoreController {

    @Autowired
    private IScoreService scoreService;

    /** 
     *@title
     *@description 添加360综合评分记录
     *@param  []
     *@return  java.lang.String
     *@author  wangtan
     *@createDate  2018/12/14 11:16
    */
    @RequestMapping(value="",method = RequestMethod.POST)
    public RspInfo addScore(HttpServletResponse response, HttpServletRequest request, ModelMap model, String type, String li, String div, Integer pageIndex,CycleScore cycleScore){
        RspInfo rspInfo = new RspInfo();
        //参数校验
        boolean checkParam = checkParam(cycleScore);
        if(!checkParam){
            rspInfo.setRspMessage("关键参数不能为空！");
            return rspInfo;
        }
        LoginSession<User> loginSession = (LoginSession<User>) request.getSession().getAttribute("loginSession");
        int userID = loginSession.getUserID();
        //通过评分人查询所评学生所在的可科室和基地



        //注：科室和基地Id未获取，前端可传则直接从cycleScore对象中取，若前端不可传则查询评分人所在基地、科室
        int roomId=1;
        int baseId=1;




        //通过被评分人和记录所属科室、基地查询评分记录
        CycleScore cycleScoreBean = scoreService.findScoreByCondition(cycleScore.getTargetId(),roomId,baseId);
        if (cycleScoreBean != null || cycleScoreBean.getScoreId() != null){
            rspInfo.setRspMessage("该生评分记录已存在不可重复添加！");
            return rspInfo;
        }
        cycleScore.setRatersId(userID);
        cycleScore.setRoomId(roomId);
        cycleScore.setBaseId(baseId);
        scoreService.addScore(cycleScore);
        rspInfo.setRspMessage("添加评分记录成功！");
        return rspInfo;
    }

    /** 
     *@title  
     *@description 必传参数校验
     *@param  [cycleScore]
     *@return  boolean
     *@author  wangtan
     *@createDate  2018/12/14 11:28
    */
    private boolean checkParam(CycleScore cycleScore){
        Integer modelId = cycleScore.getModelId();
        String targetId = cycleScore.getTargetId();
        String scoreDetail = cycleScore.getScoreDetail();
        Double totalScore = cycleScore.getTotalScore();
        if(modelId == null || StringUtils.isBlank(targetId)|| StringUtils.isBlank(scoreDetail)|| totalScore == null){
            return false;
        }
        return true;
    }
}
