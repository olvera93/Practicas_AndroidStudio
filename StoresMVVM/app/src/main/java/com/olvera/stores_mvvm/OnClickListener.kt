package com.olvera.stores_mvvm

interface OnClickListener {

    fun onClick(store: StoreEntity)
    fun onFavoriteStore(store: StoreEntity)
    fun onDeleteStore(store: StoreEntity)
}