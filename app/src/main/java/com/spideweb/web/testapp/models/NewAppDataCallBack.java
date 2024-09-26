package com.spideweb.web.testapp.models;


import java.util.List;

public class NewAppDataCallBack {


    private Integer status;

    private String message;

    private List<Datum> data;

    private List<AppCenter> appCenter;

    private List<Home> home;

    private List<MoreApp> moreApps;

    private NativeAdd nativeAdd;

    private TranslatorAdsId translatorAdsId;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public List<AppCenter> getAppCenter() {
        return appCenter;
    }

    public void setAppCenter(List<AppCenter> appCenter) {
        this.appCenter = appCenter;
    }

    public List<Home> getHome() {
        return home;
    }

    public void setHome(List<Home> home) {
        this.home = home;
    }

    public List<MoreApp> getMoreApps() {
        return moreApps;
    }

    public void setMoreApps(List<MoreApp> moreApps) {
        this.moreApps = moreApps;
    }

    public NativeAdd getNativeAdd() {
        return nativeAdd;
    }

    public void setNativeAdd(NativeAdd nativeAdd) {
        this.nativeAdd = nativeAdd;
    }

    public TranslatorAdsId getTranslatorAdsId() {
        return translatorAdsId;
    }

    public void setTranslatorAdsId(TranslatorAdsId translatorAdsId) {
        this.translatorAdsId = translatorAdsId;
    }
}
