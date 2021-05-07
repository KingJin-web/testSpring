package com.king.spring_rdering.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-05-05 20:31
 */


public class VerifyCodeUtil {

   // public static final String VERIFY_CODES = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    public static final String VERIFY_CODES = "123456789" +
            //"abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static int length = 4;


    public static int size = 40;


    public static int Dsize = 1000;

    public static int width = 200;
    public static int height = 80;


    private static Random random = new Random();

    public static int getLength() {
        return length;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    /**
     * VerifyCodeUtil包含静态方法，只要设置相关参数就可以随机生成想要结果的验证码图片。
     * VerifyCodeUtil.setHeight(37);// 设置验证码高度
     * VerifyCodeUtil.setWidth(83);// 设置验证码宽度
     * VerifyCodeUtil.setSize(10);// 设置验证码干扰线的条数
     * VerifyCodeUtil.setDsize(10);// 设置验证码干扰点的个数
     * VerifyCodeUtil.setSize(4);//设置验证码的长度
     * String indentycode=VerifyCodeUtil.generateVerifyCode();// 随机生成验证码 返回字符串验证码（默认为4为）
     * request.getSession().setAttribute("code", indentycode);// 一般存储在session域内用于登录的校验（JavaWeb项目）
     * BufferedImage image = VerifyCodeUtil.getBufferedImage(indentycode);// 将随机验证码字符串传入生成图片
     * ImageIO.write(image, "jpg", response.getOutputStream());// 在HttpServletResponse中有内置方法getOutputStream()，可以将响应转换成输出流，在通过ImageIO对象的write方法就可以输出到前台页面上去了。
     *
     * @Author lhj
     * @Description //TODO
     * @Date 16:40 2020/6/28 0028
     **/

    public static String generateVerifyCode() {
        return generateVerifyCode(length, VERIFY_CODES);
    }


    public static String generateVerifyCode(int length, String sources) {
        if (sources == null || sources.length() == 0) {
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }


    public static void outputImage(OutputStream os, String code) throws IOException {
        BufferedImage image = getBufferedImage(code);
        ImageIO.write(image, "jpg", os);
    }

    public static BufferedImage getBufferedImage(String code) {
        int verifySize = code.length();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Random rand = new Random();
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color[] colors = new Color[5];
        Color[] colorSpaces = new Color[]{Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA,
                Color.ORANGE, Color.PINK, Color.YELLOW};
        float[] fractions = new float[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
            fractions[i] = rand.nextFloat();
        }
        Arrays.sort(fractions);

        g2.setColor(Color.pink);
        g2.fillRect(0, 0, width, height);

        Color c = getRandColor(200, 250);
        g2.setColor(c);
        g2.fillRect(0, 2, width, height - 4);


        Random random = new Random();
        g2.setColor(getRandColor(160, 200));
        for (int i = 0; i < size; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g2.drawLine(x, y, x + xl + 40, y + yl + 20);
        }


        float yawpRate = 0.05f;

        for (int i = 0; i < Dsize; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }

        shear(g2, width, height, c);

        g2.setColor(getRandColor(100, 160));
        int fontSize = height - 4;
        Font font = new Font("Algerian", Font.ITALIC, fontSize);
        g2.setFont(font);
        char[] chars = code.toCharArray();
        for (int i = 0; i < verifySize; i++) {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1),
                    (width / verifySize) * i + fontSize / 2, height / 2);
            g2.setTransform(affine);
            g2.drawChars(chars, i, 1, ((width - 10) / verifySize) * i + 5, height / 2 + fontSize / 2 - 10);
        }

        g2.dispose();
        return image;
    }

    private static Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private static void shearX(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }

    }

    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        VerifyCodeUtil.size = size;
    }

    public static int getDsize() {
        return Dsize;
    }

    public static void setDsize(int dsize) {
        Dsize = dsize;
    }

    public static Random getRandom() {
        return random;
    }

    public static void setRandom(Random random) {
        VerifyCodeUtil.random = random;
    }

    public static String getVerifyCodes() {
        return VERIFY_CODES;
    }

    public static void setLength(int length) {
        VerifyCodeUtil.length = length;
    }

    public static void setWidth(int width) {
        VerifyCodeUtil.width = width;
    }

    public static void setHeight(int height) {
        VerifyCodeUtil.height = height;
    }


    /**
     * 输出宽度200、高度80、长度5位的验证码输出到页面，返回验证码字符串
     *
     * @param response
     * @return
     * @throws IOException
     */
    public static String outputImage(HttpServletResponse response) throws IOException {
        height = 80;
        length = 5;
        width = 200;
        return outputImage(1,response);
    }

    /**
     * 生成验证码图片输出成图片，返回验证码字符串
     * @param response
     * @return
     * @throws IOException
     */
    public static String outputImage( int a, HttpServletResponse response)
            throws IOException {
        // 随机生成验证码
        String verifyCode = VerifyCodeUtil.generateVerifyCode();
        // 图形写给浏览器
        response.setContentType("image/jpeg");
        // 发头控制浏览器不要缓存
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        //允许跨域访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 获取响应输出流
        OutputStream out = response.getOutputStream();
        // 输出图片
        outputImage(out, verifyCode);
        return verifyCode;
    }
}