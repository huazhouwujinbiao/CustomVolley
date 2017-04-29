package sample.change.me.customvolley.netcode;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/4/27.
 */

public class JsonDealListener<M> implements IHttpListener{
    private Class<M> responseClass;
    private IJsonListener<M> jsonListener;
    Handler handler=new Handler(Looper.getMainLooper());

    public JsonDealListener(Class<M> responseClass, IJsonListener<M> jsonListener) {
        this.responseClass = responseClass;
        this.jsonListener = jsonListener;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        String content =getContent(inputStream);
        //使用fastJson解析数据 M 响应类
        final M  response= JSON.parseObject(content,responseClass);
        //切换到主线程
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(jsonListener!=null){
                    jsonListener.onSuccess(response);
                }
            }
        });
    }

    /**
     * 解析数据流
     * @param inputStream
     * @return
     */
    private String getContent(InputStream inputStream) {
        String content="";
        BufferedReader  bufferdReader=new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb =new StringBuilder();
        String line=null;
        try {
            while((line=bufferdReader.readLine())!=null){
                sb.append(line+"\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    @Override
    public void onError() {

    }
}
