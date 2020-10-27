package com.untact.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GeneralChatHandler {
	@MessageMapping("/send/{groupid}")
	@SendTo("/sub/group/{groupid}")
	public ChatResponse broadcasting(ChatRequest vo) throws Exception{
		return new ChatResponse(vo.getMno(),vo.getFrom(),vo.getMsg());
	}
}
