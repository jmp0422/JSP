<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
$(function() {
	$('#b1').click(function() {
		alert("내가 호출되었어.!")
		$('#d1').empty() //d1에 있는 것 한번 지움.
		$.ajax({
			url:  "https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.hankyung.com%2Ffeed%2Fit&api_key=9usvf6bakh7j1jxfstaq10k3daczstjhtbg2byzy",
					
			success: function(doc) { 
				list = doc.items //[{},{},{}]
			    alert(list.length)
			    for(let i = 0; i<list.length; i++){
			        title = list[i].title
			        pubDate = list[i].pubDate
			        link = list[i].link
			        author = list[i].author
			        console.log(title+ ", " + pubDate + ", " + link  + ", " + author)
			        $('#d1').append("<a href=" + link + ">" + title + "</a><br>")
			    }
			},
			error: function(e) {
				alert('프락시 서버 실패.@@@@@@@@')
			} //error
		}) //ajax
	}) //b1
	$('#b2').click(
		function() {
			$('#d1').empty() //d1에 있는 것 한번 지움.
			//alert("내가 호출되었어.!")
			$.ajax({
						url : " https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.hankyung.com%2Ffeed%2Fentertainment&api_key=9usvf6bakh7j1jxfstaq10k3daczstjhtbg2byzy",
						success : function(doc) { //[json, json, json]
							alert('성공');
							let list = doc.items;
							  alert(list.length)
                              			    for(let i = 0; i<list.length; i++){
                              			        title = list[i].title
                              			        pubDate = list[i].pubDate
                              			        link = list[i].link
                              			        author = list[i].author
                              			        console.log(title+ ", " + pubDate + ", " + link  + ", " + author)
                              			        $('#d1').append("<a href=" + link + ">" + title + "</a><br>")
                              			    }
						},
						error : function(e) {
							alert(e)
						} //error
					}) //ajax
			}) //b2
			
			
	$('#b3').click(
		function() {
			$('#d1').empty() //d1에 있는 것 한번 지움.
			//alert("내가 호출되었어.!")
			$.ajax({
					url : "https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.hankyung.com%2Ffeed%2Fsports&api_key=9usvf6bakh7j1jxfstaq10k3daczstjhtbg2byzy",
					success : function(doc) { //[json, json, json]
						alert('성공');
						let list = doc.items;
					  alert(list.length)
                      			    for(let i = 0; i<list.length; i++){
                      			        title = list[i].title
                      			        pubDate = list[i].pubDate
                      			        link = list[i].link
                      			        author = list[i].author
                      			        console.log(title+ ", " + pubDate + ", " + link  + ", " + author)
                      			        $('#d1').append("<a href=" + link + ">" + title + "</a><br>")
                      			    }
					},
					error : function(e) {
						alert(e)
					} //error
				}) //ajax
			}) //b3


		$('#b4').click(function() {
			$('div').empty()
			$.ajax({
				url : "",
				data : {
					url :"https://news.sbs.co.kr/news/SectionRssFeed.do?sectionId=02&plink=RSSREADER",
					api_key : "",
					count : 20
				},						
				success : function(x) { 
					//alert('응답받은 내용: ' + x)
					//items키를 주고, 값인 []리스트를 가지고 오자.
					//list = x.items
				   alert('성공');
                   						let list = x.items;
                   					  alert(list.length)
                                         			    for(let i = 0; i<list.length; i++){
                                         			        title = list[i].title
                                         			        pubDate = list[i].pubDate
                                         			        link = list[i].link
                                         			        author = list[i].author
                                         			        console.log(title+ ", " + pubDate + ", " + link  + ", " + author)
                                         			        $('#d1').append("<a href=" + link + ">" + title + "</a><br>")
                                         			        $('#d1').append("<img src=" + img + " width=200 height=200>")
                                         			    }
				} //success
			}) //ajax
		}) //b4
}) //$

</script>
<style>
input {
	background: yellow;
	color: red;
}
</style>
</head>
<body>
	<button id="b1" class="mt-2 p-2 bg-primary text-white rounded">한경
		기사 XML(it)받아오자.</button>
	<button id="b2" class="mt-2 p-2 bg-primary text-white rounded">한경
		기사 XML(연예)받아오자.</button>
	<button id="b3" class="mt-2 p-2 bg-primary text-white rounded">한경
		기사 XML(스포츠)받아오자.</button>
	<button id="b4" class="mt-2 p-2 bg-primary text-white rounded">SBS</button>



	<hr>
	<div id="d1" style="width: 800px; height: 1000px;">
	</div>
</body>
</html>





