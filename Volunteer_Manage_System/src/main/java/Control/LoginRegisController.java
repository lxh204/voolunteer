package Control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import DAO.UserDAO;
import model.User;
import java.io.IOException;

@WebServlet("/LoginRegisController")
public class LoginRegisController extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO(); // 初始化DAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");

        if ("login".equals(action)) {
            // 跳转到登入页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else if ("regis".equals(action)) {
            // 跳转到注册页面
            request.getRequestDispatcher("/regis.jsp").forward(request, response);
        } else {
            // 默认跳转到登录页面
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");

        if ("login_submit".equals(action)) {
            handleLogin(request, response);
        } else if ("regis_submit".equals(action)) {
            handleRegis(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "未知操作");
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 基本验证
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "用户名和密码不能为空");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // 调用DAO验证登录
        User user = userDAO.validateLoginCredentials(username.trim(), password.trim());

        if (user != null) {
            // 登录成功，创建session
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());

            // 跳转到志愿者列表页面
            response.sendRedirect(request.getContextPath() + "/volunteer_list.jsp");
        } else {
            // 登录失败
            request.setAttribute("errorMessage", "用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    private void handleRegis(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // 基本验证
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "用户名和密码不能为空");
            request.getRequestDispatcher("/regis.jsp").forward(request, response);
            return;
        }

        // 检查用户名是否已存在
        if (userDAO.checkRegisNameExists(username.trim())) {
            request.setAttribute("errorMessage", "用户名已存在");
            request.getRequestDispatcher("/regis.jsp").forward(request, response);
            return;
        }

        // 创建新用户（默认角色为volunteer）
        User newUser = new User(username.trim(), password.trim(), email, "volunteer");
        boolean success = userDAO.createRegisUser(newUser);

        if (success) {
            // 注册成功，跳转到登录页面
            request.setAttribute("successMessage", "注册成功，请登录");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "注册失败，请重试");
            request.getRequestDispatcher("/regis.jsp").forward(request, response);
        }
    }
}