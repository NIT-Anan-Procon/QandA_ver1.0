package com.example.haruka.rescue_aid.utils;

import android.graphics.drawable.Drawable;

/**
 * Created by Tomoya on 9/15/2017 AD.
 * This class represents each care
 * It has the information of index, name, xml file, description
 */

public class Care {

    public int index;
    public String name;
    public String xml;
    public String description;
    public String buttonText;
    public static final String NULL_XML = "";
    public Drawable drawable;

    public Care(int index, String name, String xml){
        this.index = index;
        this.name = name;
        this.xml = xml;
        this.description = "";
    }

    public void setDescription(String text){
        description = text;
    }


    public void setButtonText(String text){
        buttonText = text;
    }

    public String getXml(){
        if (xml.equals(NULL_XML)){
            return null;
        } else {
            return xml;
        }
    }

    public void setDrawable(Drawable drawable){
        this.drawable = drawable;
    }

    public Drawable getDrawable(){
        return drawable;
    }
}
