package fight.tecmry.com.redlive.Bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Tecmry on 2017/8/25.
 */

public class CountData {
    /**
     * state : 1
     * msg : 成功
     * data : [{"keyword":"10","allindex":918,"mobileindex":0,"so360index":375}]
     */

    private int state;
    private String msg;
    private List<DataBean> data;

    public static CountData objectFromData(String str) {

        return new Gson().fromJson(str, CountData.class);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * keyword : 10
         * allindex : 918
         * mobileindex : 0
         * so360index : 375
         */

        private String keyword;
        private int allindex;
        private int mobileindex;
        private int so360index;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public int getAllindex() {
            return allindex;
        }

        public void setAllindex(int allindex) {
            this.allindex = allindex;
        }

        public int getMobileindex() {
            return mobileindex;
        }

        public void setMobileindex(int mobileindex) {
            this.mobileindex = mobileindex;
        }

        public int getSo360index() {
            return so360index;
        }

        public void setSo360index(int so360index) {
            this.so360index = so360index;
        }
    }
}
