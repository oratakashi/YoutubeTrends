package com.oratakashi.youtube.data.magic.mapper

import com.oratakashi.youtube.data.magic.toMediumModel
import com.oratakashi.youtube.data.magic.toStandardModel
import com.oratakashi.youtube.data.model.main.Medium
import com.oratakashi.youtube.data.model.main.Standard
import com.oratakashi.youtube.domain.model.main.ItemModel
import com.oratakashi.youtube.domain.model.main.SnippetModel
import com.oratakashi.youtube.domain.model.main.ThumnailsModel
import junit.framework.TestCase
import org.junit.Test

class MainMapperKtTest : TestCase() {
    @Test
    fun testMapper() {
        val medium = Medium(
            "https://google.com",
            100,
            200
        ).toMediumModel()

        val standard = Standard(
            "https://facebook.com",
            100,
            200
        ).toStandardModel()

        val thumbnails = ThumnailsModel(medium, standard)

        val snippet = SnippetModel(
            "2020-01-12",
            "abc",
            "Testing",
            "Testing Description",
            "Testing",
            "2",
            thumbnails
        )

        println(medium.toString())
        println(standard.toString())
        println(snippet)
        println(ItemModel("2", snippet, null))
    }
}