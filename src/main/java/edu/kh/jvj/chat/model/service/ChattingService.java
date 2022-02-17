package edu.kh.jvj.chat.model.service;

import java.util.List;

import edu.kh.jvj.chat.model.vo.Chat;
import edu.kh.jvj.chat.model.vo.ChatMessage;

public interface ChattingService {

	// 채팅방 목록 조회
	List<Chat> chatRoomList(Chat chat);

	// 채팅방 열기(만들기)
	int updateChatRoom(Chat chat);

	int insertMessage(ChatMessage chat);

	// 채팅방 입장, 이전 채팅 내역 얻어오기
	List<Chat> situationChat(ChatMessage message);

}
