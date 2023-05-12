package desriel.kiki.Api

import desriel.kiki.GameList
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("/api/games?")
    public fun getGameList(
        @Path("key") apikey: String = "9008d9a5894f4b1d8d344cbd333bbe92"
    ): Call<List<GameList>>
}