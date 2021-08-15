package com.favour.e_libary

class booksItem {
    private lateinit var name : String
    private lateinit var language : String
    private lateinit var parts : String
    private lateinit var price : String
    private lateinit var description : String
    private lateinit var coverimgurl : String
    private lateinit var pdfurl : String
    private lateinit var key: String
    private lateinit var author : String

    constructor(name: String, language: String, parts: String, price: String, description: String, coverimgurl: String, pdfurl: String, key: String, author: String){
        this.name = name
        this.language = language
        this.parts = parts
        this.price = price
        this.description = description
        this.coverimgurl = coverimgurl
        this.pdfurl = pdfurl
        this.key = key
        this.author = author
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

    fun getprice(): String{
        return price
    }

    fun getparts(): String{
        return parts
    }
    fun getkey() : String{
        return key
    }
    fun getauthor() : String{
        return author
    }
}