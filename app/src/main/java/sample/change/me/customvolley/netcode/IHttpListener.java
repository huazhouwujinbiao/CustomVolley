package sample.change.me.customvolley.netcode;

import java.io.InputStream;

/**
 * Created by Administrator on 2017/4/27.
 */

public interface IHttpListener {
    void onSuccess(InputStream inputStream);
    void onError();
}
