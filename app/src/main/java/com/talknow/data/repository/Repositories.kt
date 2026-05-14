package com.talknow.data.repository

import com.talknow.data.datasource.FirebaseDataSource
import com.talknow.data.model.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource
) {

    suspend fun signUpWithEmail(email: String, password: String, user: User) =
        firebaseDataSource.signUpWithEmail(email, password, user)

    suspend fun loginWithEmail(email: String, password: String) =
        firebaseDataSource.loginWithEmail(email, password)

    suspend fun signUpWithPhone(phone: String, verificationId: String, code: String, user: User) =
        firebaseDataSource.signUpWithPhone(phone, verificationId, code, user)

    fun getCurrentUserId() = firebaseDataSource.getCurrentUserId()

    suspend fun logout() = firebaseDataSource.logout()

    suspend fun getUser(userId: String) = firebaseDataSource.getUser(userId)

    suspend fun updateUser(userId: String, user: User) =
        firebaseDataSource.updateUser(userId, user)

    suspend fun updateUserStatus(userId: String, status: String) =
        firebaseDataSource.updateUserStatus(userId, status)

    fun getOnlineUsersCount(): Flow<Int> = firebaseDataSource.getOnlineUsersCount()
}

class FriendRepository @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource
) {

    suspend fun addFriend(userId: String, friendId: String) =
        firebaseDataSource.addFriend(userId, friendId)

    suspend fun removeFriend(userId: String, friendId: String) =
        firebaseDataSource.removeFriend(userId, friendId)

    suspend fun getFriends(userId: String) =
        firebaseDataSource.getFriends(userId)
}

class CallRepository @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource
) {

    suspend fun saveCallHistory(userId: String, callHistory: CallHistory) =
        firebaseDataSource.saveCallHistory(userId, callHistory)

    suspend fun getCallHistory(userId: String) =
        firebaseDataSource.getCallHistory(userId)

    suspend fun getRandomMatchingUser(currentUserId: String, interests: List<String>, gender: String = "") =
        firebaseDataSource.getRandomMatchingUser(currentUserId, interests, gender)

    suspend fun addToMatchingQueue(userId: String, queueItem: CallQueueItem) =
        firebaseDataSource.addToMatchingQueue(userId, queueItem)
}

class CoinRepository @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource
) {

    suspend fun addCoins(userId: String, amount: Int, type: String, description: String) =
        firebaseDataSource.addCoins(userId, amount, type, description)

    suspend fun deductCoins(userId: String, amount: Int) =
        firebaseDataSource.deductCoins(userId, amount)
}

class PremiumRepository @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource
) {

    suspend fun setPremiumStatus(userId: String, premium: PremiumUser) =
        firebaseDataSource.setPremiumStatus(userId, premium)

    suspend fun getPremiumStatus(userId: String) =
        firebaseDataSource.getPremiumStatus(userId)
}

class ReportRepository @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource
) {

    suspend fun reportUser(report: UserReport) =
        firebaseDataSource.reportUser(report)
}
