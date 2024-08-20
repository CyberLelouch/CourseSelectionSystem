package com.xuan.selectcourse.util;

import javax.imageio.ImageIO;  // 用于处理图像输入输出的类
import javax.servlet.ServletException;  // 用于处理Servlet异常的类
import javax.servlet.annotation.WebServlet;  // 用于Servlet配置的注解
import javax.servlet.http.HttpServlet;  // HttpServlet基类，用于处理HTTP请求
import javax.servlet.http.HttpServletRequest;  // 用于处理HTTP请求的类
import javax.servlet.http.HttpServletResponse;  // 用于处理HTTP响应的类
import java.awt.*;  // 用于图形界面编程的类
import java.awt.image.BufferedImage;  // 用于处理内存中的图像的类
import java.io.IOException;  // 用于处理输入输出异常的类
import java.util.Random;  // 用于生成随机数的类

/**
 * 验证码生成Servlet
 */
@WebServlet("/checkCode")  // 配置Servlet的URL模式
public class CheckCodeServlet extends HttpServlet {
    // 处理GET请求的方法,生成验证码图片并返回给客户端。
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
        // 服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        // 在内存中创建一个长80，宽30的图片，默认黑色背景
        // 参数一：长
        // 参数二：宽
        // 参数三：颜色类型
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取画笔
        Graphics g = image.getGraphics();
        // 设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        // 填充图片背景为灰色
        g.fillRect(0, 0, width, height);

        // 产生4个随机验证码
        String checkCode = getCheckCode();
        // 将验证码放入HttpSession中
        request.getSession().setAttribute("checkCode", checkCode);
        // 设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        // 设置字体大小和样式
        g.setFont(new Font("黑体", Font.BOLD, 24));
        // 向图片上写入验证码
        g.drawString(checkCode, 15, 25);

        // 将内存中的图片输出到浏览器
        // 参数一：图片对象
        // 参数二：图片的格式，如PNG, JPG, GIF
        // 参数三：图片输出到的输出流
        ImageIO.write(image, "PNG", response.getOutputStream());
    }

    /**
     * 产生4位随机字符串
     */
    private String getCheckCode() {
        // 基础字符集
        String base = "0123456789ABCDEFGabcdefg";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= 4; i++) {
            // 产生0到size-1的随机值
            int index = r.nextInt(size);
            // 在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            // 将字符添加到StringBuffer中
            sb.append(c);
        }
        // 返回4位随机字符串
        return sb.toString();
    }

    // 处理POST请求的方法，将其委托给doGet方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
