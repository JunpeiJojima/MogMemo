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
import entity.RegistrateEntity;
import logic.InputNullCheckLogic;
import logic.RegistCheckLogic;

@WebServlet("/RegistrateServlet")
public class RegistrateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// signIn.jspに遷移
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("");	
		dispatcher.forward(request, response);
	}
	
	// ユーザーチェックして、OKならinput check jspへ　NGならSignIn.jspに飛ばす
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストパラメーターの取得
				request.setCharacterEncoding("UTF-8");
				String userName = request.getParameter("userName");
				String password = request.getParameter("password");
				String emailAddress = request.getParameter("emailAddress");
				
				RegistrateEntity registrateEntity = new RegistrateEntity(userName,password,emailAddress);
				
				AccountEntity accountEntity = null;
				
				//入力のnullチェック
				InputNullCheckLogic inputNullCheckLogic = new InputNullCheckLogic();
				boolean nullCheckResult = inputNullCheckLogic.nullCheck(registrateEntity);
				
				if(nullCheckResult) {
				// IDとメールが使用済みかのチェック
				RegistCheckLogic registCheckLogic = new RegistCheckLogic();
				//アカウント情報を取得
				accountEntity = registCheckLogic.registCheck(registrateEntity);
				}
				
				// ID、メール登録済みかで処理分岐
				if(accountEntity == null && nullCheckResult) { 
					//チェック成功時（登録してないとき）
					//セッションスコープに登録情報（registrateEntity)を保存
					HttpSession session = request.getSession();
					session.setAttribute("registrateEntity", registrateEntity);
					
					// input check jspへフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/inputConfirm.jsp");	
					dispatcher.forward(request, response);
				
				}else {//チェック失敗時（すでに使用されてる）
					
					errorMsg(request, registrateEntity, accountEntity, nullCheckResult);
					//入力画面（registInput）へフォワード
					RequestDispatcher dispatcher = 
							request.getRequestDispatcher("");	
					dispatcher.forward(request, response);
				}
	}

	private void errorMsg(HttpServletRequest request, 
						RegistrateEntity registrateEntity, 
						AccountEntity accountEntity,
						boolean nullCheckResult) {
		if(accountEntity == null) {
			request.setAttribute("errorMsg", "※入力内容が正しくない可能性があります");
		// エラーメッセージをrequestスコープに保存
		}else if(accountEntity.getUserName().equals(registrateEntity.getUserName())) {
			request.setAttribute("errorMsg", "※ユーザーIDが既に使用されています");
		}else if(accountEntity.getEmailAddress().equals(registrateEntity.getEmailAddress())){
			request.setAttribute("errorMsg", "※メールアドレスが既に使用されています");
		}else if(nullCheckResult){
			request.setAttribute("errorMsg", "※未入力があります　項目はすべて入力してください");
		}else {
			request.setAttribute("errorMsg", "※入力内容が正しくない可能性があります");
		}
	}

}
