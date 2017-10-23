package com.pokhyl.ghclient.model

import com.fasterxml.jackson.annotation.JsonProperty

class SearchRepoResult {
    @JsonProperty("total_count") var totalCount: Int = 0
    @JsonProperty("incomplete_results") var incompleteResults: Boolean = false
    @JsonProperty("items") var items: List<Repo>? = null
}