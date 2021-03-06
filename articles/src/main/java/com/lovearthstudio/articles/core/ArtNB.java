package com.lovearthstudio.articles.core;

import android.util.Log;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.lovearthstudio.articles.constant.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zhaoliang on 16/4/6.
 */
public class ArtNB {
    /*
    * 封装网络部分
    */
    private OkHttpClient httpClient;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public ArtNB() {
        httpClient = new OkHttpClient();
    }

    /**
     * 根据setArtParams指定的参数，发送网络请求，并在response时回掉myCallBack
     * 回调接口返回如下
     * 1： OnFailure(JSONObject(1=网络通讯错误,"错误原因"));
     * 3： OnFailure(JSONObject(2=数据格式错误,"错误原因"));
     * 2： OnFailure(JSONObject(>100000=服务逻辑错误,"错误原因"));
     *
     *
     * 4： OnResponse(jsonResponse)//成功返回
     * @param setArtParams 在Articles.java中设置
     * @param myCallBack 回掉接口
     */
    public void setArticle(SetArtParams setArtParams,final  MyCallBack myCallBack) {

        Log.i("setArticle","begin");
        String requestParams = com.alibaba.fastjson.JSON.toJSONString(setArtParams);
        System.out.println("---------request:" + requestParams);
        //{"action":"get_articles","channel":"Recommend","dua_id":0,"fields":["inc","star","comt","content","good","bad","shar"],"filter":{"inc[>]":0},"order":"inc DESC","rows":20}
        RequestBody body = RequestBody.create(JSON, requestParams);
        Request request = new Request.Builder().url(Constant.baseUrl).post(body).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                myCallBack.onFailure(Constant.failureObject(Constant.NETWORK_FAILURE,e.toString()));
            }
            @Override
            public void onResponse(Call call, Response response) throws  IOException{
                try {
                    //下面解析如果解析错了，就会进入try catch 里面，所以不要专门为它判断null
                    JSONObject jsonResponse = new JSONObject(response.body().string());
                    if (jsonResponse.optInt("status") !=  0) {
                        myCallBack.onFailure(Constant.failureObject(Constant.SERVER_FAILURE,"Fail with error code:"+ jsonResponse.optInt("status")));
                    }else{
                        myCallBack.onResponse(jsonResponse);
                    }
                } catch (JSONException e) {
                    myCallBack.onFailure(Constant.failureObject(Constant.JSON_FAILURE,"JSONExecption:" + e.toString()));
                }catch (IOException e){
                    myCallBack.onFailure(Constant.failureObject(Constant.SERVER_FAILURE,"IOExecption: "+e.toString()));
                }catch (Exception e){
                    myCallBack.onFailure(Constant.failureObject(Constant.SERVER_FAILURE,"Execption: "+e.toString()));
                }
            }
        });
    }

    public void addArticle(AddArtParams addArtParams,final  MyCallBack myCallBack) {
        Log.i("addArticle","begin");
        String requestParams = com.alibaba.fastjson.JSON.toJSONString(addArtParams);
        System.out.println("---------request:" + requestParams);
        //{"action":"get_articles","channel":"Recommend","dua_id":0,"fields":["inc","star","comt","content","good","bad","shar"],"filter":{"inc[>]":0},"order":"inc DESC","rows":20}
        RequestBody body = RequestBody.create(JSON, requestParams);
        Request request = new Request.Builder().url(Constant.baseUrl).post(body).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                myCallBack.onFailure(Constant.failureObject(Constant.NETWORK_FAILURE,e.toString()));
            }
            @Override
            public void onResponse(Call call, Response response) throws  IOException{
                try {
                    //下面解析如果解析错了，就会进入try catch 里面，所以不要专门为它判断null
                    JSONObject jsonResponse = new JSONObject(response.body().string());
                    if (jsonResponse.optInt("status") !=  0) {
                        myCallBack.onFailure(Constant.failureObject(Constant.SERVER_FAILURE,"Fail with error code:"+ jsonResponse.optInt("status")));
                    }else{
                        myCallBack.onResponse(jsonResponse);
                    }
                } catch (JSONException e) {
                    myCallBack.onFailure(Constant.failureObject(Constant.JSON_FAILURE,"JSONExecption:" + e.toString()));
                }catch (IOException e){
                    myCallBack.onFailure(Constant.failureObject(Constant.SERVER_FAILURE,"IOExecption: "+e.toString()));
                }catch (Exception e){
                    myCallBack.onFailure(Constant.failureObject(Constant.SERVER_FAILURE,"Execption: "+e.toString()));
                }
            }
        });
    }
    /**
     * 根据setArtParams指定的参数，发送网络请求，并在response时回掉myCallBack
     * 回调接口返回如下
     * 1： OnFailure(JSONObject(1=网络通讯错误,"错误原因"));
     * 3： OnFailure(JSONObject(2=数据格式错误,"错误原因"));
     * 2： OnFailure(JSONObject(>100000=服务逻辑错误,"错误原因"));
     *
     * 4： OnResponse(jsonResponse)//成功返回
     * @param getArtParams
     * @param myCallBack
     */
    public void getArticles(GetArtParams getArtParams, final MyCallBack myCallBack) {
        String requestParams = com.alibaba.fastjson.JSON.toJSONString(getArtParams);
        Log.i("Articles-ArtNB","getArticles "+requestParams);
        //System.out.println("---------request:" + requestParams);
        //{"action":"get_articles","channel":"Recommend","dua_id":0,"fields":["inc","star","comt","content","good","bad","shar"],"filter":{"inc[>]":0},"order":"inc DESC","rows":20}
        RequestBody body = RequestBody.create(JSON, requestParams);
        Request request = new Request.Builder().url(Constant.baseUrl).post(body).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //IOException e是由于网络原因引起的异常
                myCallBack.onFailure(Constant.failureObject(Constant.NETWORK_FAILURE,e.toString()));
            }
            @Override
            public void onResponse(Call call, Response response) throws  IOException{
                try {
                    String response_body_string = response.body().string();
                    Log.i("ArtNB",response_body_string);
                    //FIXME: 这个地方，如果出错了，那么就是服务器数据链路出错或者服务器返回500等错误
                    //FIXME: 这个地方，有可能是网管服务器给的错误，比如，计算所。返回：﻿﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"开头的计算所无线网络portal认证页面
                    //fixme: 这种情况下，jsonResponse不确定是谁，直接跳到catch JSONException这个语句.
                    JSONObject jsonResponse = new JSONObject(response_body_string);
                    if (jsonResponse.optInt("status") !=  0) {
                        myCallBack.onFailure(jsonResponse);
                    }else{
                        myCallBack.onResponse(jsonResponse);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    myCallBack.onFailure(Constant.failureObject(Constant.JSON_FAILURE,"JSONExecption: "+e.toString()));
                } catch (IOException e){
                    e.printStackTrace();
                    /**
                     * FIXME: fail status: 4 IOExecption: java.net.SocketTimeoutException
                     * 如果断网，那么回出现上面这个错误，那么应该是网络错误
                     * */
                    myCallBack.onFailure(Constant.failureObject(Constant.NETWORK_FAILURE,"IOExecption: "+e.toString()));
                }catch (Exception e){
                    e.printStackTrace();
                    myCallBack.onFailure(Constant.failureObject(Constant.NETWORK_FAILURE,"Execption: "+e.toString()));
                }
            }
        });
    }


    public void getArticle(GetArtParams getArtParams, final MyCallBack myCallBack) {
        String requestParams = com.alibaba.fastjson.JSON.toJSONString(getArtParams);
        Log.i("Articles-ArtNB","getArticles "+requestParams);
        HttpParams params = new HttpParams();
        params.putJsonParams(requestParams);
        HttpCallback callBack = new HttpCallback(){
            @Override
            public void onSuccess(String t) {
                try {
                    String response_body_string = t;
                    Log.i("RxVolley",response_body_string);
                    JSONObject jsonResponse = new JSONObject(response_body_string);
                    if (jsonResponse.optInt("status") !=  0) {
                        myCallBack.onFailure(jsonResponse);
                    }else{
                        myCallBack.onResponse(jsonResponse);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    myCallBack.onFailure(Constant.failureObject(Constant.SERVER_FAILURE,"JSONExecption: "+e.toString()));
                } catch (Exception e){
                    e.printStackTrace();
                    myCallBack.onFailure(Constant.failureObject(Constant.SERVER_FAILURE,"Execption: "+e.toString()));
                }
            }
            @Override
            public void onFailure(int errorNo, String strMsg) {
                myCallBack.onFailure(Constant.failureObject(Constant.SERVER_FAILURE,errorNo+" "+strMsg));
            }
        };
        new RxVolley.Builder()
                .url(Constant.baseUrl)
                .httpMethod(RxVolley.Method.POST) //default GET or POST/PUT/DELETE/HEAD/OPTIONS/TRACE/PATCH
                .cacheTime(6) //default: get 5min, post 0min
                .params(params)
                .contentType(RxVolley.ContentType.JSON)
                .shouldCache(true) //default: get true, post false
                .callback(callBack)
                .encoding("UTF-8") //default
                .doTask();
    }




}




