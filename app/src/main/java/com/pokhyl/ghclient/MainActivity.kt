package com.pokhyl.ghclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import com.github.salomonbrys.kodein.KodeinInjected
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.instance
import com.pokhyl.ghclient.api.GitHubRepoService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.Functions
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class MainActivity : AppCompatActivity(), KodeinInjected  {

    override val injector = KodeinInjector()

    val retrofit: Retrofit by instance()
    //private val kodein by injector.kodein()

    lateinit var gitHubRepoService: GitHubRepoService
    lateinit var recyclerView : RecyclerView
    lateinit var searchView : EditText
    lateinit var progressView : ProgressBar;
    lateinit var repoAdapter : RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injector.inject(GithubClientApplication.instance.kodein)

        initRetrofit();
        initViews();

        var disposable : Disposable = gitHubRepoService.searchRepos("android")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        Consumer { searchRepoResult ->
                            Log.d("Test1", "totalCount " + searchRepoResult.totalCount)
                            repoAdapter.repoList = searchRepoResult.items ?: ArrayList();
                            recyclerView.visibility = View.VISIBLE
                            progressView.visibility = View.GONE

                        },
                        Consumer { ex -> Log.e("Test1", "Error", ex) } ,
                        Functions.EMPTY_ACTION,
                        Consumer { subscription ->
                            subscription.request(Long.MAX_VALUE)
                            recyclerView.visibility = View.GONE
                            progressView.visibility = View.VISIBLE
                        });
    }

    private fun initViews() {
        recyclerView = findViewById<View>(R.id.repo_list) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        repoAdapter = RepoAdapter()
        recyclerView.adapter = repoAdapter
        searchView = findViewById(R.id.search_repo_text)
        progressView = findViewById(R.id.search_progress)
    }

    private fun initRetrofit() {
        gitHubRepoService = retrofit.create(GitHubRepoService::class.java)
    }
}
