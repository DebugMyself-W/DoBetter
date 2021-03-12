import net.coobird.thumbnailator.Thumbnails;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * https://github.com/coobird/thumbnailator
 * https://www.cnblogs.com/linkstar/p/7412012.html
 *
 * <dependency>
 * <groupId>net.coobird</groupId>
 * <artifactId>thumbnailator</artifactId>
 * <version>0.4.13</version>
 * </dependency>
 */
public class PictureCompressDemo {
    public static void main(String[] args) {
        PictureHandle picture = new PictureHandle();
        List<Float> list = new ArrayList<>();
        list.add(1f);
        list.add(0.5f);
        list.add(0.25f);
        list.add(0.125f);
        list.add(0.06f);
        list.add(0.03f);
        list.add(0.01f);
        for (Float i : list) {
            picture.thumbnails(i);
        }
    }
}

class PictureHandle {
    /**
     * 图片压缩
     *
     * @param i
     */
    void thumbnails(Float i) {
        try {
            long startTime = System.currentTimeMillis();
            Thumbnails.of("F:\\test\\picture.jpg")
                    .scale(1f)
                    .outputQuality((Float) i)
                    .toFile("F:\\test\\" + i + "picture.jpg");
            System.out.println("压缩倍率：" + i + "耗时" + (System.currentTimeMillis() - startTime));
        } catch (IOException e) {
            System.out.println("fail");
            e.printStackTrace();

        }
    }

    /**
     * 图片转base64字符串
     *
     * @param imgFile 图片路径
     * @return
     */
    public static String imageToBase64Str(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * base64编码字符串转换为图片
     *
     * @param imgStr base64编码字符串
     * @param path   图片路径
     * @return
     */
    public static boolean base64StrToImage(String imgStr, String path) {
        if (imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //文件夹不存在则自动创建
            File tempFile = new File(path);
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(tempFile);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static void main(String[] args) {
        String base64Str = imageToBase64Str("F:\\test\\picture.jpg");
        System.out.println(base64Str);
        boolean b = base64StrToImage(base64Str, "F:\\test\\picturetest.jpg");
        System.out.println(b);
    }
}
