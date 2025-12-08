package Service;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/ApplicationServlet")
public class ApplicationServlet extends HttpServlet {
    private ApplicationDAO applicationDAO = new ApplicationDAO();
    private ActivityDAO activityDAO = new ActivityDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("applyForm".equals(action)) {
            showApplyForm(request, response);
        } else if ("myApplications".equals(action)) {
            showMyApplications(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("apply".equals(action)) {
            applyForActivity(request, response);
        }
    }

    private void showApplyForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int activityId = Integer.parseInt(request.getParameter("activityId"));
            Activity activity = activityDAO.getActivityById(activityId);

            if (activity != null && "open".equals(activity.getStatus())) {
                request.setAttribute("activity", activity);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/applyForm.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/ActivityServlet?action=list&error=1");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/ActivityServlet?action=list&error=1");
        }
    }

    private void applyForActivity(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            Volunteer volunteer = (Volunteer) request.getSession().getAttribute("volunteer");
            int activityId = Integer.parseInt(request.getParameter("activityId"));
            String applyReason = request.getParameter("applyReason");

            Application application = new Application();
            application.setVolunteerId(volunteer.getId());
            application.setActivityId(activityId);
            application.setApplyReason(applyReason);
            application.setStatus("pending");

            boolean success = applicationDAO.insert(application);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/ActivityServlet?action=list&success=1");
            } else {
                response.sendRedirect(request.getContextPath() + "/ActivityServlet?action=list&error=2");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/ActivityServlet?action=list&error=3");
        }
    }

    private void showMyApplications(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Volunteer volunteer = (Volunteer) request.getSession().getAttribute("volunteer");
            List<Application> applications = applicationDAO.getApplicationsByVolunteerId(volunteer.getId());
            request.setAttribute("applications", applications);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/myApplications.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/volunteer/main.jsp");
        }
    }
}