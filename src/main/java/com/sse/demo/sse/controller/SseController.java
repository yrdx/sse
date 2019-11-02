/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sse.demo.sse.controller;

import com.sse.demo.sse.event.PayCompletedEvent;
import com.sse.demo.sse.listener.PayCompletedListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 客户端向后台(SseController->push)发送异步请求，客户端处于监听等待状态;
 * 微信(支付宝)支付成功后回调后台(SseController->payCallback模拟);
 * payCallback方法通过applicationContext.publishEvent向系统内部发送支付完成事件;
 * push方法通过payCompletedListener监听事件并通过SseEmitter发送给客户端
 * @author zhuls
 * @version V1.0
 * @since 2019-07-10 13:03
 */
@RestController
public class SseController {

    @Autowired
    private ApplicationContext context;

   @Autowired
    private PayCompletedListener listener;

   @GetMapping("/push")
   public SseEmitter push(@RequestParam("payRecordId") Long payRecordId) {
       final SseEmitter sseEmitter = new SseEmitter();
       try {
           listener.addSseEmitter(payRecordId, sseEmitter);
       } catch (Exception e) {
           sseEmitter.completeWithError(e);
       }
       return sseEmitter;
   }


    @GetMapping("/pay-callback")
    public String payCallback(@RequestParam Long payRecordId){
        context.publishEvent(new PayCompletedEvent(this,payRecordId));
        return "请到监听处查看消息";
    }

    @RequestMapping(value = "/sse/get_data", produces = "text/event-stream;charset=UTF-8")
    public String push() {
         try {
             Thread.sleep(1000);
              //第三方数据源调用
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
              return "data:xdclass 行情" + Math.random() + "\n\n";
              }
}
