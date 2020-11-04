package com.untact.socket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.untact.domain.member.MemberEntity;
import com.untact.security.JwtTokenProvider;
@Component
public class StompHandler implements ChannelInterceptor {
	public static Map<String,Map<String,MemberEntity>> memberMap = new ConcurrentHashMap<>();
	//destination, member
	private static Map<String,String> sessionIdToDestination = new ConcurrentHashMap<>();
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
    	StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
    	if(StompCommand.SUBSCRIBE == accessor.getCommand()) {
    		Authentication authentication = jwtTokenProvider.getAuthentication(accessor.getFirstNativeHeader("X-AUTH-TOKEN"));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            accessor.setUser(authentication);
            MemberEntity member = ((MemberEntity)authentication.getPrincipal()).copy();
            String sessionId = accessor.getSessionId();
            String destination = accessor.getDestination();
            memberMap.computeIfAbsent(destination, memSet->new ConcurrentHashMap<>()).put(sessionId,member);
            sessionIdToDestination.put(sessionId, destination);
    	}else if(StompCommand.UNSUBSCRIBE == accessor.getCommand()||StompCommand.DISCONNECT == accessor.getCommand()) {
    		//memberMap.get(accessor.getDestination()).remove(accessor.getSessionId());
    		String sessionId = accessor.getSessionId();
    		String destination = sessionIdToDestination.get(sessionId);
    		memberMap.get(destination).remove(sessionId);
    		//시스템 메시지 전송
    		List<MemberEntity> list = new ArrayList<>(StompHandler.memberMap.get(destination).values());
    		messagingTemplate.convertAndSend(destination, new ChatResponse(0L,"시스템","사용자퇴장","시간",list));
    	}
    	return message;
    }
}
