package com.favour.e_libary

class booksItem {
    private lateinit var name : String
    private lateinit var language : String
    private var parts : Int = 0
    private var price : Int = 0
    private lateinit var description : String
    private lateinit var coverimgurl : String
    private lateinit var pdfurl : String
    private lateinit var key: String

    constructor(name: String, language: String, parts: Int, price: Int, description: String, coverimgurl: String, pdfurl: String, key: String){
        this.name = name
        this.language = language
        this.parts = parts
        this.price = price
        this.description = description
        this.coverimgurl = coverimgurl
        this.pdfurl = pdfurl
        this.key = key
    }

    fun getname() : String{
        return name
    }

    fun getlanguage() : String{
        return language
    }

    fun getdescription() : String{
        return description
    }

    fun getcoverimgurl() : String{
        return coverimgurl
    }

    fun getpdfurl() : String{
        return pdfurl
    }

    fun getprice(): Int{
        return price
    }

    fun getparts(): Int{
        return parts
    }
    fun getkey() : String{
        return key
    }
}