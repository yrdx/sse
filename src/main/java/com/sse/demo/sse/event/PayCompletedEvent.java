/*
 * Copyright (c) 2001-2019 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.sse.demo.sse.event;

import org.springframework.context.ApplicationEvent;

/**
 *
 * @author zhuls
 * @version V1.0
 * @since 2019-07-10 13:04
 */
public class PayCompletedEvent extends ApplicationEvent {

    private Long payRecordId;
    public PayCompletedEvent(Object source, Long payRecordId) {
        super(source);
        this.payRecordId = payRecordId;
    }

    public Long getPayRecordId() {
        return payRecordId;
    }

    public void setPayRecordId(Long payRecordId) {
        this.payRecordId = payRecordId;
    }
}
