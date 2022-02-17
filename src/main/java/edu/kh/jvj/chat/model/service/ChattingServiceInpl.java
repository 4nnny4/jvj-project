package edu.kh.jvj.chat.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.jvj.chat.model.dao.ChattingDAO;
import edu.kh.jvj.chat.model.vo.Chat;
import edu.kh.jvj.chat.model.vo.ChatMessage;
import edu.kh.jvj.common.Util;


@Service
public class ChattingServiceInpl implements ChattingService {

	@Autowired
	private ChattingDAO dao;

	// 채팅방 목록 조회(관리자만)
	@Override
	public List<Chat> chatRoomList(Chat chat) {
		return dao.chatRoomList(chat);
	}

	// 채팅 방 열기
	@Override
	public int updateChatRoom(Chat chat) {
		
		int result = dao.openChatRoom(chat);
		
		if(result > 0) { // 성공
			return chat.getChatRoomNo();
			
		}else { // 실패
		return 0;
	}
}

	

	
	// 채팅방 입장, 이전 채팅 내역 얻어오기
	@Override
	public List<Chat> situationChat(ChatMessage message) {
		
		int result = dao.existsChatRoom(message.getChatRoomNo());
				
		if(result > 0 ) {
			try {
			dao.joinChatRoom(message);
			} catch (Exception e) {} 
					
			return dao.selectChatmessage(message.getChatRoomNo());
				
		} else {
			return null;
		}
	}
	
	
	// 채팅 입력
		@Override
		public int insertMessage(ChatMessage cm) {
			
			int result = 0;
			
			if(cm.getMessage() == null) { // 퇴장
				result = dao.insertMessage(cm);
				if(result > 0) {
					result = dao.exitChatRoom(cm);
				}
				
				
			}else { // 채팅 입력
				
				// XSS, 개행문자 처리
				cm.setMessage(Util.XSS(cm.getMessage()));
				cm.setMessage(Util.changeNewLine(cm.getMessage()));
				
				result = dao.insertMessage(cm);
			
			}
			
			return result;
		}
	
}
