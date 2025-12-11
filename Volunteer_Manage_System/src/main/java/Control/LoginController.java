package Control;

import DAO.UserDAO;
import com.mysql.cj.Session;
import model.User;
import util.CaptchaServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO(); // 初始化DAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 跳转到登录页面
        request.getRequestDispatcher("/Login-Regis/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        handleLogin(request, response);
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");

        // 验证码检查
        HttpSession session = request.getSession();
        String sessionCaptcha = (String) session.getAttribute(CaptchaServlet.CAPTCHA_KEY);
        
        // 移除已使用的验证码
        session.removeAttribute(CaptchaServlet.CAPTCHA_KEY);
        
        // 检查验证码
        if (captcha == null || sessionCaptcha == null || !captcha.equalsIgnoreCase(sessionCaptcha)) {
            request.setAttribute("errorMessage", "验证码错误");
            request.getRequestDispatcher("/Login-Regis/login.jsp").forward(request, response);
            return;
        }

        // 基本验证
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "用户名和密码不能为空");
            request.getRequestDispatcher("/Login-Regis/login.jsp").forward(request, response);
            return;
        }

        // 调用DAO验证登录
        User user = userDAO.validateLoginCredentials(username.trim(), password.trim());

        if (user != null) {
            // 登录成功，创建session
            session.setAttribute("currentUser", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("role", user.getRole());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("password", user.getPassword());

            // 根据用户角色跳转到不同页面
            String role = user.getRole();
            if ("admin".equals(role)) {
                // 管理员跳转到管理员页面
                response.sendRedirect(request.getContextPath() + "/admin/dashboard.jsp");
            } else {
                // 志愿者跳转到志愿者页面
                response.sendRedirect(request.getContextPath() + "/Volunteer/index.jsp");
            }
        } else {
            // 登录失败
            request.setAttribute("errorMessage", "用户名或密码错误");
            request.getRequestDispatcher("/Login-Regis/login.jsp").forward(request, response);
        }
    }
}
