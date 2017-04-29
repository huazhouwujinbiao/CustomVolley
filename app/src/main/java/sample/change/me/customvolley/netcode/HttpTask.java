package sample.change.me.customvolley.netcode;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/28.
 */

public class HttpTask<T> implements Runnable {
    private IHttpService httpService;
    public <T extends Serializable> HttpTask(T requestInfo,String url,IHttpListener httpListener){
        this.httpService=new JSONHttpService();
        httpService.setUrl(url);
        httpService.setHttpCallback(httpListener);
        httpService.setResultDate(JSON.toJSONString(requestInfo).getBytes());
    }
    @Override
    public void run() {
        this.httpService.excute();
    }
}
