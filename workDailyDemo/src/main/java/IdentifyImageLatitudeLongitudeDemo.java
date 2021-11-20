import com.alibaba.fastjson.JSONObject;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;

/**
 * @author debugmyself
 * @Description 此demo可以获取图片信息
 * @tips 微信原图，复制都有经纬度信息
 * https://lbsyun.baidu.com/
 * https://blog.csdn.net/zwrlj527/article/details/119823407
 * https://drewnoakes.com/code/exif/
 *
 **/
public class IdentifyImageLatitudeLongitudeDemo {
    public static void main(String[] args) throws Exception {

        File file = new File("C:\\Users\\86151\\Desktop\\test");

        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file1 = files[i];
                readImageInfo(file1);
        }
    }

    /**
     * 提取照片里面的信息
     *
     * @param file 照片文件
     * @throws ImageProcessingException
     * @throws Exception
     */
    private static void readImageInfo(File file) throws ImageProcessingException, Exception {
        Metadata metadata = ImageMetadataReader.readMetadata(file);

        Double lat = null;
        Double lng = null;
        System.out.println("---打印全部详情---");
        for (Directory directory : metadata.getDirectories()) {

            for (Tag tag : directory.getTags()) {
                System.out.format("[%s] - %s = %s\n",
                        directory.getName(), tag.getTagName(), tag.getDescription());
                if (tag.getTagName().equals("Date/Time Original")) {
                    System.err.println("拍摄时间: " + tag.getDescription());
                } else if (tag.getTagName().equals("GPS Latitude")) {
                    lat = latLng2Decimal(tag.getDescription());
                } else if (tag.getTagName().equals("GPS Longitude")) {
                    lng = latLng2Decimal(tag.getDescription());
                }
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s", error);
                }
            }
        }

        if(null!=lat){
            System.err.println("-经纬度转地址--");
            convertGpsToLoaction(lat, lng);
        }
    }

    /**
     * 经纬度格式  转换为  度分秒格式 ,如果需要的话可以调用该方法进行转换
     *
     * @param point 坐标点
     * @return
     */
    public static String pointToLatlong(String point) {
        Double du = Double.parseDouble(point.substring(0, point.indexOf("°")).trim());
        Double fen = Double.parseDouble(point.substring(point.indexOf("°") + 1, point.indexOf("'")).trim());
        Double miao = Double.parseDouble(point.substring(point.indexOf("'") + 1, point.indexOf("\"")).trim());
        Double duStr = du + fen / 60 + miao / 60 / 60;
        return duStr.toString();
    }

    /***
     * 经纬度坐标格式转换（* °转十进制格式）
     * @param gps
     */
    public static double latLng2Decimal(String gps) {
        String a = gps.split("°")[0].replace(" ", "");
        String b = gps.split("°")[1].split("'")[0].replace(" ", "");
        String c = gps.split("°")[1].split("'")[1].replace(" ", "").replace("\"", "");
        double gps_dou = Double.parseDouble(a) + Double.parseDouble(b) / 60 + Double.parseDouble(c) / 60 / 60;
        return gps_dou;
    }

    /**
     * api_key：注册的百度api的key
     * coords：经纬度坐标
     * http://api.map.baidu.com/reverse_geocoding/v3/?ak="+api_key+"&output=json&coordtype=wgs84ll&location="+coords
     * <p>
     * 经纬度转地址信息
     *
     * @param gps_latitude  维度
     * @param gps_longitude 精度
     */
    private static void convertGpsToLoaction(double gps_latitude, double gps_longitude) throws IOException {
//        String apiKey = "YNxcSCAphFvuPD4LwcgWXwC3SEZZc7Ra";
        String apiKey = "KUoBUmoGjj9STorNaeKnNljqKkkTnzTR";

        String res = "";
        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=" + apiKey + "&output=json&coordtype=wgs84ll&location=" + (gps_latitude + "," + gps_longitude);
        System.err.println("【url】" + url);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            res=response.body().string();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject object = JSONObject.parseObject(res);
        if (object.containsKey("result")) {
            JSONObject result = object.getJSONObject("result");
            if (result.containsKey("addressComponent")) {
                JSONObject address = object.getJSONObject("result").getJSONObject("addressComponent");
                System.err.println("拍摄地点：" + address.get("country")+"拍摄地点：" + address.get("country") + " " + address.get("province") + " " + address.get("city") + " " + address.get("district") + " "
                        + address.get("street") + " " + result.get("formatted_address") + " " + result.get("business"));
            }
        }
    }

}