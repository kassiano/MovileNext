package br.com.honeyinvestimentos.day3_dagger2

import br.com.honeyinvestimentos.day3_dagger2.di.DaggerTwitterComponent
import br.com.honeyinvestimentos.day3_dagger2.di.TwitterModule
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject
import javax.inject.Singleton


class Tweet(tweet:String)

class Timeline(private val api: TwitterApi,
               private val user: String) {
    private val cache: List<Tweet> = emptyList()
    fun get() = cache
    fun loadMore(amount: Int) { /* ... */ }
}

class Tweeter(private val api: TwitterApi,
              private val user: String) {
    fun tweet(tweet: String) {
        api.postTweet(user, tweet)
    }
}

@Singleton
class TwitterApi @Inject constructor(private val client: OkHttpClient) {
    fun postTweet(user: String, tweet: String) {
        val request = Request.Builder()/*...*/.build()
        client.newCall(request).execute()
    }
}

class Streaming {
    fun register(app: TwitterApplication) { /* ... */ }
}

class TwitterApplication {

    @Inject lateinit var tweeter: Tweeter
    @Inject lateinit var timeline: Timeline

    @Inject
    fun enableStreaming(streaming: Streaming) {
        streaming.register(this)
    }

}


fun main(){

    val component = DaggerTwitterComponent.builder()
//        .networkModule(NetworkModule())
        .twitterModule(TwitterModule("Kassiano Resende"))
        .build()

    val tweeter = component.tweeter()
    tweeter.tweet("Hello, #Dagger2")
    tweeter.tweet("Kotlin > Java #KotlinWorld")
    tweeter.tweet("#Dagger2 s2")
    tweeter.tweet("#Dagger2 is the best")

    val timeline = component.timeline()
    timeline.loadMore(20)
    for (tweet in timeline.get()) {
        println(tweet)
    }
}