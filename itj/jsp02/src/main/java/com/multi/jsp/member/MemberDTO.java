package com.multi.jsp.member;

public class MemberDTO {

	private String id;
	private String pw;
	private String name;
	private String tel;
	
	//가방에 데이터를 하나씩 넣는 기능 => 함수(메서드)
	public void setId(String id) { //setter
		this.id = id;
	}
	//가방에서 데이터를 하나씩 꺼내는 기능 
	public String getId() { //getter
		return id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "가방에 들어있는 데이터 [id=" + id + ", pw=" + pw + ", name=" + name + ", tel=" + tel + "]";
	}
	
} //class


