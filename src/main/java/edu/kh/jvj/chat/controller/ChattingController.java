package edu.kh.jvj.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.jvj.chat.model.service.ChattingService;
import edu.kh.jvj.chat.model.vo.Chat;
import edu.kh.jvj.chat.model.vo.ChatMessage;
import edu.kh.jvj.common.Util;
import edu.kh.jvj.member.model.vo.Member;


@Controller
@SessionAttributes({"loginMember","chatRoomNo"})
public class ChattingController {
	
	
	@Autowired
	public ChattingService service;
	
	
	
	// 채팅방 목록 조회(관리자)
		@RequestMapping("/chatting/list")
		public String chatRoomList(Chat chat, Model model , @ModelAttribute("loginMember") Member loginMember, ChatMessage cm) {
			
			chat.setMemberNo(loginMember.getMemberNo());
			chat.setMemberName(loginMember.getMemberName());
			chat.setGradeCode(loginMember.getGradeCode());
			
			
			List<Chat> chatVowel = service.chatRoomList(chat);
			
			model.addAttribute("chatVowel", chatVowel);
			
			
			return "/chatting/chatList";
		}
		
		// 채팅방 만들기(회원)
		@RequestMapping(value = "/chatting/chattingroom", method = RequestMethod.POST)
		private String openChatRoom(Chat chat , @ModelAttribute("loginMember") Member loginMember,
				RedirectAttributes ra) {
			
			chat.setMemberNo(loginMember.getMemberNo() );
			
			int chatRoomNo = service.updateChatRoom(chat);
			
			String path = "redirect:/chatting/";
			
			if(chatRoomNo>0) {
				
				path+="chat/"+chatRoomNo; 
				
			}else {
				Util.swalSetMessage("문의 서비스 오류가 발생하였습니다.", path, "error", ra);
				return "/chatting/chatList";
			}
			return path;
		}
		
		
		// 채팅방 입장, 이전 채팅 내역 얻어오기(관리자)
		@RequestMapping(value = "/chatting/chat/{chatRoomNo}", method = RequestMethod.GET)
		public String joinChatRoom(@PathVariable("chatRoomNo") int chatRoomNo,
								   @ModelAttribute("loginMember") Member loginMember,
								   ChatMessage message, RedirectAttributes ra, Model model) {
			message.setChatRoomNo(chatRoomNo);
			message.setMemberNo(loginMember.getMemberNo());
			
			List<Chat> inpuChatting = service.situationChat(message);

			if(inpuChatting != null) {
				
				model.addAttribute("inpuChatting", inpuChatting);
				model.addAttribute("chatRoomNo", chatRoomNo);
				
				return "chatting/chatting";
				
				
			}else { 
				
				Util.swalSetMessage("해당 채팅방이 존재하지 않습니다", null , "info", ra);
				return "redirect:../";
				
			}
			
		}
		
		@ExceptionHandler(HttpSessionRequiredException.class)
		public String exceptionHandler(Exception e) {
			return "redirect:/chat/roomList";
		}

}
