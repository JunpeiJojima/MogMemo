package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.RegistrateEntity;
import logic.NewRegistLogic;

@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

	// ユーザー登録処理を行って、resistResult.jspへフォワード
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String emailAddress = request.getParameter("emailAddress");
		
		RegistrateEntity registrateEntity = new RegistrateEntity(userName,password,emailAddress);
		
		//ユーザー登録処理
		NewRegistLogic newRegistLogic = new NewRegistLogic();
		boolean registResult = newRegistLogic.regist(registrateEntity);
		
		//登録結果のbooleanをリクエストスコープに保存
		request.setAttribute("registResult", registResult);
		
		//登録結果画面にフォワード
		RequestDispatcher dispatcher =
						request.getRequestDispatcher("/WEB-INF/jsp/registResult.jsp");
		dispatcher.forward(request, response);
		
	}

}
