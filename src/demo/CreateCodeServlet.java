package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCodeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5454344963450581231L;

	// 图片宽度
	private static final int IMAGE_WIDTH = 110;
	// 图片高度
	private static final int IMAGE_HEIGHT = 30;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.在内存中创建一幅图片
		BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_BGR);
		
		// 2.向图片上写数据
		Graphics g = image.getGraphics();
		
		// 设背景色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
		
		// 画5个验证码字符
		String checkcode = "";
		for (int i = 0; i < 5; i++) {
			g.setColor(generateColor());
			g.setFont(generateFont());
			String str = generateStr();
			checkcode += str;
			g.drawString(str, 20 * i, 25);
		}
		
		// 画干扰点
		for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int x = random.nextInt(IMAGE_WIDTH);
            int y = random.nextInt(IMAGE_HEIGHT);
            g.setColor(generateColor());
            g.fillOval(x, y, 2, 2);
		}
		// 画干扰线
		for (int i = 0; i < 5; i++) {
			Random random = new Random();
            int x1 = random.nextInt(IMAGE_WIDTH);
            int y1 = random.nextInt(IMAGE_HEIGHT);
            int x2 = random.nextInt(IMAGE_WIDTH);
            int y2 = random.nextInt(IMAGE_HEIGHT);
            g.setColor(generateColor());
            g.drawLine(x1, y1, x2, y2);
		}
		
		// 6.通知客户机以图片方式打开发送过去的数据
		resp.setContentType("image/jpeg");
		
		// 7.禁止浏览器缓存随机图片
		resp.setDateHeader("Expires", -1);
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Pragma", "no-cache");
		
		// 把随机生成的验证码，保存到session
		// 验证码不区分大小写，所以这里转为小写
		req.getSession().setAttribute("checkcode", checkcode.toLowerCase());
		
		ImageIO.write(image, "jpg", resp.getOutputStream());
	}
	
	/**
	 * 生成随机颜色
	 * @return
	 */
	public Color generateColor() {
		Random r = new Random();
		return new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
	}
	
	/**
	 * 生成随机数[0-9a-zA-Z]
	 * @return
	 */
	public String generateStr() {
		String[] nums = new String[62];
		
		// 添加0-9这10个数字
		for (int i = 0; i < 10; i++) {
			nums[i] = String.valueOf(i);
		}
		
		// 添加A-Z这26个大写字母
		for (int i = 65; i < 91; i++) {
			nums[i - 55] = Character.toString((char) i);
		}
		
		// 添加a-z这26个小写字母
		for (int i = 97; i < 123; i++) {
			nums[i - 61] = Character.toString((char) i);
		}
		
        // 产生一个随机数
        Random random = new Random();
        int index = random.nextInt(62);
        return nums[index];
	}
	
	/**
	 * 生成随机字体
	 * @return
	 */
	public Font generateFont() {
		String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		int[] font_styles = new int[]{Font.BOLD, Font.ITALIC, Font.BOLD|Font.ITALIC};
		Random random = new Random();
		
		return new Font(fontNames[random.nextInt(fontNames.length)], font_styles[random.nextInt(font_styles.length)], 28);
	}
}
