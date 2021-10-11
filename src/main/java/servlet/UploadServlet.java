package servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import constant.Constant;
import entity.AccountEntity;
import entity.MealEntity;
import logic.PostMealLogic;

@WebServlet("/UploadServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 2)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Part part;
		String fileName = Constant.DEFAULT_IMAGES_PATH;
		if (!(request.getPart("imageFile").getSubmittedFileName().length() == 0)) {
		part = request.getPart("imageFile");
		fileName = fileRename(part);
		String contextPath = getServletContext().getRealPath(Constant.IMAGES_DIRECTRY_PATH);
		String filePath = contextPath + File.separator + fileName;
		part.write(filePath);
		} 
		MealEntity mealEntity = createMealEntity(request, fileName);

		PostMealLogic postMealLogic = new PostMealLogic();
		postMealLogic.execute(mealEntity);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/MainPageServlet");
		dispatcher.forward(request, response);
	}

	private MealEntity createMealEntity(HttpServletRequest request, String filePath) {
		
		HttpSession session = request.getSession();
		AccountEntity loginUser = (AccountEntity) session.getAttribute("accountEntity");
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateNow = dateFormat.format(date);
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		String timeNow = timeFormat.format(date);

		String category = request.getParameter("category");
		int mealNo;
		if (category.equalsIgnoreCase("Moning")) {
			mealNo = MealEntity.moning;
		} else if (category.equalsIgnoreCase("Lunch")) {
			mealNo = MealEntity.lunch;
		} else {
			mealNo = MealEntity.dinner;
		}
		String cookingName = request.getParameter("cookingName");
		if (cookingName.length() == 0) {
			cookingName = "No Title";
		}
		String cookingMemo = request.getParameter("cookingMemo");
		if (cookingMemo.length() == 0) {
			cookingMemo = "Nothing";
		}
		String mealDays = dateNow;
		String mealTimes = timeNow;
		String imagesPath = filePath;
		int userId = loginUser.getUserId();

		MealEntity mealEntity = new MealEntity(
				category, 
				mealNo, 
				cookingName, 
				cookingMemo, 
				mealDays, 
				mealTimes,
				imagesPath,
				userId);

		return mealEntity;
	}

	private String fileRename(Part part) {
		String originalFileName = part.getSubmittedFileName();
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String dateNow = dateFormat.format(date);
		String reFileName;
		if(originalFileName.equals(Constant.DEFAULT_IMAGES_PATH)) {
			return originalFileName;
		} else {
		reFileName = dateNow + originalFileName;
		return reFileName;
		}
	}

}
