package Control;

import DAO.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO(); // 初始化DAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 跳转到注册页面
        request.getRequestDispatcher("/Login-Regis/regis.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        handleRegis(request, response);
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
            request.getRequestDispatcher("/Login-Regis/regis.jsp").forward(request, response);
            return;
        }

        // 检查用户名是否已存在
        if (userDAO.checkRegisNameExists(username.trim())) {
            request.setAttribute("errorMessage", "用户名已存在");
            request.getRequestDispatcher("/Login-Regis/regis.jsp").forward(request, response);
            return;
        }

        // 创建新用户（默认角色为volunteer）
        User newUser = new User(username.trim(), password.trim(), email, "volunteer");
        boolean success = userDAO.createRegisUser(newUser);

        if (success) {
            // 注册成功，跳转到登录页面
            request.setAttribute("successMessage", "注册成功，请登录");
            request.getRequestDispatcher("/Login-Regis/login.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "注册失败，请重试");
            request.getRequestDispatcher("/Login-Regis/regis.jsp").forward(request, response);
        }
    }
}
