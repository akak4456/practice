package com.untact.socket;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GeneralChatHandler {
	@MessageMapping("/send/{groupid}")
	@SendTo("/sub/group/{groupid}")
	public ChatResponse broadcasting(ChatRequest vo) throws Exception{
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format1.format(time);
		return new ChatResponse(vo.getMno(),vo.getFrom(),vo.getMsg(),time1);
	}
}
