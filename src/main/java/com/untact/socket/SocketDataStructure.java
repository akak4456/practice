package com.untact.socket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.untact.domain.member.MemberEntity;

public class SocketDataStructure {
	public static Map<String,Map<String,MemberEntity>> memberMap = new ConcurrentHashMap<>();
	//destination, member
	public static Map<String,String> sessionIdToDestination = new ConcurrentHashMap<>();
}
