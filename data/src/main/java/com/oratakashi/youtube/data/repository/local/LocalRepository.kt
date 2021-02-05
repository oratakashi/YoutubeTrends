package com.oratakashi.youtube.data.repository.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oratakashi.youtube.data.R
import com.oratakashi.youtube.data.database.RoomDB
import com.oratakashi.youtube.data.magic.toFavorite
import com.oratakashi.youtube.data.magic.toFavoriteModels
import com.oratakashi.youtube.domain.model.favorite.FavoriteModel
import com.oratakashi.youtube.domain.repository.Repository
import com.oratakashi.youtube.domain.state.DomainMainState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LocalRepository(
    private val db: RoomDB,
    private val context: Context
) : Repository {
    private val disposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun add(data: FavoriteModel, state: MutableLiveData<Boolean>) {
        db.fav().getById(data.toFavorite().id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .subscribe {
                if (it.isEmpty()) {
                    db.fav().add(data.toFavorite())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map { true }
                        .toFlowable()
                        .subscribe(state::postValue)
                        .let { return@let disposable::add }
                } else {
                    db.fav().delete(data.toFavorite())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map { false }
                        .toFlowable()
                        .subscribe(state::postValue)
                        .let { return@let disposable::add }
                }
            }
            .let { return@let disposable::add }
    }

    override fun getById(data: FavoriteModel, state: MutableLiveData<Boolean>) {
        db.fav().getById(data.toFavorite().id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.isNotEmpty() }
            .toFlowable()
            .subscribe(state::postValue)
            .let { return@let disposable::add }
    }

    override fun getAll(state: MutableLiveData<List<FavoriteModel>>) {
        db.fav().getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.toFavoriteModels() }
            .toFlowable()
            .subscribe(state::postValue)
            .let { return@let disposable::add }
    }

    override fun getByCategory(id: String, state: MutableLiveData<List<FavoriteModel>>) {
        db.fav().getByCategory(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.toFavoriteModels() }
            .toFlowable()
            .subscribe(state::postValue)
            .let { return@let disposable::add }
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