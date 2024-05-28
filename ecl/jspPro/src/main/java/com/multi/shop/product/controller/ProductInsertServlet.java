package com.multi.shop.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.multi.shop.member.model.dto.MemberDTO;
import com.multi.shop.product.model.dto.AttachmentDTO;
import com.multi.shop.product.model.dto.CompanyDTO;
import com.multi.shop.product.model.dto.ProductDTO;
import com.multi.shop.product.service.ProductService;
import com.multi.shop.product.service.ProductServiceImpl;

import net.coobird.thumbnailator.Thumbnails;

/**
 * Servlet implementation class ProductInsertServlet
 */
@WebServlet("/product/insert")
public class ProductInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	//상품등록 폼으로 이동하기위한 jsp파일 경로설정
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = "/WEB-INF/views/product/insertform.jsp";
		
		//지정된 경로로 포워딩
		request.getRequestDispatcher(path).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// chatgpt => 요청이 multipart/form-data인지 확인
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(request)) {
			//파일저장 루트 경로설정
			String rootLocation = getServletContext().getRealPath("/");
			int maxFileSize = 1024 * 1024 * 10; //최대파일크기
			String encodingType = "UTF-8";

			System.out.println("파일 저장 ROOT 경로 : " + rootLocation);
			System.out.println("최대 업로드 파일 용량 : " + maxFileSize);
			System.out.println("인코딩 방식 : " + encodingType);

			String fileUploadDirectory = rootLocation + "/resources/upload/original/"; //원본파일 저장경로
			String thumbnailDirectory = rootLocation + "/resources/upload/thumbnail/"; //썸내일 저장경로

			File directory = new File(fileUploadDirectory);
			File directory2 = new File(thumbnailDirectory);

			if (!directory.exists() || !directory2.exists()) { //디렉토리가 없다면
				System.out.println("폴더 생성 : " + directory.mkdirs()); 
				System.out.println("폴더 생성 : " + directory2.mkdirs()); 
			}
			
			// 폼데이터와 파일데이터를 담을 map, list생성
			Map<String, String> parameter = new HashMap<>();
			List<Map<String, String>> fileList = new ArrayList<>();

			/* 파일을 업로드할 시 최대 크기나 임시 저장할 폴더의 경로 등을 포함하기 위한 인스턴스이다. */
			// DiskFileItemFactory와 ServletFileUpload를 이용해 파일 업로드 환경 설정
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			fileItemFactory.setRepository(new File(fileUploadDirectory));
			fileItemFactory.setSizeThreshold(maxFileSize);

			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			String path = "";
			try {
				List<FileItem> fileItems = fileUpload.parseRequest(request);
				for (FileItem item : fileItems) {
					/* 폼 데이터는 isFormField 속성이 true이고, 파일은 isFormField 속성이 false이다. */
					System.out.println(item);
				}

				for (int i = 0; i < fileItems.size(); i++) {
					FileItem item = fileItems.get(i);

					if (!item.isFormField()) { // 파일데이터인경우
						if (item.getSize() > 0) {
							String filedName = item.getFieldName();
							String originFileName = item.getName();
							
							//파일 확장자 추출
							int dot = originFileName.lastIndexOf(".");
							String ext = originFileName.substring(dot);
							
							// 랜덤파일이름 생성
							String randomFileName = UUID.randomUUID().toString().replace("-", "") + ext;
							System.out.println(randomFileName);
							
							//저장할 파일객체 생성
							File storeFile = new File(fileUploadDirectory + "/" + randomFileName);
							
							//파일저장
							item.write(storeFile);

							/* 필요한 정보를 Map에 담는다. */
							Map<String, String> fileMap = new HashMap<>();
							fileMap.put("filedName", filedName);
							fileMap.put("originFileName", originFileName);
							fileMap.put("savedFileName", randomFileName);
							fileMap.put("savePath", fileUploadDirectory);

							/* 제목 사진과 나머지 사진을 구분하고 썸네일도 생성한다. */
							int width = 0;
							int height = 0;
							if ("thumbnailImg1".equals(filedName)) {
								fileMap.put("fileType", "TITLE");

								/* 썸네일로 변환 할 사이즈를 지정한다. */
								width = 350;
								height = 200;
							} else {
								fileMap.put("fileType", "BODY");

								width = 120;
								height = 100;
							}

							/* 썸네일로 변환 후 저장한다. */
							Thumbnails.of(fileUploadDirectory + randomFileName).size(width, height)
									.toFile(thumbnailDirectory + "thumbnail_" + randomFileName);

							/* 나중에 웹서버에서 접근 가능한 경로 형태로 썸네일의 저장 경로도 함께 저장한다. */
							fileMap.put("thumbnailPath", "/resources/upload/thumbnail/thumbnail_" + randomFileName);

							fileList.add(fileMap);

						}

					} else {
						/*
						 * 전송된 폼의 name은 getFiledName()으로 받아오고, 해당 필드의 value는 getString()으로 받아온다. 하지만 인코딩
						 * 설정을 하더라도 전송되는 파라미터는 ISO-8859-1로 처리된다. 별도로 ISO-8859-1로 해석된 한글을 UTF-8로 변경해주어야
						 * 한다.
						 */
						parameter.put(item.getFieldName(),
								new String(item.getString().getBytes("ISO-8859-1"), "UTF-8"));

					}

				}

				System.out.println("parameter : " + parameter);
				System.out.println("fileList : " + fileList);
				
				//파라미터에서 상품정보추출
				String name = parameter.get("name");
				String content = parameter.get("content");
				String createdPerson = ((MemberDTO) request.getSession().getAttribute("loginMember")).getId();
				String companyId = parameter.get("company");
				int price = Integer.parseInt(parameter.get("price"));
				
				// ProductDTO 객체 생성 및 설정
				ProductDTO newProduct = new ProductDTO();
				newProduct.setName(name);
				newProduct.setContent(content);
				newProduct.setPrice(price);
				newProduct.setCreatePerson(createdPerson);

				CompanyDTO company = new CompanyDTO();
				company.setId(companyId);

				newProduct.setCompany(company);

				System.out.println("thumbnail product : " + newProduct);
				
				//파일 리스트를 productDTO에 추가
				newProduct.setAttachments(new ArrayList<AttachmentDTO>());
				List<AttachmentDTO> list = newProduct.getAttachments();
				for (int i = 0; i < fileList.size(); i++) {
					Map<String, String> file = fileList.get(i);
					
					
					AttachmentDTO tempFileInfo = new AttachmentDTO();
					tempFileInfo.setOriginalName(file.get("originFileName"));
					tempFileInfo.setSavedName(file.get("savedFileName"));
					tempFileInfo.setSavePath(file.get("savePath"));
					tempFileInfo.setThumbnailPath(file.get("thumbnailPath"));
					tempFileInfo.setCreatePerson(createdPerson);

					list.add(tempFileInfo);
				}

				System.out.println("thumbnail product setAttachments : " + newProduct);

				/* 서비스 메소드를 요청한다. */
				int result = productService.insertProduct(newProduct);

				if (result > 0) {
					path = "/WEB-INF/views/common/success.jsp";
					request.setAttribute("successCode", "insertProduct");
				} else {
					path = "/WEB-INF/views/common/failed.jsp";
					request.setAttribute("message", "제품 등록 실패!");
				}

			} catch (Exception e) {
				// 어떤 종류의 Exception이 발생 하더라도실패 시 파일을 삭제해야 한다.
				int cnt = 0;
				for (int i = 0; i < fileList.size(); i++) {
					Map<String, String> file = fileList.get(i);

					File deleteFile = new File(fileUploadDirectory + "/" + file.get("savedFileName"));
					boolean isDeleted = deleteFile.delete();

					if (isDeleted) {
						cnt++;
					}
				}

				if (cnt == fileList.size()) {
					System.out.println("업로드에실패한 모든 사진 삭제 완료!");
					e.printStackTrace();
				} else {
					e.printStackTrace();
				}
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("message", "제품 등록 실패!");

			}
			request.getRequestDispatcher(path).forward(request, response);
		}

	}

}
