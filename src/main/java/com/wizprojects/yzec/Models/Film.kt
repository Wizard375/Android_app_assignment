package com.wizprojects.yzec.Models

class Film {
    var filmUrl:String=""
    var caption:String=""
    var profileLink:String?=null
    constructor()

    constructor(filmUrl: String, caption: String) {
        this.filmUrl = filmUrl
        this.caption = caption
    }

    constructor(filmUrl: String, caption: String, profileLink: String) {
        this.filmUrl = filmUrl
        this.caption = caption
        this.profileLink = profileLink
    }


}