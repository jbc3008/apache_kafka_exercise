package jbc.practica.users.model;

public class User {

    private Long registertime;
    private String userid;
    private String regionid;
    private String gender;

    public User() {}

    public User(Long registertime, String userid, String regionid, String gender) {
        this.registertime = registertime;
        this.userid = userid;
        this.regionid = regionid;
        this.gender = gender;
    }

    public Long getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Long registertime) {
        this.registertime = registertime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
