package com.better.modules.base.util;

import com.better.modules.base.api.Result;
import com.better.modules.base.constant.CommonConstant;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import java.io.IOException;

/**
 * @author sheldon
 * @date 2021/5/17 9:25
 */
public class SMSUtil {

    private static final  int appid = CommonConstant.ADDID;

    private static final String appkey = CommonConstant.APPKEY;
    //签名ID
    private static final String sign = CommonConstant.SIGN;
    //模板ID
    private static final int templateId = CommonConstant.TEMPLATEID;

    public static Result sendMsg(String mobile,String code){
        //发送短信
        SmsMultiSender  sender = new SmsMultiSender(appid, appkey);
        String[] mobileStr = {mobile};
        try {
            String[] params = {code};
            SmsMultiSenderResult  resultcode = sender.sendWithParam("86", mobileStr,
                    templateId, params , sign, "","");
            if(resultcode.result == 0){
                return Result.ok();
            }
        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.error("短信发送失败");

    }



    public static void main(String[] args) {
        Result result=SMSUtil.sendMsg("15366221553","545455");
        System.out.println(result);
    }

}
