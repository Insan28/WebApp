package main;
import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SessionsServlet;
import servlets.SignUpServlet;
import servlets.SignInServlet;

public class main {
    public static void main(String[] args) throws Exception
    {
        int port = 8080;
        AccountService accountService= new AccountService();
        accountService.AddNewUser(new UserProfile("admin"));
        accountService.AddNewUser(new UserProfile("test"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)),"/sign up");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)),"/sign in");
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)),"/api/v1/sessions");

        //обращение  статическим ресурсам
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("register");

        //обработчик запросов
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler,context});

        Server server = new Server(port);
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
