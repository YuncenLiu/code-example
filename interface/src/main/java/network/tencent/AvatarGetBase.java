package network.tencent;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: network.tencent
 * @author: Xiang想
 * @createTime: 2024-08-27  12:01
 * @description: TODO
 * @version: 1.0
 */
public class AvatarGetBase {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

//        String imageUrl = "https://qlogo4.store.qq.com/qzone/1321892831/1321892831/100.png";
        String imageUrl = "http://q1.qlogo.cn/g?b=qq&nk=1850697175&s=1";
        try {
            // 1. 创建 URL 对象
            URL url = new URL(imageUrl);

            // 2. 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 3. 获取输入流
            InputStream inputStream = connection.getInputStream();

            // 4. 读取输入流到字节数组
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();

            // 5. 将字节数组转换为 Base64 编码
            byte[] imageBytes = outputStream.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // 6. 构造 HTML <img> 标签
            String imgTag = "<img src='data:image/png;base64," + base64Image + "' alt='Base64 Image'/>";

            // 7. 输出 HTML <img> 标签
            System.out.println("HTML <img> Tag:");
            System.out.println(imgTag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("end-start = " +( end - start));
    }
}
