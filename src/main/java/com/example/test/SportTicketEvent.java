package com.example.test;


        import java.math.BigInteger;

        import javax.xml.datatype.XMLGregorianCalendar;

public class SportTicketEvent implements  java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public SportTicketEvent(String key) {
        sportTicketkey = key;
    }

    private String sportTicketkey;

    private BigInteger id;
    private String title;
    private String subtitle;
    private String siText;
    private String packageCode;
    private String picture;
    private String channelName;
    private String channelLogo;
    private XMLGregorianCalendar onAirStarttime;
    private XMLGregorianCalendar onAirEndtime;
    private BigInteger duration;


    public String getSportTicketkey() {
        return sportTicketkey;
    }
    public void setSportTicketkey(String sportTicketkey) {
        this.sportTicketkey = sportTicketkey;
    }
    public BigInteger getId() {
        return id;
    }
    public void setId(BigInteger id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    public String getSiText() {
        return siText;
    }
    public void setSiText(String siText) {
        this.siText = siText;
    }
    public String getPackageCode() {
        return packageCode;
    }
    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getChannelName() {
        return channelName;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    public String getChannelLogo() {
        return channelLogo;
    }
    public void setChannelLogo(String channelLogo) {
        this.channelLogo = channelLogo;
    }
    public XMLGregorianCalendar getOnAirStarttime() {
        return onAirStarttime;
    }
    public void setOnAirStarttime(XMLGregorianCalendar onAirStarttime) {
        this.onAirStarttime = onAirStarttime;
    }
    public XMLGregorianCalendar getOnAirEndtime() {
        return onAirEndtime;
    }
    public void setOnAirEndtime(XMLGregorianCalendar onAirEndtime) {
        this.onAirEndtime = onAirEndtime;
    }
    public BigInteger getDuration() {
        return duration;
    }
    public void setDuration(BigInteger duration) {
        this.duration = duration;
    }

}