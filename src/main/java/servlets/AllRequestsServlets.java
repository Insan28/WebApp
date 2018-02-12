package servlets;
import templater.PageGenerator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class AllRequestsServlets extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {
        //формируем ответ
        Map<String,Object> pageVariables = createPageVariablesMap(request);
        pageVariables.put("message","");
        //создание страницы
        response.getWriter().println(PageGenerator.instance().GetPage("page.html",pageVariables));
        response.getWriter().println(request.getParameter("key"));
        response.setContentType("text/html;charset=utf-8");
        //ключ возврата
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        Map<String,Object> pageVariables = createPageVariablesMap(request);
        String message = request.getParameter("message");

        response.setContentType("text/html;charset=utf-8");

        if (message == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);//сервер понял запрос, но отказался вып его(код ошибки)
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        pageVariables.put("message", message == null ? "" : message);

        response.getWriter().println(PageGenerator.instance().GetPage("page.html", pageVariables));
    }

    private static Map<String,Object> createPageVariablesMap(HttpServletRequest request)
    {
        Map<String,Object> pageVariables = new HashMap<String, Object>();
        pageVariables.put("method",request.getMethod());
        pageVariables.put("URL",request.getRequestURL().toString());
        pageVariables.put("pathInfo",request.getPathInfo());
        pageVariables.put("sessionID",request.getSession().getId());
        pageVariables.put("parameters",request.getParameterMap().toString());
        return pageVariables;
    }
}
