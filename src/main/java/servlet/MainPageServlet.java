package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.AccountEntity;
import entity.MealEntity;
import logic.GetMealMapLogic;


@WebServlet("/MainPageServlet")
public class MainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		AccountEntity loginUser = (AccountEntity) session.getAttribute("accountEntity");
		
		GetMealMapLogic getMealMapLogic = new GetMealMapLogic();
		Map<String,List<MealEntity>> mealMap = getMealMapLogic.execute(loginUser.getUserId());
		request.setAttribute("mealMap", mealMap);
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp");
		dispatcher.forward(request, response);
		
	}

}
