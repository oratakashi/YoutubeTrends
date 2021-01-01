package com.oratakashi.youtube.core.data.source

import androidx.paging.PageKeyedDataSource
import com.oratakashi.youtube.core.data.model.main.Items

class HomeDataSource : PageKeyedDataSource<String, Items>() {

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Items>
    ) {

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Items>) {

    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Items>) {

    }

}