package com.example.a27459.ttime.Bean;

import java.util.List;

/**
 * Created by 27459 on 2017/1/22.
 */

public class Goods {


    /**
     * errcode : 000
     * errmsg :
     * result : [{"aid":"208","title":"复古蓝针瑞士 MIDO 美度贝伦赛丽典藏40周年纪念款","pro_img":"http://api.ttime.com/attachment/images/b44058a7977008c6bae9109620e7ba4c.jpg","c_name":"淘宝","c_price":" ¥5680.00","time":"47天前","rate_praise":"100%"},{"aid":"206","title":"飞亚达时尚镂空腕表","pro_img":"http://api.ttime.com/attachment/images/8396fb6c76b71cfedc043c9726276879.jpg","c_name":"京东","c_price":"￥3789.00","time":"51天前","rate_praise":"86%"},{"aid":"205","title":"商务优雅，尽在罗西尼","pro_img":"http://api.ttime.com/attachment/images/6544507acee5a3d7255dd63c8132f017.png","c_name":"京东","c_price":"￥398.00","time":"53天前","rate_praise":"89%"},{"aid":"204","title":"诠释经典与时尚，西铁城腕表","pro_img":"http://api.ttime.com/attachment/images/6d7652e5c421fcf47bd59ad329b8c538.png","c_name":"京东","c_price":"￥1320.00","time":"55天前","rate_praise":"87%"},{"aid":"203","title":"简约商务范 有它就够了","pro_img":"http://api.ttime.com/attachment/images/b34b54a1ff66bbf8e5acdee2fb70d527.png","c_name":"京东","c_price":"￥1180.00","time":"58天前","rate_praise":"86%"},{"aid":"202","title":"内外兼修，多功能卡西欧","pro_img":"http://api.ttime.com/attachment/images/851eec71adeec88c964ec2c49bec6ba9.png","c_name":"京东","c_price":"￥4496.00","time":"60天前","rate_praise":"86%"},{"aid":"201","title":"五千以内机械表，首选美度","pro_img":"http://api.ttime.com/attachment/images/c1e2f29395d82053bac75d578e38e121.png","c_name":"京东","c_price":"￥4799.00","time":"62天前","rate_praise":"86%"},{"aid":"200","title":"女士首选 简约KC","pro_img":"http://api.ttime.com/attachment/images/4957b07428956686f537a6edba9e3b6a.png","c_name":"京东","c_price":"￥1199.00","time":"65天前","rate_praise":"86%"},{"aid":"199","title":"千元好表，海鸥多功能","pro_img":"http://api.ttime.com/attachment/images/c059f72ac6b573e815d4fcd37d55e7b5.png","c_name":"京东","c_price":"￥998.00","time":"67天前","rate_praise":"90%"},{"aid":"198","title":"仅售万元，浪琴名匠系列","pro_img":"http://api.ttime.com/attachment/images/577bb399b41c3d07fd7a5c6264aa0c80.png","c_name":"京东","c_price":"￥10299.00","time":"69天前","rate_praise":"90%"}]
     */

    private String errcode;
    private String errmsg;
    private List<ResultBean> result;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * aid : 208
         * title : 复古蓝针瑞士 MIDO 美度贝伦赛丽典藏40周年纪念款
         * pro_img : http://api.ttime.com/attachment/images/b44058a7977008c6bae9109620e7ba4c.jpg
         * c_name : 淘宝
         * c_price :  ¥5680.00
         * time : 47天前
         * rate_praise : 100%
         */

        private String aid;
        private String title;
        private String pro_img;
        private String c_name;
        private String c_price;
        private String time;
        private String rate_praise;

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPro_img() {
            return pro_img;
        }

        public void setPro_img(String pro_img) {
            this.pro_img = pro_img;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getC_price() {
            return c_price;
        }

        public void setC_price(String c_price) {
            this.c_price = c_price;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getRate_praise() {
            return rate_praise;
        }

        public void setRate_praise(String rate_praise) {
            this.rate_praise = rate_praise;
        }
    }
}
