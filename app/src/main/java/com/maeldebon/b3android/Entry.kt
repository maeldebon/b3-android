package com.maeldebon.b3android

class Entry {
    var id : Int = 0
    var euid : Int = 0
    var nom : String = ""
    var image : String = ""
    var prixjournalierbase : Int = 0
    var categorieco2 : String = ""

    constructor(euid:Int,nom:String,image:String,prixjournalierbase:Int,categorieco2:String) {
        this.euid = euid
        this.nom = nom
        this.image = image
        this.prixjournalierbase = prixjournalierbase
        this.categorieco2 = categorieco2

    }

    constructor() {
    }
}