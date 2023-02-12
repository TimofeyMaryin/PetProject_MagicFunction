package com.example.thisappwillbefunny.presentation.fr.select_activity

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.R.*
import com.example.thisappwillbefunny.domain.model.FilterElementModel
import com.example.thisappwillbefunny.domain.model.RequestActivityModel
import com.example.thisappwillbefunny.domain.room.AppDataBase
import com.example.thisappwillbefunny.domain.room.like_activities.LikeActivitiesEntity
import com.example.thisappwillbefunny.domain.repository.LikeActivityDaoRepo
import com.example.thisappwillbefunny.domain.repository.LikeActivityRepository
import com.example.thisappwillbefunny.domain.state.ActivityLevels
import com.example.thisappwillbefunny.domain.state.FilterActivityState
import com.example.thisappwillbefunny.domain.use_cases.GetCurrentActivity
import com.example.thisappwillbefunny.presentation.repository.ActivityInfoRepoImpl
import com.example.thisappwillbefunny.utils.UiConst
import com.example.thisappwillbefunny.utils.emptyRequestActivityModel
import kotlinx.coroutines.coroutineScope

class SelectActivityViewModel(
    application: Application,
    private val repo: LikeActivityRepository
) : ViewModel(), LikeActivityDaoRepo {

    override fun getAllActivity(): MutableList<LikeActivitiesEntity> = repo.getAllActivity()
    override fun getLikedActivities(): MutableList<LikeActivitiesEntity> = repo.getLikedActivities()
    override fun likeActivity(activity: LikeActivitiesEntity) =  repo.likeActivity(activity = activity)
    override fun deleteActivity(activity: LikeActivitiesEntity) = repo.deleteActivity(activity = activity)



    var defaultCountRequest by mutableStateOf(10)
    var currentRequest by mutableStateOf(listOf(emptyRequestActivityModel))
    val activityRepo = ActivityInfoRepoImpl()

    suspend fun requestActivity(): MutableList<RequestActivityModel> {
        val result = mutableListOf<RequestActivityModel>()
        coroutineScope {
            for (i in 0 until defaultCountRequest) {
                val currentRequest = GetCurrentActivity.execute()
                result.add(
                    RequestActivityModel(
                        activityPOJO = currentRequest,
                        accessibleModel = activityRepo.getAccessibility(currentRequest.accessibility.toFloat()),
                        participants = activityRepo.getParticipants(currentRequest.participants),
                        pricing = activityRepo.getPrice(currentRequest.price.toFloat())
                    )
                )
            }

        }

        return result
    }

    fun selectCurrentIconType(type: String): Int {
        return when(type) {
            "education" -> R.drawable.education
            "recreational" -> R.drawable.recreational
            "social" -> R.drawable.social
            "diy" -> R.drawable.safety_pin_coucou_svgrepo_com
            "charity" -> R.drawable.charity_svgrepo
            "cooking" -> R.drawable.cooking_svgrepo
            "relaxation" -> R.drawable.relaxation
            "music" -> R.drawable.music_svgrepo
            else -> R.drawable.busywork
        }
    }







    @Deprecated("Doesn`t work. I can`t releas it.")
    private var currentFilterIndex by mutableStateOf(0)
    @Deprecated("Doesn`t work. I can`t releas it.")
    private var currentFilterState by mutableStateOf(FilterActivityState.BACK)
    @Deprecated("Doesn`t work. I can`t releas it.")
    private var currentFilterStateLevelForAcc by mutableStateOf<ActivityLevels?>(null)
    @Deprecated("Doesn`t work. I can`t releas it.")
    private val filterElement by mutableStateOf(listOf(
        FilterElementModel(drawable.reload, mutableStateOf(true), UiConst.Brushes.Refresh),
        FilterElementModel(drawable.accessibility_easy, mutableStateOf(false), UiConst.Brushes.Item),
        FilterElementModel(drawable.particants_easy, mutableStateOf(false), UiConst.Brushes.Item),
        FilterElementModel(drawable.price_ez, mutableStateOf(false), UiConst.Brushes.Item),
    ))
    @Deprecated("Doesn`t work. I can`t releas it.")
    private val accessibilityItems by mutableStateOf(listOf(
        FilterElementModel(drawable.reload, mutableStateOf(false), UiConst.Brushes.Refresh),
        FilterElementModel(
            drawable.accessibility_easy,
            mutableStateOf(false),
            UiConst.Brushes.ActivityTipsStatus.easy.first
        ) { currentFilterStateLevelForAcc = ActivityLevels.EASY },
        FilterElementModel(
            drawable.accessibility_normal,
            mutableStateOf(false),
            UiConst.Brushes.ActivityTipsStatus.normal.first
        ) { currentFilterStateLevelForAcc = ActivityLevels.NORMAL },
        FilterElementModel(
            drawable.accessibility_hard,
            mutableStateOf(false),
            UiConst.Brushes.ActivityTipsStatus.hard.first
        ) { currentFilterStateLevelForAcc = ActivityLevels.HARD }
    ))
    @Deprecated("Doesn`t work. I can`t releas it.")
    private val participantItems by mutableStateOf(listOf(
        FilterElementModel(drawable.reload, mutableStateOf(false), UiConst.Brushes.Refresh),
        FilterElementModel(drawable.particants_easy, mutableStateOf(false), UiConst.Brushes.ActivityTipsStatus.easy.first),
        FilterElementModel(drawable.particanst_normal, mutableStateOf(false), UiConst.Brushes.ActivityTipsStatus.normal.first),
        FilterElementModel(drawable.particant_hard, mutableStateOf(false), UiConst.Brushes.ActivityTipsStatus.hard.first)
    ))
    @Deprecated("Doesn`t work. I can`t releas it.")
    private val priceItems by mutableStateOf(listOf(
        FilterElementModel(drawable.reload, mutableStateOf(false), UiConst.Brushes.Refresh),
        FilterElementModel(drawable.price_ez, mutableStateOf(false), UiConst.Brushes.ActivityTipsStatus.easy.first),
        FilterElementModel(drawable.price_normal, mutableStateOf(false), UiConst.Brushes.ActivityTipsStatus.normal.first),
        FilterElementModel(drawable.price_hard, mutableStateOf(false), UiConst.Brushes.ActivityTipsStatus.hard.first)
    ))
    @Deprecated("Doesn`t work. I can`t releas it.")
    fun selectFilterEl(index: Int) {
        filterElement[currentFilterIndex].isActive.value = false
        currentFilterIndex = index
        filterElement[currentFilterIndex].isActive.value = true

        currentFilterState = when (index){
            0 -> FilterActivityState.BACK
            1 -> FilterActivityState.ACCESSIBILITY
            2 -> FilterActivityState.PARTICIPANTS
            else -> FilterActivityState.PRICE
        }
    }
    @Deprecated("Doesn`t work. I can`t releas it.")
    private fun getCurrentInternetRequestType(): String {
        return when (currentFilterState) {
            FilterActivityState.PRICE -> "price"
            FilterActivityState.PARTICIPANTS -> "participants"
            else -> "accessibility"
        }
    }
    @Deprecated("Doesn`t work. I can`t releas it.")
    suspend fun requestActivityWithFilter(type: String, detail: String) {
        val result = mutableListOf<RequestActivityModel>()
        coroutineScope {
            for(i in 0 until defaultCountRequest){
                val currentRequest = GetCurrentActivity.getActivityWithFilter(type = getCurrentInternetRequestType(), detail = detail)
                result.add(
                    RequestActivityModel(
                        activityPOJO = currentRequest,
                        accessibleModel = activityRepo.getAccessibility(currentRequest.accessibility.toFloat()),
                        participants = activityRepo.getParticipants(currentRequest.participants),
                        pricing = activityRepo.getPrice(currentRequest.price.toFloat())
                    )
                )
            }
        }
    }
    @Deprecated("Doesn`t work. I can`t releas it.")
    fun getFilterElements(): List<FilterElementModel> {
        return when(currentFilterState) {
            FilterActivityState.ACCESSIBILITY -> accessibilityItems
            FilterActivityState.PARTICIPANTS -> participantItems
            FilterActivityState.PRICE -> priceItems
            else -> filterElement
        }
    }


}