package com.pokhyl.ghclient.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Repo(@JsonProperty("id") val id : Int?, @JsonProperty("name") val name : String?)