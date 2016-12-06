/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monjurul.home.practice;

import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author Rasel
 */
public class ShareItem {

    static Logger logger = Logger.getLogger(ShareItem.class);
    private String name;
    private double buyPrice;
    private int buyQty;
    private double buyCommission;
    private double sellPrice;
    private double sellCommission;
    private Date buyDate;
    private Date sellDate;

    public double getBuyCommission() {
        return buyCommission;
    }

    public void setBuyCommission(double buyCommission) {
        this.buyCommission = buyCommission;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getBuyQty() {
        return buyQty;
    }

    public void setBuyQty(int buyQty) {
        this.buyQty = buyQty;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ShareItem.logger = logger;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSellCommission() {
        return sellCommission;
    }

    public void setSellCommission(double sellCommission) {
        this.sellCommission = sellCommission;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getTotalBuyPrice() {
        return this.buyPrice + this.buyCommission;
    }
    
    public double getTotalSellPrice(){
        return this.sellPrice +this.sellCommission;
    }
}
