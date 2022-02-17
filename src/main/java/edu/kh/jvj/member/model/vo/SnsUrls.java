package edu.kh.jvj.member.model.vo;

public interface SnsUrls {
	static final String NAVER_ACCESS_TOKEN =
			"https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
	static final String NAVER_AUTH = "https://nid.naver.com/oauth2.0/authorize";
	static final String NAVER_PROFILE_URL = "https://openapi.naver.com/v1/nid/me";
	
	
	
	static final String KAKAO_AUTH = "https://kauth.kakao.com/oauth/authorize";
	static final String KAKAO_ACCESS_TOKEN = "https://kauth.kakao.com/oauth/token";
	static final String KAKAO_PROFILE_URL = "https://kapi.kakao.com/v2/user/me";
}

