package com.favour.e_libary

class addpayed {
    var bookid = ""
    var userid = ""
    var name = ""
    var language = ""
    var parts = ""
    var price = ""
    var description = ""
    var coverimgurl = ""
    var pdfurl = ""
    var author = ""
    constructor(bookid : String, userid: String, name: String, language: String, parts: String, price: String, description: String, coverimgurl: String, pdfurl: String, author: String){
        this.bookid = bookid
        this.userid = userid
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