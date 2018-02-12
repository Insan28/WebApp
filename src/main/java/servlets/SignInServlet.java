package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet{
    private final AccountService accountService;
    public SignInServlet(AccountService accountService)
    {
        this.accountService = accountService;
    }
    //войти в систему
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserProfile profile = accountService.getUserByLogin(login);
        if(profile == null || !(profile.getPassword().equals(password)))
        {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        else
        {
            Gson gson = new Gson();
            String json = gson.toJson(profile.getLogin());
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Authorized");
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
