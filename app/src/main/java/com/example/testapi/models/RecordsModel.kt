package com.example.testapi.models

data class RecordsModel(
    var uuid: String = "null",
    var artist_uuid: String = "Doe",
    var title: String = "Jon",
    var number_of_plays: Int? = 0,
    var number_of_moons: Int? = 0,
    var voice_style: String = "null",
    var kind: String = "super",
    var description: String = "null",
    var created_at: String = "",
    var updated_at: String = ""
)
