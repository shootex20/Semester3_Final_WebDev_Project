/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale.Category;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 813017
 */
@Entity
@Table(name = "items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Items.findAll", query = "SELECT h FROM HomeItems h")
    , @NamedQuery(name = "Items.findByItemID", query = "SELECT h FROM HomeItems h WHERE h.itemID = :itemID")
    , @NamedQuery(name = "Items.findByItemName", query = "SELECT h FROM HomeItems h WHERE h.itemName = :itemName")
    , @NamedQuery(name = "Items.findByPrice", query = "SELECT h FROM HomeItems h WHERE h.price = :price")})
public class HomeItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ItemID")
    private Integer itemID;
    @Basic(optional = false)
    @Column(name = "ItemName")
    private String itemName;
    @Basic(optional = false)
    @Column(name = "Price")
    private double price;
    @JoinColumn(name = "Category", referencedColumnName = "CategoryID")
    @ManyToOne(optional = false)
    private Categories category;
    @JoinColumn(name = "Owner", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Users owner;

    public HomeItems() {
    }

    public HomeItems(Integer itemID) {
        this.itemID = itemID;
    }
        public HomeItems(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }
    public HomeItems(Categories cat, String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
        this.category = cat;
    }

    public HomeItems(Integer itemID, Categories cat, String itemName, double price) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
        this.category = cat;
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemID != null ? itemID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HomeItems)) {
            return false;
        }
        HomeItems other = (HomeItems) object;
        if ((this.itemID == null && other.itemID != null) || (this.itemID != null && !this.itemID.equals(other.itemID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.HomeItems[ itemID=" + itemID + " ]";
    }
    
}
