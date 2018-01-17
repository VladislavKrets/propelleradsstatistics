package online.omnia.propellerads;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by lollipop on 13.10.2017.
 */
@Entity
@Table(name = "source_statistics")
public class SourceStatisticsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "spent")
    private double spent;
    @Column(name = "conversions")
    private int conversions;
    @Column(name = "receiver")
    private String receiver;
    @Column(name = "account_id")
    private int account_id;
    @Column(name = "date")
    private Date date;
    @Column(name = "CTR")
    private double ctr;
    @Column(name = "CPC")
    private double cpc;
    @Column(name = "CPM")
    private double cpm;
    @Column(name = "CPI")
    private double cpi;
    @Column(name = "clicks")
    private int clicks;


    public double getCtr() {
        return ctr;
    }

    public void setCtr(double ctr) {
        this.ctr = ctr;
    }

    public double getCpc() {
        return cpc;
    }

    public void setCpc(double cpc) {
        this.cpc = cpc;
    }

    public double getCpm() {
        return cpm;
    }

    public void setCpm(double cpm) {
        this.cpm = cpm;
    }

    public double getCpi() {
        return cpi;
    }

    public void setCpi(double cpi) {
        this.cpi = cpi;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public double getSpent() {
        return spent;
    }

    public void setSpent(double spent) {
        this.spent = spent;
    }

    public int getConversions() {
        return conversions;
    }

    public void setConversions(int conversions) {
        this.conversions = conversions;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SourceStatisticsEntity{" +
                "id=" + id +
                ", spent=" + spent +
                ", conversions=" + conversions +
                ", receiver='" + receiver + '\'' +
                ", account_id=" + account_id +
                ", date=" + date +
                ", ctr=" + ctr +
                ", cpc=" + cpc +
                ", cpm=" + cpm +
                ", cpi=" + cpi +
                ", clicks=" + clicks +
                '}';
    }
}
