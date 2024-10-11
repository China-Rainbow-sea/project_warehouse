package com.rainbowsea.servlets;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/generateWithLogon")
// 设置传输文件的最大值和最小值
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 100)
public class GerateQRcodeWithLogon extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        // 使用谷歌提供的 zxing开源库，生成普通的黑白二维码(核心代码)


        try {
            // 需要创建一个Map集合，用这个 Map集合存储二维码相关的属性(参数)
            Map map = new HashMap();
            // 设置二维码的误差校正级别
            map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            // 设置二维码的字符集
            map.put(EncodeHintType.CHARACTER_SET, "utf-8");
            // 设置二维码四周的留白
            map.put(EncodeHintType.MARGIN, 1);

            // 创建 zxing的核心对象， MultiFormatWriter （多格式写入器）
            // 通过 MultiFormatWriter 对象来生成二维码
            MultiFormatWriter writer = new MultiFormatWriter();

            // 获取文本内容
            String url = request.getParameter("url");
            //writer.encode(内容，什么格式的二维码，二维码的宽度，二维码的高度，二维码的参数)
            // 位矩阵对象
            BitMatrix bitMatrix = writer.encode(url, BarcodeFormat.QR_CODE, 300, 300, map);

            // 获取该位矩阵的宽度
            int width = bitMatrix.getWidth();
            // 获取该位矩阵的高度
            int height = bitMatrix.getHeight();

            // 生成二维码的图片
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            // 编写一个嵌套的循环，遍历二维数组的一个循环，遍历位矩阵对象
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    // 0xFF000000 : 0xFFFFFFFF 表示 黑白的十六进制
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            // 给二维码添加Logo
            // 第一部分: 将Logo缩放
            Part logoPart = request.getPart("logo");
            InputStream inputStream = logoPart.getInputStream();
            BufferedImage logoImage = ImageIO.read(inputStream);

            // 对获取到的logo图片进行缩放
            int logoWidth = logoImage.getWidth(null);
            int logoHeight = logoImage.getHeight(null);
            // 对 logo 图片的大小进行一个缩放，整理
            if (logoWidth > 60) {
                logoWidth = 60;
            }

            if (logoHeight > 60) {
                logoHeight = 60;
            }

            // 这一行代码非常重要，全靠它来进行缩放了
            // 使用平滑缩放算法对原始的Logo图像进行缩放得到一个全新的图像
            Image scaledLogo = logoImage.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);

            // 第二部分: 将缩放后的Logo画到黑白二维码上
            // 获取一个2D的画笔
            Graphics2D graphics2D = image.createGraphics();
            // 指定 logo 图片从哪里开始，也就是指定开始的坐标 x,y
            int x = (300 - logoWidth) / 2;
            int y = (300 - logoHeight) / 2;

            // 将缩放之后的logo画上去
            // 第一个参数是：logo缩放后的图片，二三参数是:坐标，第四个参数是: null
            graphics2D.drawImage(scaledLogo,x,y,null);
            // 创建一个具有指定位置，宽度，高度和圆角半径的圆角矩形，这个圆角矩形是用来绘制边框的
            Shape shape = new RoundRectangle2D.Float(x, y, logoWidth, logoHeight, 10, 10);
            // 使用一个宽度为 4 像素的基本笔触
            graphics2D.setStroke(new BasicStroke(4f));
            // 给 logo 画圆角矩形
            graphics2D.draw(shape);

            // 释放画笔
            graphics2D.dispose();

            // 将二维码图片响应到浏览器上
            ImageIO.write(image, "png", response.getOutputStream());

        } catch (WriterException e) {
            e.printStackTrace();
        }


    }
}
