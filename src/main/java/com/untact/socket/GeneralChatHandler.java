package com.untact.socket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.untact.domain.member.MemberEntity;

import lombok.extern.java.Log;

@Controller
@Log
public class GeneralChatHandler {
	@MessageMapping("/send/{groupid}")
	@SendTo("/sub/group/{groupid}")
	public ChatResponse broadcasting(ChatRequest vo) throws Exception{
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format1.format(time);
		return new ChatResponse(vo.getMno(),vo.getFrom(),vo.getMsg(),time1,null);
	}
	@MessageMapping("/hello/{groupid}")
	@SendTo("/sub/group/{groupid}")
	public ChatResponse hello(@DestinationVariable String groupid) throws Exception{
		List<MemberEntity> list = new ArrayList<>(StompHandler.memberMap.get("/sub/group/"+groupid).values());
		return new ChatResponse(0L,"시스템","사용자입장","시간",list);
	}
}
