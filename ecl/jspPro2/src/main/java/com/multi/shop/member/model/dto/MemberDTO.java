package com.multi.shop.member.model.dto;

import java.sql.Date;

public class MemberDTO {
	private int no;
	private String id;
	private String pw;
	private String name;
	private String tel;
	private Date create_date;
	
	public MemberDTO() {}
	
	public MemberDTO(int no, String id, String pw, String name, String tel, Date create_date) {
		super();
		this.no = no;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.tel = tel;
		this.create_date = create_date;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	@Override
	public String toString() {
		return "MemberDTO [no=" + no + ", id=" + id + ", pw=" + pw + ", name=" + name + ", tel=" + tel
				+ ", create_date=" + create_date + "]";
	}
	
	
	

}
