package util;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public static final String CAPTCHA_KEY = "captcha";
    
    // 设置验证码图片的宽度和高度
    private static final int WIDTH = 120;
    private static final int HEIGHT = 40;
    
    // 验证码字符集
    private static final String CHAR_SET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int CODE_COUNT = 4; // 验证码长度
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 设置响应头
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        
        // 创建图像
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        
        // 设置背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        // 设置边框
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);
        
        // 生成随机验证码
        Random random = new Random();
        StringBuilder captchaCode = new StringBuilder();
        
        // 设置字体
        Font font = new Font("Arial", Font.BOLD, 24);
        g.setFont(font);
        
        // 画验证码字符
        for (int i = 0; i < CODE_COUNT; i++) {
            String code = String.valueOf(CHAR_SET.charAt(random.nextInt(CHAR_SET.length())));
            captchaCode.append(code);
            
            // 设置字符颜色
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            
            // 绘制字符
            g.drawString(code, 20 * i + 15, 28);
        }
        
        // 添加干扰线
        for (int i = 0; i < 10; i++) {
            g.setColor(getRandColor(160, 200));
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }
        
        // 添加噪点
        float yawpRate = 0.05f;
        int area = (int) (yawpRate * WIDTH * HEIGHT);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            image.setRGB(x, y, random.nextInt(255));
        }
        
        // 将验证码存储到Session中
        HttpSession session = request.getSession();
        session.setAttribute(CAPTCHA_KEY, captchaCode.toString());
        
        // 输出图像到客户端
        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }
    
    // 获取随机颜色
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
