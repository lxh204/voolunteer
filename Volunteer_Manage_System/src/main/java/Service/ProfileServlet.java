package Service;

import DAO.VolunteerDAO;
import model.Volunteer;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/service/Volunteer/profile")
public class ProfileServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 获取当前登录用户的ID
            HttpSession session = request.getSession();
            Integer userId = (Integer) session.getAttribute("userId");

            if (userId == null) {
                response.sendRedirect(request.getContextPath() + "/Login-Regis/login.jsp");
                return;
            }
            
            VolunteerDAO volunteerDAO = new VolunteerDAO();
            Volunteer volunteer = volunteerDAO.findById(userId);
            
            request.setAttribute("volunteer", volunteer);
            request.getRequestDispatcher("/Volunteer/profile.jsp").forward(request, response);
            
        } catch (SQLException e) {
            throw new ServletException("数据库操作失败", e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        try {
            // 获取表单数据
            String realName = request.getParameter("realName");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String experience = request.getParameter("experience");
            
            // 获取当前登录用户ID
            HttpSession session = request.getSession();
            Integer userId = (Integer) session.getAttribute("userId");
            String username = (String) session.getAttribute("username");
            String email = (String) session.getAttribute("email");
            String password = (String) session.getAttribute("password");
            if (userId == null) {
                response.sendRedirect(request.getContextPath() + "/Login-Regis/login.jsp");
                return;
            }
            
            // 创建志愿者对象并设置属性
            Volunteer volunteer = new Volunteer();
            volunteer.setId(userId);
            volunteer.setRealName(realName);
            volunteer.setGender(gender);
            volunteer.setPhone(phone);
            volunteer.setAddress(address);
            volunteer.setExperience(experience);
            volunteer.setUsername(username);
            volunteer.setEmail(email);
            volunteer.setPassword(password);

            VolunteerDAO volunteerDAO = new VolunteerDAO();
            
            if (volunteerDAO.updateProfile(volunteer)) {
                // 更新成功，重定向到个人页面
                response.sendRedirect("/service/Volunteer/profile.jsp?success=1");
            } else {
                // 更新失败，返回错误信息
                request.setAttribute("error", "更新个人信息失败");
                request.getRequestDispatcher("/Volunteer/profile.jsp").forward(request, response);
            }
            
        } catch (SQLException e) {
            throw new ServletException("数据库操作失败", e);
        }
    }
}
