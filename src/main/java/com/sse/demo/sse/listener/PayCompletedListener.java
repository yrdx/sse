/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sse.demo.sse.listener;

import com.sse.demo.sse.event.PayCompletedEvent;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**

 *
 * @author zhuls
 * @version V1.0
 * @since 2019-07-10 13:06
 */
@Component
public class PayCompletedListener {

    private static Map<Long, SseEmitter> sseEmitters = new Hashtable<>();

    public void addSseEmitter(Long payRecordId, SseEmitter sseEmitter) {
        sseEmitters.put(payRecordId, sseEmitter);
    }

    @EventListener
    public void deployEventListener(PayCompletedEvent event) throws IOException {
        Long payRecordId = event.getPayRecordId();
        SseEmitter sseEmitter = sseEmitters.get(payRecordId);
        sseEmitter.send("支付成功");
        sseEmitter.complete();

    }


}
