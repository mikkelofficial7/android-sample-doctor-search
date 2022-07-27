package com.android.mobile.alteacaretest.model.detail

class MovieList (
    var id: String,
    var title: String,
    var fullTitle: String,
    var year: String,
    var image: String,

    var rank: String? = null,
    var crew: String? = null,
    var imDbRating: String? = null,
    var imDbRatingCount: String? = null,
    var releaseState: String? = null,
    var contentRating: String? = null,
    var genres: String? = null,
    ) {
    fun rating(): String {
        return if(imDbRating?.isEmpty() == true) "-" else imDbRating.orEmpty()
    }
}