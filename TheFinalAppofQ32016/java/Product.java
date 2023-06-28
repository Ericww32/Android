package com.eric.thefinalappofq32016;

/**
 * Created by Eric on 7/28/16.
 */
public class Product {
    private int _id;
    private String _productName;
    private String _description;

    public Product() {
    }

    public Product(String _productName, String _description) {
        this._productName = _productName;
        this._description = _description;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_productName(String _productName) {
        this._productName = _productName;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public int get_id() {
        return _id;
    }

    public String get_productName() {
        return _productName;
    }

    public String get_description() {
        return _description;
    }
}