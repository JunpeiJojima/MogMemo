package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.AccountEntity;
import entity.SignInEntity;
import logic.SignInLogic;

// そのまま呼び出されたらGET　＝＞　ログイン画面へ
// POSTで呼び出されたら　＝＞　ログイン成功画面へ
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("userName").equals("") ||
				request.getParameter("passWord").equals("")) {
			request.setAttribute("errorMsg", "※入力内容が正しくない可能性があります");
			this.doGet(request, response);
		} else {
			// リクエストパラメーターの取得
			request.setCharacterEncoding("UTF-8");
			String userName = request.getParameter("userName");
			String passWord = request.getParameter("passWord");

			// ログイン処理の実行
			SignInEntity signInEntity = new SignInEntity(userName, passWord);
			SignInLogic signInLogic = new SignInLogic();
			// アカウント情報を取得
			AccountEntity accountEntity = signInLogic.execute(signInEntity);

			// ログイン成否で処理分岐
			if (accountEntity != null) { // ログイン成功時
				// セッションスコープにアカウントを保存
				HttpSession session = request.getSession();
				session.setAttribute("accountEntity", accountEntity);

				// フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/MainPageServlet");
				dispatcher.forward(request, response);
			} else {
				// ログイン失敗時
				request.setAttribute("errorMsg", "※IDとパスワードが一致しません");
				RequestDispatcher dispatcher = request.getRequestDispatcher("");
				dispatcher.forward(request, response);
			}
		}
	}

}
