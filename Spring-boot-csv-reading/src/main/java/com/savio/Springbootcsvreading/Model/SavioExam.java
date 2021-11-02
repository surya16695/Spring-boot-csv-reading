package com.savio.Springbootcsvreading.Model;

import javax.persistence.*;

@Entity
@Table(name = "savio_exam")
public class SavioExam {

	@Id
	@Column(name = "unique_id")
	private String id;

	@Column(name = "c_name")
	private String nameOfTab;

	@Column(name = "batch_no")
	private String batchNum;

	@Column(name = "expiry_Date")
	private String dateOfExpiry ;

	@Column(name = "balance_qty")
	private int balanceOfTabs;

	@Column(name = "packaging")
	private String sizeOfPackage;

	@Column(name = "schemes")
	private String schemes;

	@Column(name = "m.r.p")
	private float priceOfMedicine;
    
    @Column(name = "manufacturer")
	private String manufacturedBy;

	@Column(name = "hsn_code")
	private int hsnNum; 

    public SavioExam() {

    }

    public SavioExam(String id, String nameOfTab, String batchNum, String dateOfExpiry,
                         int balanceOfTabs, String sizeOfPackage, String schemes, float priceOfMedicine, String manufacturedBy, int hsnNum) {
        this.id = id;
        this.nameOfTab = nameOfTab;
        this.batchNum = batchNum;
        this.dateOfExpiry = dateOfExpiry;
        this.balanceOfTabs = balanceOfTabs;
        this.sizeOfPackage = sizeOfPackage;
        this.schemes = schemes;
        this.priceOfMedicine = priceOfMedicine;
        this.manufacturedBy = manufacturedBy;
        this.hsnNum = hsnNum;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameOfTab() {
        return this.nameOfTab;
    }

    public void setNameOfTab(String nameOfTab) {
        this.nameOfTab = nameOfTab;
    }

    public String getBatchNum() {
        return this.batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public String getDateOfExpiry() {
        return this.dateOfExpiry;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public int getBalanceOfTabs() {
        return this.balanceOfTabs;
    }

    public void setBalanceOfTabs(int balanceOfTabs) {
        this.balanceOfTabs = balanceOfTabs;
    }

    public String getSizeOfPackage() {
        return this.sizeOfPackage;
    }

    public void setSizeOfPackage(String sizeOfPackage) {
        this.sizeOfPackage = sizeOfPackage;
    }

    public String getSchemes() {
        return this.schemes;
    }

    public void setSchemes(String schemes) {
        this.schemes = schemes;
    }

    public float getPriceOfMedicine() {
        return this.priceOfMedicine;
    }

    public void setPriceOfMedicine(float priceOfMedicine) {
        this.priceOfMedicine = priceOfMedicine;
    }

    public String getManufacturedBy() {
        return this.manufacturedBy;
    }

    public void setManufacturedBy(String manufacturedBy) {
        this.manufacturedBy = manufacturedBy;
    }

    public int getHsnNum() {
        return this.hsnNum;
    }

    public void setHsnNum(int hsnNum) {
        this.hsnNum = hsnNum;
    }

    //todo: add all the elements in the tostring()
    @Override
	  public String toString() {
	    return " [id=" + id + ", name=" + nameOfTab + ", batchNum=" + batchNum + ", ExpiryDAte=" + dateOfExpiry + ", balanceOfTabs=" + balanceOfTabs+"]";
	  }
	}