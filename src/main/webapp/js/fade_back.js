// JavaScript Document
$(document).ready(function() { // 首先将#back-to-top隐藏
	$(".backToTop").hide(); // 当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
	$(function() {
		$(window).scroll(function() {
			if ($(window).scrollTop() > 150) {
				$(".backToTop").fadeIn(500);
			} else {
				$(".backToTop").fadeOut(500);
			}
		});

		$(".backToTop").click(function() { // 当点击跳转链接后，回到页面顶部位置
			$('body,html').animate({
				scrollTop : 0
			}, 300);
			return false;
		});
	});

	$(".findmap").hide().css("margin-top", "300px");
	$(function() {
		$(window).scroll(function() {
			if ($(window).scrollTop() > 140) {
				$(".findmap").fadeIn(500).animate({
					marginTop : '0px'
				}, 300);
			} else {
				$(".findmap").fadeOut(500);
			}
		});
	});
});