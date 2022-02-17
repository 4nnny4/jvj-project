package edu.kh.jvj.chat.websocket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.kh.jvj.chat.model.service.ChattingService;
import edu.kh.jvj.chat.model.vo.ChatMessage;


public class WebsocketHandler extends TextWebSocketHandler {

    
    
    @Autowired
    private ChattingService service;
    
    
    private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println(session.getId() + " 연결 완료 ");
        
        sessions.add(session);
        
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        
        System.out.println("전달 받은 내용 : " + message.getPayload());
        
        ObjectMapper objectmapper = new ObjectMapper();
        ChatMessage chat = objectmapper.readValue(message.getPayload(), ChatMessage.class);
        System.out.println("변경된 chatting : " + chat);
        
        // 채팅 내용을 DB에 저장하는 Service 호출
        int result = service.insertMessage(chat);
        
        if(result>0) {
        for( WebSocketSession wss : sessions ) {
            
            int chatRoomNo = (Integer)wss.getAttributes().get("chatRoomNo");
            
            if(chatRoomNo == chat.getChatRoomNo()) {
                wss.sendMessage(new TextMessage(message.getPayload()));
            }
            
        }
            
        }
        
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        
        sessions.remove(session);
        
        super.afterConnectionClosed(session, status);
    }
    
    
    
    
    
    
    
    
    

}