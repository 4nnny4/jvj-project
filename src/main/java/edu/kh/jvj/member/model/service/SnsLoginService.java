package edu.kh.jvj.member.model.service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import edu.kh.jvj.member.model.vo.Member;
import edu.kh.jvj.member.model.vo.SnsToken;
import edu.kh.jvj.member.model.vo.SnsValue;

@Service
public class SnsLoginService {

	private OAuth20Service oauthService;
	private SnsValue sns;

	public SnsLoginService(SnsValue sns) {

		if(sns.isNaver()) {
			this.oauthService = new ServiceBuilder(sns.getClientId())
					.apiSecret(sns.getClientSecret())
					.callback(sns.getRedirectUrl())
					.scope("profile")
					.build(sns.getApi20Instance());

		}  else if(sns.isKakao()) {
			this.oauthService = new ServiceBuilder(sns.getClientId())
					.apiSecret(sns.getClientSecret())
					.callback(sns.getRedirectUrl())
					.build(sns.getApi20Instance());
		}

		this.sns = sns;
	}
	
	public SnsLoginService() {
		// TODO Auto-generated constructor stub
	}

	public String getSnsAuthURL() {
		return this.oauthService.getAuthorizationUrl();
	}

	public Member getNaverUserProfile(String code, String snsService) throws Exception{
		OAuth2AccessToken accessToken = oauthService.getAccessToken(code);

		OAuthRequest request = new OAuthRequest(Verb.GET, this.sns.getProfileUrl());
		oauthService.signRequest(accessToken, request);
		Response resp = oauthService.execute(request);

		Member member = new Member();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(resp.getBody());

		JsonNode resNode = rootNode.get("response");

		member.setMemberId(resNode.get("id").asText());
		member.setMemberNickname(resNode.get("nickname").asText());
		member.setMemberEmail(resNode.get("email").asText());
		member.setMemberName(resNode.get("name").asText());
		member.setService(snsService);

		return member;
	}


	public SnsToken getKakaoToken(String code) throws Exception{
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		URL url = new URL(reqURL);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		//  URL????????? ???????????? ?????? ??? ??? ??????, POST ?????? PUT ????????? ????????? setDoOutput??? true??? ???????????????.
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);

		//	POST ????????? ????????? ???????????? ???????????? ???????????? ?????? ??????
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		StringBuilder sb = new StringBuilder();
		sb.append("grant_type=authorization_code");
		sb.append("&client_id=dce5678c6c2aceeae891719b598cbe3b");  //????????? ???????????? key
		
		// ????????? ??????????????? ??????
		sb.append("&redirect_uri=http://localhost:8080/jvj/member/kakao/callback");     // ????????? ????????? ?????? ??????
		sb.append("&code=" + code);
		bw.write(sb.toString());
		bw.flush();

		//    ?????? ????????? 200????????? ??????
		int responseCode = conn.getResponseCode();



		//    ????????? ?????? ?????? JSON????????? Response ????????? ????????????
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = "";
		String result = "";


		while ((line = br.readLine()) != null) {
			result += line;
		}

		br.close();
		bw.close();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(result);

		SnsToken snsToken = new SnsToken();
		snsToken.setAccess_Token(rootNode.get("access_token").asText());
		snsToken.setRefresh_Token(rootNode.get("refresh_token").asText());

		return snsToken;


	}

	public Member getKakaoProfile(SnsToken snsToken, String snsService) throws Exception{

		String reqURL = "https://kapi.kakao.com/v2/user/me";
		String resultInfo = "";
		
		URL url = new URL(reqURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		//    ????????? ????????? Header??? ????????? ??????
		conn.setRequestProperty("Authorization", "Bearer " + snsToken.getAccess_Token());

		int responseCode = conn.getResponseCode();

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = "";

		while ((line = br.readLine()) != null) {
			resultInfo += line;
		}

		br.close();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode resultNode = mapper.readTree(resultInfo);

		JsonNode kakao_account = resultNode.get("kakao_account");
		JsonNode profile = kakao_account.get("profile");
		
		System.out.println(resultNode);
		
		Member member = new Member();

		member.setMemberId(resultNode.get("id").asText());
		member.setMemberNickname(profile.get("nickname").asText());
		member.setMemberEmail(kakao_account.get("email").asText());
		member.setService(snsService);

		return member;
	}
}
