package com.oratakashi.youtube.data.repository.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.oratakashi.youtube.data.R
import com.oratakashi.youtube.data.database.RoomDB
import com.oratakashi.youtube.data.magic.toFavorite
import com.oratakashi.youtube.data.magic.toFavoriteModel
import com.oratakashi.youtube.domain.model.favorite.FavoriteModel
import com.oratakashi.youtube.domain.repository.Repository
import com.oratakashi.youtube.domain.state.DomainMainState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalRepository(
    private val db: RoomDB,
    private val context: Context
) : Repository {
    override fun add(data: FavoriteModel) {
        CoroutineScope(Dispatchers.IO).launch {
            db.fav().add(data.toFavorite())
        }
    }

    override fun delete(data: FavoriteModel) {
        CoroutineScope(Dispatchers.IO).launch {
            db.fav().delete(data.toFavorite())
        }
    }

    override suspend fun getById(data: FavoriteModel): List<FavoriteModel> {
        val output: MutableList<FavoriteModel> = ArrayList()
        return output.apply {
            db.fav().getById(data.toFavorite().id).forEach {
                this.add(it.toFavoriteModel())
            }
        }
    }

    override suspend fun getAll(): List<FavoriteModel> {
        val output: MutableList<FavoriteModel> = ArrayList()
        return output.apply {
            db.fav().getAll().forEach {
                this.add(it.toFavoriteModel())
            }
        }
    }

    override fun getTrends(): LiveData<DomainMainState> {
        throw UnsupportedOperationException(context.getString(R.string.title_unsupported))
    }

    override fun getGames(): LiveData<DomainMainState> {
        throw UnsupportedOperationException(context.getString(R.string.title_unsupported))
    }

    override fun getMusic(): LiveData<DomainMainState> {
        throw UnsupportedOperationException(context.getString(R.string.title_unsupported))
    }

    override fun getSport(): LiveData<DomainMainState> {
        throw UnsupportedOperationException(context.getString(R.string.title_unsupported))
    }
}