

import java.sql.Date;

import com.multi.shop.board.model.dto.CategoryDTO;


public class MemberDTO {
	private int no;
	private String id;
	private String pw;
	private String name;
	private String tel;
	private Date createDate;

	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}

	
	




	public MemberDTO(int no, String id, String pw, String name, String tel) {
		super();
		this.no = no;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.tel = tel;
	}







	public MemberDTO(int no, String id, String pw, String name, String tel, Date createDate) {
		super();
		this.no = no;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.tel = tel;
		this.createDate = createDate;
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







	public Date getCreateDate() {
		return createDate;
	}







	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}







	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", tel=" + tel + ", createDate=" + createDate
				+ "]";
	}








	
	
	
}