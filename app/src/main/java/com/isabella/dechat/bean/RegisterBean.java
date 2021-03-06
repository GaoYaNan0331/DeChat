package com.isabella.dechat.bean;

import java.io.Serializable;

/**
 * description
 * Created by 张芸艳 on 2017/6/1.
 */

public class RegisterBean {


    /**
     * result_message : success
     * result_code : 200
     * data : {"createtime":1497528266897,"phone":"121111113","area":"1212","nickname":"12121231","userId":3,"introduce":"1212","gender":"男","password":"456"}
     */

    
    private String result_message;
    private int result_code;
    private DataBean data;

    public String getResult_message() {
        return result_message;
    }

    public void setResult_message(String result_message) {
        this.result_message = result_message;
    }

    public int getResult_code() {
        return result_code;
    }

    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RegisterBean{" +
                "result_message='" + result_message + '\'' +
                ", result_code=" + result_code +
                ", data=" + data +
                '}';
    }

    public static class DataBean implements Serializable{
        /**
         * createtime : 1497528266897
         * phone : 121111113
         * area : 1212
         * nickname : 12121231
         * userId : 3
         * introduce : 1212
         * gender : 男
         * password : 456
         */

        private long createtime;
        private String phone;
        private String area;
        private String nickname;
        private int userId;
        private String introduce;
        private String gender;
        private String password;
        private String age;
        private double lat;
        private double lng;
        private  String yxpassword;

        public String getYxpassword() {
            return yxpassword;
        }

        public void setYxpassword(String yxpassword) {
            this.yxpassword = yxpassword;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "UserInfoDataBean{" +
                    "createtime=" + createtime +
                    ", phone='" + phone + '\'' +
                    ", area='" + area + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", userId=" + userId +
                    ", introduce='" + introduce + '\'' +
                    ", gender='" + gender + '\'' +
                    ", password='" + password + '\'' +
                    ", age='" + age + '\'' +
                    ", lat=" + lat +
                    ", lng=" + lng +
                    '}';
        }
    }
}
