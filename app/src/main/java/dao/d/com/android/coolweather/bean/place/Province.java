package dao.d.com.android.coolweather.bean.place;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Province implements Serializable {
    @SerializedName("name")
    private String provinceName;
    @SerializedName("id")
    private String provinceCode;
    private transient int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
