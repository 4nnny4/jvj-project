package edu.kh.jvj.mypage.model.vo;

public class Pagination {


	private int currentPage; // 현재 페이지 번호
	private int listCount; // 전체 상품수
	
	private int limit = 6; // 한페이지에 보여지는 상품수
	private int pageSize = 10; // 보여질 페이지 번호 갯수
	
	private int maxPage; // 마지막 페이지
	private int startPage; // 보여지는 맨앞페이지 번호
	private int endPage; // 보여지는 맨뒤 페이지 번호
	
	private int prevPage; // 이전페이지의 페이지번호 맨 끝
	private int nextPage; // 다음페이지의 페이지 번호 맨 앞
	
	public Pagination(int listCount, int currentPage) {
		this.listCount = listCount;
		this.currentPage = currentPage;
		
		// 객체 생성시 페이징 다시
		makePagination();
	
	}

	public Pagination(int currentPage, int listCount, int limit, int pageSize) {
	
		this.currentPage = currentPage;
		this.listCount = listCount;
		this.limit = limit;
		this.pageSize = pageSize;
		makePagination();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		makePagination();
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
		makePagination();
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
		makePagination();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		makePagination();
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	@Override
	public String toString() {
		return "Pagination [currentPage=" + currentPage + ", listCount=" + listCount + ", limit=" + limit
				+ ", pageSize=" + pageSize + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage + "]";
	}
	
	// 페이징 처리에 필요한 값을 계산
	private void makePagination() {
		// * maxPage = 최대 페이지 == 마지막 페이지 == 총페이지수
		
		// 한페이지에 8개씩 상품이 있을경우
		// 상품수가 78인 경우 == 10페이지
		// 상품수가 80인경우 == 10페이지
		
		// 전체 상품수 / 한 페이지에 보여지는 상품수 -> 올림처리
		// 78/8 = 7.8 = 9.75 -> 10
		
		maxPage = (int)Math.ceil((double)listCount/limit);
		
		// 페이지네이션 목록에서 제일 앞에 보여지는 번호
		// 현재 페이지 1-5 인경우 : 1페이지
		startPage = (currentPage-1) / pageSize*pageSize+1;
		
		// 끝번호
		// 1-5 인경우 5 페이지
		endPage = startPage + pageSize-1;
		
		// ** 계산된 endPage가 전체 페이지네이션 목록수를 초과하는 경우
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		
		// prevPage : 이전 페이지네이션 목록 끝번호
		// nextPage : 다음 페이지네이션 목록 시작번호
		
		if(currentPage<=pageSize) prevPage = 1;
		else
			prevPage = startPage -1;
		if(endPage == maxPage) nextPage = maxPage;
		else nextPage = endPage +1;
	}
	
}
