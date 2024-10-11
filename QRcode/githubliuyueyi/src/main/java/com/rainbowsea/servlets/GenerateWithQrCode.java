package com.rainbowsea.servlets;

import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeDeWrapper;
import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeGenWrapper;
import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeOptions;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet("/generateWithLogon")
// 设置传输文件的最大值和最小值
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100)
public class GenerateWithQrCode extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String url = request.getParameter("url");

            // 生成黑白二维码
       /*     BufferedImage image = QrCodeGenWrapper.of(url)
                    .asBufferedImage();*/

            // 生成带有 logo 的黑白二维码
            BufferedImage image = QrCodeGenWrapper.of(url)
                    .setLogo(request.getPart("logo").getInputStream())
                    .setLogoRate(7) // 设置Logo图片与二维码之间的比例，7表示Logo的宽度等于二维码的 1/7
                    .setLogoStyle(QrCodeOptions.LogoStyle.ROUND) // 设置logo图片的样式，将logo的边框形状设置为圆锐角
                    .asBufferedImage();


            // 生成带有背景图的二维码
/*            BufferedImage image = QrCodeGenWrapper.of(url)
                    .setBgImg(request.getPart("logo").getInputStream())
                    .setBgOpacity(0.5F)  // 设置背景图片的透明度
                    .asBufferedImage();*/

            // 生成特殊形状的二维码
         /*   BufferedImage image = QrCodeGenWrapper.of(url)
                    .setDrawEnableScale(true)  // 启用二维码绘制时的缩放功能
                    .setDrawStyle(QrCodeOptions.DrawStyle.DIAMOND)  // 指定绘制采用钻石形状绘制
                    .asBufferedImage();*/


            // 生成图片填充二维码
       /*     BufferedImage image = QrCodeGenWrapper.of(url)
                    .setErrorCorrection(ErrorCorrectionLevel.H) // 设置二维码的错误纠正规则
                    .setDrawStyle(QrCodeOptions.DrawStyle.IMAGE) // 绘制二维码时采用图片填充
                    .addImg(1, 1, request.getPart("logo").getInputStream()) // 添加图片
                    .asBufferedImage();*/

            // 将二维码图片响应到浏览器
            ImageIO.write(image, "png", response.getOutputStream());


        } catch (WriterException e) {
            e.printStackTrace();
        }

    }
}
