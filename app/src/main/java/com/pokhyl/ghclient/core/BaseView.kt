package com.pokhyl.ghclient.core

interface BaseView <in P> {

    fun setPresenter(presenter: P)
}