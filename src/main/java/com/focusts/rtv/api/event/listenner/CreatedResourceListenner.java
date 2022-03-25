package com.focusts.rtv.api.event.listenner;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import com.focusts.rtv.api.event.CreatedResourceEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class CreatedResourceListenner implements ApplicationListener<CreatedResourceEvent> {
    
    @Override
    public void onApplicationEvent(CreatedResourceEvent createdResourceEvent) {
        HttpServletResponse response = createdResourceEvent.getResponse();
        Long codigo = createdResourceEvent.getCode();
        adicionarHeaderLocation(response, codigo);
    }

    private void adicionarHeaderLocation(HttpServletResponse response, Long code) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}").buildAndExpand(code).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
