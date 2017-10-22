package com.pokhyl.ghclient.model

class SearchRepoResult {
    var total_count: Int = 0
    var incomplete_results: Boolean = false
    var items: List<Repo>? = null
}