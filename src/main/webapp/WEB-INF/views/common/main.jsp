<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jean val jean</title>
<jsp:include page="../common/header.jsp" />
<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css" />
	   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/animejs/2.2.0/anime.min.js"></script>

 
</head>
<body>
	<script>
        AOS.init();
    </script>
	<!-- Swiper -->
	<div class="swiper mySwiper">
		<div class="swiper-wrapper">
			<div class="swiper-slide">
				<div class="main-banner"></div>
			</div>
			<div class="swiper-slide">
				<div class="main-banner2"></div>
			</div>
			<div class="swiper-slide">
				<div class="main-banner3"></div>
			</div>
			<div class="swiper-slide">
				<div class="main-banner4"></div>
			</div>
			
		</div>
		<div class="swiper-pagination" style="bottom:100px"></div>
		      <div class="swiper-button-next"></div>
      <div class="swiper-button-prev"></div>
      
	</div>
	
	<c:choose>
	<c:when test="${loginMember.gradeCode eq 100}">
		<form method="POST" action="${contextPath}/chatting/chattingroom" target="popup_window" id="popUpForm">
		<button id="chatting_btn1" class ="chatting_btn" type="submit">
	        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat" viewBox="0 0 16 16">
	        <path d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"/>
	        </svg>
		</button>
		</form>

	</c:when>	
	<c:when test="${loginMember.gradeCode eq 101}">
		<button id="chatting_btn2" type="submit" class ="chatting_btn">
	        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat" viewBox="0 0 16 16">
	        <path d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"/>
	        </svg>
		</button>
	</c:when>
	</c:choose>
	
	<div class="main-wrapper">
<svg id="morph" height="100vh" widht="100vw" viewbox="0 0 1921 1083" preserveAspectRatio="none">
    <path class="morph" d="M0,0V-.384s151.27.01,385.2.01c362.84.355,235.588,1.411,478.055.083s285.457-.149,598.429.15,459.139.317,459.139.317V0H0Z" fill="#ffbd51"></path>
</svg>
		<div class="story">
			<div data-aos="fade-up" data-aos-duration="1000">
				<div class="store">

					<div class="store-pdt pdt1-img"></div>
					<div class="store-ex" style="margin-left: 126px">
						<h1>식 빵</h1>
						<p>식빵은 빵의 한 종류로, 밀가루, 물, 효모를 반죽해 틀에 넣어 구운 덩어리 빵,  일본에서는 미술 디자인을 할 때 그림 지우는
							용도로 사용하는 빵을 케시판(消しパン: 지움빵)이라 부르는데, 이에 대응하여 실제로 먹는 빵을 쇼쿠판(食パン:
							먹음빵)이라 부르던 데서 나왔다는 설이 대표적이다.</p>
						<h1>35,000원</h1>
						<div class="moreBtn" >+ 더보기</div>
					</div>
				</div>
			</div>
			<div data-aos="fade-up" data-aos-duration="800">
				<div class="store">
					<div class="store-ex" style="margin-right: 126px">
						<h1>바게트 빵</h1>
						<p>프랑스에서 주식용 빵은 동그란 형상의 빵인 캉파뉴가 주류였지만, 19세기 들어 바게트가 일상화되면서
							그 자리를 꿰차게 되었다. 캉파뉴는 발효 과정과 만드는 시간, 과정이 바게트보다 까다롭고 오래 걸린다. 반면 바게트는 캉파뉴보다 만들기 쉬웠고, 먹기도 편하고 운반하는 데도 더
							유리했다.</p>
						<h1>35,000원</h1>
						<div class="moreBtn">+ 더보기</div>
					</div>
					<div class="store-pdt pdt2-img"></div>

				</div>
			</div>
			<div data-aos="fade-up" data-aos-duration="800">
				<div class="store">
					<div class="store-pdt pdt3-img"></div>
					<div class="store-ex" style="margin-left: 126px">
						<h1>마카롱</h1>
						<p>마카롱을 누가 어디에서 처음 만들었는지 공식적인 기록은 1533년 이탈리아 메디치 가문의 카트린 드
							메디시스가 프랑스의 앙리 2세와 결혼하게 되면서부터다. 그녀와 함께 프랑스로
							갔던 이탈리아의 요리사들을 통해 마카롱뿐만 아니라 이탈리아의 식문화가 프랑스에 전파되었다고 한다.</p>
						<h1>35,000원</h1>
						<div class="moreBtn">+ 더보기</div>
					</div>

				</div>
			</div>
		</div>
		<div class="m-notice" data-aos="fade-up" data-aos-duration="400">
		<a href="notice/view?noticeNo=481&cate=0&cp=1"><img alt="" src="${contextPath}/resources/images/main/notice.png" style="width:1200px"></a>
		</div>
	</div>

	<div class="subscribe">
		<div data-aos="fade-up" data-aos-duration="800">
			<div>

				<h1 style="margin-top: 35px; display: inline-block;">정기구독</h1>
			</div>

			<div class="subs-wrap">
				<div class="subs-item subs-img1"></div>
				<h2>식빵 세트</h2>
			</div>
			<div class="subs-wrap">

				<div class="subs-item subs-img2"></div>
				<h2>식빵 & 커피 세트</h2>

			</div>

		</div>
	</div>
	<div data-aos="fade-up" data-aos-duration="800">
		<div class="main-wrapper">
			<div class="one-day-class">
				<div class="fake-hr"></div>
				<h1 style="display: inline-block; color: #828282;">원데이 클래스</h1>
				<div class="fake-hr"></div>
			</div>
			<div class="class-wrapper">
				<div class="class-wrap bigclass class-img0" onclick="location.href='onedayclass/list'"></div>
				<div class="class-wrap">
					<a href="onedayclass/view/1521"><div class="smallclass class-img2"></div></a>
					<div class="class-article">
						<p style="font-size: 26px;">마카롱 클래스</p>
						<p style="font-size: 20px; margin-top: 20px">2/16</p>
						<p style="font-size: 20px; margin-top: 20px">50,000원</p>
						<a href="onedayclass/view/1521"><div class="black-btn" style="margin-top: 20px">마포점</div></a>
					</div>

					<div class="fake-hr2"></div>

					<a href="onedayclass/view/1591"><div class="smallclass class-img1"></div></a>
					<div class="class-article">
						<p style="font-size: 26px;">슈크림 클래스</p>
						<p style="font-size: 20px; margin-top: 20px">2/16</p>
						<p style="font-size: 20px; margin-top: 20px">50,000원</p>
						<a href="onedayclass/view/1591"><div class="black-btn" style="margin-top: 20px">마포점</div></a>
					</div>
				</div>

			</div>
		</div>
	</div>
	<div data-aos="fade-up" data-aos-duration="800">
		<div class="event">
			<h1 style="font-style: italic;">이벤트 소식</h1>
			
			<img src="${contextPath}/resources/images/main/mainevent1.png">
			<img src="${contextPath}/resources/images/main/mainevent2.png">
			<img src="${contextPath}/resources/images/main/mainevent3.png">
			<br>
			<div class="e-textbox">
			
			<h3>첫 구매 시 웰컴 패키지 90% 할인</h3> 식빵부터 버터나이프까지 알찬 구성
			</div>
			<div class="e-textbox">
			<h3>장발장 식빵 3만원 이상 구매시</h3>무료배송 이벤트 
			</div>
			<div class="e-textbox">
			<h3>
			신규회원 쇼핑 지원금 감사쿠폰</h3>
			회원가입시 3,000원 상당의 감사쿠폰 지급
			</div>
		</div>
	</div>
	
	
	
	
	<script>
    var swiper = new Swiper(".mySwiper", {
        spaceBetween: 30,
        centeredSlides: true,
        autoplay: {
          delay: 5500,
          disableOnInteraction: false,
        },
        pagination: {
          el: ".swiper-pagination",
          clickable: true,
        },
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
      });

    


    const morphing = anime({
        targets: '.morph',
        d: [
            {value: 'M0,0V520.616s151.27-180.99,385.2-180.99c362.84,22.355,235.588,316.411,478.055,349.083s285.457-232.149,598.429-319.85,459.139,194.317,459.139,194.317V0Z'},
            {value: 'M0,0V1080.616s151.27.01,385.2.01c362.84.355,235.588-.589,478.055.083s285.457.851,598.429.15,459.139.317,459.139.317V0Z'}
        ],
        easing: 'easeInOutQuint',
        duration: 1300,
        loop: false,
        autoplay: false
    });
    const btn = document.getElementsByClassName('moreBtn')[0];
    btn.addEventListener('click', function() {

        	location.href='store?cp=1&ct=1&op=0';

    });
    const btn2 = document.getElementsByClassName('moreBtn')[1];
    btn2.addEventListener('click', function() {
      
        	location.href='store?cp=1&ct=2&op=0';

    });
    const btn3 = document.getElementsByClassName('moreBtn')[2];
    btn3.addEventListener('click', function() {
      
        	location.href='store/info/1941';

    });
    $('.subs-img1').on('click',()=>{
    	 morphing.restart();
         morphing.finished.then(() => {
    	location.href='subscribe/subBread';
         	
           });
    });
    $('.subs-img2').on('click',()=>{
    	 morphing.restart();
         morphing.finished.then(() => {
    	location.href='subscribe/subCoffee';
           });
    });
    
    $('.swiper-pagination-bullet').css('background','#564334');

    $(document).ready(function(){
    	  $("#chatting_btn1").on("click", function(){
    	    window.open("", "popup_window", "width=405, height=505, scrollbars=no, resizeble=no, status=no");
    	    $("#popUpForm").submit();
    	  });
   	});
    
    $(document).ready(function(){
    	  $("#chatting_btn2").on("click", function(){
    	    window.open("${contextPath}/chatting/list", "", "width=405, height=520, scrollbars=no, resizeble=no, status=no");
    	  });
   	});
    	

    </script>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>