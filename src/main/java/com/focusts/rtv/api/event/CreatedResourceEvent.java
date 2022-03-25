package com.focusts.rtv.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

public class CreatedResourceEvent extends ApplicationEvent {
    
    private static final long serialVersionUID = 1L;

    @Getter
    private Long code;

    @Getter
    private HttpServletResponse response;

    public CreatedResourceEvent(Object source, HttpServletResponse response, Long code) {
        super(source);
        this.response = response;
        this.code = code;
    }

}
