package com.favour.e_libary

class addbook{
    var name = ""
    var language = ""
    var parts = 0
    var price = 0
    var description = ""
    var coverimgurl = ""
    var pdfurl = ""

    constructor(name: String, language: String, parts: Int, price: Int, description: String, coverimgurl: String, pdfurl: String){
        this.name = name
        this.language = language
        this.parts = parts
        this.price = price
        this.description = description
        this.coverimgurl = coverimgurl
        this.pdfurl = pdfurl
    }
}