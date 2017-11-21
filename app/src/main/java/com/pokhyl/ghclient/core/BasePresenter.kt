package com.pokhyl.ghclient.core

interface BasePresenter<in V> {

    fun setView(view : V)

    fun onCreate() {}

    fun onAttach() {}

    fun onDetach() {}

    fun onDestroy() {}

}