package edu.kh.jvj.notice.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.jvj.admin.model.vo.MadeCoupon;
import edu.kh.jvj.notice.model.dao.NoticeDAO;
import edu.kh.jvj.notice.model.vo.Notice;
import edu.kh.jvj.store.model.vo.Pagination;

@Service
public class NoticeServiceImpl implements NoticeService{

	
	@Autowired
	private NoticeDAO dao;
	
	//공지 개수 조회 페이지네이션 반환
	@Override
	public Pagination countNotice(Map<String , String> map ) {
		int count =  dao.countNotice(map);
		return new Pagination(count,Integer.parseInt(map.get("cp")));
	}
	
	//상세공지
	@Override
	public Notice selectOneNotice(int noticeNo) {
		return dao.selectOneNotice(noticeNo);
	}

	//공지 목록
	@Override
	public List<Notice> selectNoticeList(Pagination page, Map<String, String> dataMap) {
		return dao.selectSearchNoticeList(page,dataMap);
	}

	//쿠폰목록
	@Override
	public List<MadeCoupon> selectCoupons(int noticeNo) {
		return dao.selectCoupons(noticeNo);
	}
	
	
	@Override
	public MadeCoupon getMadeCoupon(int madeCouponNo) {
		return dao.getMadeCoupon(madeCouponNo);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertCouponToMember(MadeCoupon madeCoupon) {
		//회원이 사용가능한 해당 쿠폰 받은적 있는지 검사 필요
		int result= 0;
		//회원의 쿠폰 발급 이력
		int count = dao.countGetCoupon(madeCoupon);
		
		//발급 가능상태면
		if(madeCoupon.getCouponLimit()>0 && count<3) {
			//발급
			result=dao.insertCouponToMember(madeCoupon);
			//발급이력 남기고 재고 차감
			dao.insertCouponHistory(madeCoupon);
			result = dao.deductionCoupon(madeCoupon.getCouponNo());
			
			//차감 후 재고 0이면 스테이터스 변경
			if(result>0) {
				result= madeCoupon.getCouponLimit()-1;
				if(result==0) {
					dao.ChangeCouponStatus(madeCoupon.getCouponNo());
				}
			}
			
		}
		else if(madeCoupon.getCouponLimit()>0) {
			result = -1;
			//발급가능 수량 초과
		}
		else if(madeCoupon.getCouponLimit()<=0) result = -2; //쿠폰소진
		else result = -3; //에러
		return result;
	}

	
	
}
