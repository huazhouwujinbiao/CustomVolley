package sample.change.me.customvolley.netcode;

/**
 * Created by Administrator on 2017/4/27.
 */

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 具体实现请求处理，JSON
 */
public class JSONHttpService implements IHttpService{
    String url=null;
    //回调结果的接口
    IHttpListener httpListener;
    byte[] resultDate;
    @Override
    public void setUrl(String url) {
        this.url=url;
    }
    @Override
    public void setResultDate(byte[] resultDate) {
        this.resultDate=resultDate;
    }
    @Override
    public void excute() {
        try {
            URL httpUrl=new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            //一旦设置  conn.setDoOutput(true);就代表着该请求是用POST方式发送请求，不管conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
//            conn.setRequestMethod("GET");
            // 设定传送的内容类型是可序列化的java对象
            // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
            conn.setRequestProperty("Content-type", "application/x-java-serialized-object");
            conn.connect();
            ObjectOutputStream objectOutputStream =new ObjectOutputStream(conn.getOutputStream());
            objectOutputStream.write(resultDate);
            objectOutputStream.flush();
            objectOutputStream.close();
            InputStream input =conn.getInputStream();
            httpListener.onSuccess(input);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setHttpCallback(IHttpListener httpListener) {
        this.httpListener=httpListener;
    }
}
