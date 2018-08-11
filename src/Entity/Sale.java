package Entity;

import java.util.Date;
import java.math.BigDecimal;

public class Sale {

    //sale attributes
    private int billno;
    private java.sql.Date date;
    private String shopname;
    private java.sql.Date nextdate;
    private Integer onekgQty;
    private Integer twokgQty;
    private Integer fourkgQty;
    private Integer fivekgQty;
    private Integer sixkgQty;
    private Integer ninekgQty;
    private Integer ci = new Integer("0");
    private BigDecimal onekgAmount;
    private BigDecimal twokgAmount;
    private BigDecimal fourkgAmount;
    private BigDecimal fivekgAmount;
    private BigDecimal sixkgAmount;
    private BigDecimal ninekgAmount;
    private BigDecimal amount;
    private BigDecimal total;
    private BigDecimal cm = new BigDecimal("0.00");

    //basic starters and getters
    public int getBillno() {
        return billno;
    }

    public void setBillno(int billno) {
        this.billno = billno;
    }

    public Date getDate() {
        return date;
    }

    public void setStartdate(java.sql.Date date) {
        this.date = date;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public Date getNextdate() {
        return nextdate;
    }

    public void setNextdate(java.sql.Date nextdate) {
        this.nextdate = nextdate;
    }

    public Integer getOnekgqty() {
        return onekgQty;
    }

    public void setOnekgqty(Integer onekgQty) {
        if (onekgQty.compareTo(ci) == 0) {
            onekgQty = null;
        } else {
            this.onekgQty = onekgQty;
        }
    }

    public Integer getTwokgqty() {
        return twokgQty;
    }

    public void setTwokgqty(Integer twokgQty) {
        if (twokgQty.compareTo(ci) == 0) {
            twokgQty = null;
        } else {
            this.twokgQty = twokgQty;
        }
    }

    public Integer getFourkgqty() {
        return fourkgQty;
    }

    public void setFourkgqty(Integer fourkgQty) {
        if (fourkgQty.compareTo(ci) == 0) {
            fourkgQty = null;
        } else {
            this.fourkgQty = fourkgQty;
        }
    }

    public Integer getFivekgqty() {
        return fivekgQty;
    }

    public void setFivekgqty(Integer fivekgQty) {
        if (fivekgQty.compareTo(ci) == 0) {
            fivekgQty = null;
        } else {
            this.fivekgQty = fivekgQty;
        }
    }

    public Integer getSixkgqty() {
        return sixkgQty;
    }

    public void setSixkgqty(Integer sixkgQty) {
        if (sixkgQty.compareTo(ci) == 0) {
            sixkgQty = null;
        } else {
            this.sixkgQty = sixkgQty;
        }
    }

    public Integer getNinekgqty() {
        return ninekgQty;
    }

    public void setNinekgqty(Integer ninekgQty) {
        if (ninekgQty.compareTo(ci) == 0) {
            ninekgQty = null;
        } else {
            this.ninekgQty = ninekgQty;
        }
    }

    public BigDecimal getOnekgamount() {
        return onekgAmount;
    }

    public void setOnekgamount(BigDecimal onekgAmount) {
        if ((onekgAmount.compareTo(cm)) == 0) {
            this.onekgAmount = null;
        } else {

            this.onekgAmount = onekgAmount;
        }
    }

    public BigDecimal getTwokgamount() {
        return twokgAmount;
    }

    public void setTwokgamount(BigDecimal twokgAmount) {
        if ((twokgAmount.compareTo(cm)) == 0) {
            this.twokgAmount = null;
        } else {
            this.twokgAmount = twokgAmount;
        }
    }

    public BigDecimal getFourkgamount() {
        return fourkgAmount;
    }

    public void setFourkgamount(BigDecimal fourkgAmount) {
        if ((fourkgAmount.compareTo(cm)) == 0) {
            this.fourkgAmount = null;
        } else {
            this.fourkgAmount = fourkgAmount;
        }
    }

    public BigDecimal getFivekgamount() {
        return fivekgAmount;
    }

    public void setFivekgamount(BigDecimal fivekgAmount) {
        if ((fivekgAmount.compareTo(cm)) == 0) {
            this.fivekgAmount = null;
        } else {
            this.fivekgAmount = fivekgAmount;
        }
    }

    public BigDecimal getSixkgamount() {
        return sixkgAmount;
    }

    public void setSixkgamount(BigDecimal sixkgAmount) {
        if ((sixkgAmount.compareTo(cm)) == 0) {
            this.sixkgAmount = null;
        } else {
            this.sixkgAmount = sixkgAmount;
        }
    }

    public BigDecimal getNinekgamount() {
        return ninekgAmount;
    }

    public void setNinekgamount(BigDecimal ninekgAmount) {
        if ((ninekgAmount.compareTo(cm)) == 0 || ninekgAmount == null) {
            this.ninekgAmount = null;
        } else {
            this.ninekgAmount = ninekgAmount;
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
