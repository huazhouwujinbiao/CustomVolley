package sample.change.me.customvolley.netcode;

/**
 * Created by Administrator on 2017/4/27.
 */

public interface IHttpService {
    void setUrl(String url);
    void excute();
    void setHttpCallback(IHttpListener httpListener);
    void setResultDate(byte[] resultDate);
}
