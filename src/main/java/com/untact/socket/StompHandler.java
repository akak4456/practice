package com.untact.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.untact.security.JwtTokenProvider;
@Component
public class StompHandler implements ChannelInterceptor {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
    	StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
    	if(StompCommand.CONNECT == accessor.getCommand()) {
    		Authentication authentication = jwtTokenProvider.getAuthentication(accessor.getFirstNativeHeader("X-AUTH-TOKEN"));
            SecurityContextHolder.getContext().setAuthentication(authentication);
    	}
    	
    	return message;
    }
}
