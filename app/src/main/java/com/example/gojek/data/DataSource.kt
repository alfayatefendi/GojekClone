package com.example.gojek.data

import com.example.gojek.R
import com.example.gojek.domain.model.News
import com.example.gojek.domain.model.Promo

object DataSource {
    private val imgNews = intArrayOf(
        R.drawable.img_news_1,
        R.drawable.img_news_2,
        R.drawable.img_news_3,
    )
    private val titleNews = intArrayOf(
        R.string.title_news,
        R.string.title_news,
        R.string.title_news,
    )
    private val descriptionNews = intArrayOf(
        R.string.description_news,
        R.string.description_news,
        R.string.description_news,
    )
    fun getDataNews(): List<News> {
        val data: ArrayList<News> = arrayListOf()
        for (i in imgNews.indices) {
            val news = News (
                imgNews[i],
                titleNews[i],
                descriptionNews[i]
            )
            data.add(news)
        }
        return data
    }
    private val imgPromo = intArrayOf(
        R.drawable.promo_1,
        R.drawable.promo_2,
        R.drawable.promo_3,
        R.drawable.promo_4,
    )
    private val titlePromo = intArrayOf(
        R.string.title_promo_1,
        R.string.title_promo_2,
        R.string.title_promo_3,
        R.string.title_promo_4,
    )
    private val distancePromo = intArrayOf(
        R.string.title_distance,
        R.string.title_distance,
        R.string.title_distance,
        R.string.title_distance,
    )
    private val ratingPromo = intArrayOf(
        R.string.title_rating,
        R.string.title_rating,
        R.string.title_rating,
        R.string.title_rating,
    )
    fun getDataPromo(): List<Promo> {
        val data: ArrayList<Promo> = arrayListOf()
        for (i in imgPromo.indices){
            val promo = Promo(
                imgPromo[i],
                titlePromo[i],
                distancePromo[i],
                ratingPromo[i]
            )
            data.add(promo)
        }
        return data
    }
}