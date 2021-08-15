package com.favour.e_libary

class addbook{
    var name = ""
    var language = ""
    var parts = ""
    var price = ""
    var description = ""
    var coverimgurl = ""
    var pdfurl = ""
    var author = ""

    constructor(name: String, language: String, parts: String, price: String, description: String, coverimgurl: String, pdfurl: String, author: String){
        this.name = name
        this.language = language
        this.parts = parts
        this.price = price
        this.description = description
        this.coverimgurl = coverimgurl
        this.pdfurl = pdfurl
        this.author = author
    }
}