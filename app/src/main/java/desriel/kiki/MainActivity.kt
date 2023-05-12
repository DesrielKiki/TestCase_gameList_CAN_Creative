package desriel.kiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import desriel.kiki.Api.ApiConfig
import desriel.kiki.databinding.ActivityMainBinding
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object{
        private const val TAG = "MainActivity"
        private const val GAME_ID = "9008d9a5894f4b1d8d344cbd333bbe92"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val layoutManager = LinearLayoutManager(this)
        binding.rvGame.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvGame.addItemDecoration(itemDecoration)
        findGame()
    }
    private fun findGame() {
        showLoading(true)
        val client = ApiConfig.getApiService().getGameList(GAME_ID)
        client.enqueue(object : Callback<GameList> {
            override fun onResponse(
                call: Call<GameList>,
                response: Response<GameList>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setGameData(responseBody.GameList)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<GameList>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
    private fun setGameData(game: Game) {
        binding.tvTitle.text = game.name
        binding.tvReleaseDate.text = game.release
        binding.tvRating.text = game.rating
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}

