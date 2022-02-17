package edu.kh.jvj.chat.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.jvj.chat.model.vo.Chat;
import edu.kh.jvj.chat.model.vo.ChatMessage;

@Repository
public class ChattingDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	
	/** 채팅방 목록 조회(관리자만)
	 * @param chat
	 * @return
	 */
	public List<Chat> chatRoomList(Chat chat) {
		return sqlSession.selectList("chatMapper.chatRoomList", chat);
	}

	/** 채팅방열기
	 * @param chat
	 * @return
	 */
	public int openChatRoom(Chat chat) {
		return sqlSession.insert("chatMapper.openChatRoom", chat);
		}
		

	/** 일치한 방 번호 조회(채팅방 존재 여부 확인)
	 * @param cmNo
	 * @return
	 */
	public int existsChatRoom(int cmNo) {
		return sqlSession.selectOne("chatMapper.existsChatRoom", cmNo);
	}

	/** 채팅방 입장
	 * @param message
	 */
	public void joinChatRoom(ChatMessage message) {
		sqlSession.insert("chatMapper.joinChatRoom", message);
	}

	/** 해당 방 번호와 일치하는 모든 메세지 조회
	 * @param cmNo
	 * @return
	 */
	public List<Chat> selectChatmessage(int cmNo) {
		return sqlSession.selectList("chatMapper.selectChatMessage", cmNo);
	}

	/** 채팅 내용 삽입
	 * @param cm
	 * @return
	 */
	public int insertMessage(ChatMessage cm) {
		return sqlSession.insert("chatMapper.insertMessage", cm);
	}
	
	/** 채팅 퇴장
	 * @param cm
	 * @return
	 */
	public int exitChatRoom(ChatMessage cm) {
		return sqlSession.delete("chatMapper.exitChatRoom",cm);
	}
	
}
