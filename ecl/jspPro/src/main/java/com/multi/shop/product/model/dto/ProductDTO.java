package com.multi.shop.product.model.dto;

import java.sql.Date;
import java.util.List;

public class ProductDTO {
		private int id;
	    private String name;
	    private String content;
	    private int price;
	    private CompanyDTO company;
	    private String img;
	    private String status;
	    private Date createDate;
	    private String createPerson;
	    private Date modifyDate;
	    private String modifyPerson;
	    private List<AttachmentDTO> attachments;
	    
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public CompanyDTO getCompany() {
			return company;
		}
		public void setCompany(CompanyDTO company) {
			this.company = company;
		}
		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public String getCreatePerson() {
			return createPerson;
		}
		public void setCreatePerson(String createPerson) {
			this.createPerson = createPerson;
		}
		public Date getModifyDate() {
			return modifyDate;
		}
		public void setModifyDate(Date modifyDate) {
			this.modifyDate = modifyDate;
		}
		public String getModifyPerson() {
			return modifyPerson;
		}
		public void setModifyPerson(String modifyPerson) {
			this.modifyPerson = modifyPerson;
		}
		public List<AttachmentDTO> getAttachments() {
			return attachments;
		}
		public void setAttachments(List<AttachmentDTO> attachments) {
			this.attachments = attachments;
		}
		@Override
		public String toString() {
			return "ProductDTO [id=" + id + ", name=" + name + ", content=" + content + ", price=" + price
					+ ", company=" + company + ", img=" + img + ", status=" + status + ", createDate=" + createDate
					+ ", createPerson=" + createPerson + ", modifyDate=" + modifyDate + ", modifyPerson=" + modifyPerson
					+ ", attachments=" + attachments + "]";
		}
	    
	    
	    
	    
	    
}
