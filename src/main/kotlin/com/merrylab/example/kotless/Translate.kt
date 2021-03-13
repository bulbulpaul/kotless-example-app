package com.merrylab.example.kotless

import io.kotless.dsl.lang.http.Get

@Get("/welcome")
fun welcomeMessage(): String {
    return "Welcome to nakanoshima.dev!!"
}

@Get("/translate")
fun translate(word: String): String {
    return Vocabulary.getByWord(word)!!
}
