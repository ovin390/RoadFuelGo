package com.example.roadfuelgo;

public class bookb {
    String bunkname,id,custname,phone,fueltype,quantity,location,bdate;

    public bookb() {
    }

    public bookb(String bunkname, String id, String custname, String phone, String fueltype, String quantity, String location,String bdate) {
        this.bunkname = bunkname;
        this.id = id;
        this.custname = custname;
        this.phone = phone;
        this.fueltype = fueltype;
        this.quantity = quantity;
        this.location = location;
        this.bdate=bdate;
    }

    public String getBunkname() {
        return bunkname;
    }

    public void setBunkname(String bunkname) {
        this.bunkname = bunkname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }
}
