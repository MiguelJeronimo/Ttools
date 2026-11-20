package com.example.TibiaTools.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.TibiaTools.data.mapper.toDomain
import com.example.TibiaTools.data.retrofit.TibiaAPIServer
import com.example.TibiaTools.domain.model.HighScoreItem

class HighScorePagingSource(
    private val apiServer: TibiaAPIServer,
    private val world: String? = null,
    private val category: String? = null,
    private val vocation: String? = null
) : PagingSource<Int, HighScoreItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HighScoreItem> {
        val page = params.key ?: 1
        return try {

            Log.i("HigScoresScreen", "$world, $category, $vocation, $page")
            val response = apiServer.getHighScoreInformation(
                world = world ?: "all",
                category = category?: "experience",
                vocation = vocation?: "all",
                page = page
            ).toDomain()
            Log.i("HigScoresScreen", "Loaded high scores for page $response")
            val players = response.highscores?.highscoreList ?: emptyList()
            val totalPages = response.highscores?.highscorePage?.totalPages ?: 1

            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (page >= totalPages || players.isEmpty()) null else page + 1

            LoadResult.Page(
                data = players,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            Log.e("HigScoresScreen", "Error occurred while loading high scores", e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, HighScoreItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
