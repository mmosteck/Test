package com.example.test;

        import java.io.Serializable;
        import java.math.BigInteger;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.List;

        import javax.xml.datatype.XMLGregorianCalendar;


        import org.apache.commons.lang.time.DateUtils;
        import org.joda.time.DateTime;
        import org.joda.time.Interval;

        import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Entity used for purchasable sport tickets, retrieved from FAA via SGSL
 * GetPurchasableTickets
 *
 * @author BAND02
 *
 */
public class SportTicket implements Serializable, Comparable<SportTicket> {
    private static final long serialVersionUID = 1L;

    private EnumSportTickets category;

    private String key;
    private String eventId;
    private String orderNumber;
    private String productId;
    private String title;
    private Date startDate;
    private Date endDate;

    private XMLGregorianCalendar starttime;
    private XMLGregorianCalendar endtime;
    private double price;
    private String bundleDescription;
    private BigInteger bundleWeight;
    private String bundleStickerImage;
    private String bundleImage;
    private String entitlements;

    private List<SportTicketEvent> events;

    public List<SportTicketEvent> getEvents() {
        return events;
    }

    public void setEvents(List<SportTicketEvent> events) {
        this.events = events;
    }

    public EnumSportTickets getCategory() {
        return category;
    }

    public void setCategory(EnumSportTickets category) {
        this.category = category;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public XMLGregorianCalendar getStarttime() {
        return starttime;
    }

    public void setStarttime(XMLGregorianCalendar starttime) {
        this.starttime = starttime;
    }

    public XMLGregorianCalendar getEndtime() {
        return endtime;
    }

    public void setEndtime(XMLGregorianCalendar endtime) {
        this.endtime = endtime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBundleDescription() {
        return bundleDescription;
    }

    public void setBundleDescription(String bundleDescription) {
        this.bundleDescription = bundleDescription;
    }

    public BigInteger getBundleWeight() {
        return bundleWeight;
    }

    public void setBundleWeight(BigInteger bundleWeight) {
        this.bundleWeight = bundleWeight;
    }

    public String getBundleStickerImage() {
        return bundleStickerImage;
    }

    public void setBundleStickerImage(String bundleStickerImage) {
        this.bundleStickerImage = bundleStickerImage;
    }

    public String getBundleImage() {
        return bundleImage;
    }

    public void setBundleImage(String bundleImage) {
        this.bundleImage = bundleImage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @JsonIgnore
    public Interval getInterval() {
        return new Interval(new DateTime(getStartDate()), new DateTime(getEndDate()));
    }

    public String getEntitlements() {
        return entitlements;
    }

    public void setEntitlements(String entitlements) {
        this.entitlements = entitlements;
    }

    public boolean isActive() {
        if (startDate != null || endDate != null) {
            Date now = DateUtils.truncate(Calendar.getInstance(), Calendar.DATE).getTime();
            if ((endDate != null && endDate.compareTo(now) > -1)
                    || (startDate != null && startDate.compareTo(now) < 1)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((starttime == null) ? 0 : starttime.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SportTicket other = (SportTicket) obj;
        if (category != other.category)
            return false;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        if (starttime == null) {
            if (other.starttime != null)
                return false;
        } else if (!starttime.equals(other.starttime))
            return false;
        return true;
    }

    @Override
    public int compareTo(SportTicket checkSportTicket) {
        if (null == checkSportTicket)
            return -1;

        int compare = this.getCategory().compareTo(checkSportTicket.getCategory());
        if (compare != 0)
            return compare;

        compare = this.getStartDate().compareTo(checkSportTicket.getStartDate());
        if (compare != 0)
            return compare;

        compare = this.getEndDate().compareTo(checkSportTicket.getEndDate());
        return compare;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SportTicket [category="
                + category
                + ", key="
                + key
                + ", eventId="
                + eventId
                + ", orderNumber="
                + orderNumber
                + ", productId="
                + productId
                + ", title="
                + title
                + ", startDate="
                + startDate
                + ", endDate="
                + endDate
                + ", starttime="
                + starttime
                + ", endtime="
                + endtime
                + ", price="
                + price
                + "...]";
    }


}