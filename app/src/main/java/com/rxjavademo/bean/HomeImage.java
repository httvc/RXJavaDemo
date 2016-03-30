package com.rxjavademo.bean;

import java.util.List;

/**
 * Created by Android Studio
 * Project：MatchLayout
 * Author：Jiafujie
 * Email：jfjie2013@163.com
 * Date：2016/1/25.
 */
public class HomeImage {

    /**
     * id : 0
     * icon : http://www.langtianhealth.com:20080/v2/image/ef76a2cd96a84ba5a557b7f32d565033
     * text : 健康监测
     * label : health
     * bgcolor : #3ed56c
     * flag : 0
     * mask : 0
     */

    private List<DataListEntity> data_list;

    public void setData_list(List<DataListEntity> data_list) {
        this.data_list = data_list;
    }

    public List<DataListEntity> getData_list() {
        return data_list;
    }

    public static class DataListEntity {
        private int id;
        private String icon;
        private String text;
        private String label;
        private String bgcolor;
        private int flag;
        private int mask;

        public void setId(int id) {
            this.id = id;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public void setBgcolor(String bgcolor) {
            this.bgcolor = bgcolor;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public void setMask(int mask) {
            this.mask = mask;
        }

        public int getId() {
            return id;
        }

        public String getIcon() {
            return icon;
        }

        public String getText() {
            return text;
        }

        public String getLabel() {
            return label;
        }

        public String getBgcolor() {
            return bgcolor;
        }

        public int getFlag() {
            return flag;
        }

        public int getMask() {
            return mask;
        }
    }
}
