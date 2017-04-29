package sample.change.me.customvolley.netcode;

/**
 * Created by Administrator on 2017/4/27.
 */

/**
 *
 * @param <M> 泛型 响应类
 */
public interface IJsonListener<M> {
    void onSuccess(M m);
    void onError();
}
