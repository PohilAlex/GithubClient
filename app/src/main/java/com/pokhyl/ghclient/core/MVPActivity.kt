package com.pokhyl.ghclient.core

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class MVPActivity<P : BasePresenter<V>, V : BaseView<P>> : AppCompatActivity, BaseView<P> {

    protected val presenter: P
    protected val view: BaseView<P>

    constructor() {
        this.presenter = createPresenter()
        this.view = createView()
    }

    protected abstract fun createPresenter(): P

    protected fun createView(): BaseView<P> {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onAttach()
    }

    override fun onResume() {
        super.onResume()
        presenter.onAttach()
    }

    override fun onPause() {
        super.onPause()
        presenter.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


}