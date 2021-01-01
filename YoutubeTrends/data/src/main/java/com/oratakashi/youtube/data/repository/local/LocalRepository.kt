package com.oratakashi.youtube.data.repository.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.oratakashi.youtube.data.R
import com.oratakashi.youtube.data.database.RoomDB
import com.oratakashi.youtube.domain.repository.Repository
import com.oratakashi.youtube.domain.state.MainState

class LocalRepository(
    private val db : RoomDB,
    private val context : Context
) : Repository {
    override fun getTrends(): LiveData<MainState> {
        throw UnsupportedOperationException(context.getString(R.string.title_unsupported))
    }
}