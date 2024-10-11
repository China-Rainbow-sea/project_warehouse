package com.rainbowsea.servlets;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/create")
public class GenerateQRcode extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            // 将图片响应到浏览器客户端上
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image,"png",out);
            out.flush();
            out.close();

        } catch (WriterException e) {
            e.printStackTrace();
        }

    }
}
