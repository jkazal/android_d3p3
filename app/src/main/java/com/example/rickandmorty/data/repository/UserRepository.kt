package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.entities.CharacterList
import com.example.rickandmorty.data.entities.Info
import com.example.rickandmorty.data.entities.user.User
import com.example.rickandmorty.data.entities.user.UserList
import com.example.rickandmorty.data.remote.users.UserRemoteDataSource
import com.example.rickandmorty.utils.Resource
import com.example.rickandmorty.utils.performGetOperation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UserRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource
    // private val localDataSource: CharacterDao
) {
    suspend fun getUsers(): Resource<UserList> {
        // return remoteDataSource.getAllUsers()
        // [MOCK]
        var listInfo : Info = Info(0, "", 0, 0)
        var userArray = ArrayList<User>()
        var u1 = User("1", "Johann", "KAZAL", "johannkazal@gmail.com")
        var u2 = User("2", "Thomas", "CARAYON", "thomas.carayon@gmail.com")
        var u3 = User("3", "Hyacinthe", "BRIAND", "hyacinthe.briand@gmail.com")
        var u4 = User("4", "Gael", "PELTEY", "gael.peltey@gmail.com")
        var u5 = User("5", "Leonard", "POTHERAT", "leonard.potherat@gmail.com")
        var u6 = User("6", "Ronald", "McDonald", "ronald.mcdonald@gmail.com")
        var u7 = User("7", "John", "John", "john.john@gmail.com")
        var u8 = User("8", "test", "test", "test@gmail.com")
        var u9 = User("9", "Leonard", "POTHERAT", "leonard.potherat@gmail.com")
        var u10 = User("10", "Ronald", "McDonald", "ronald.mcdonald@gmail.com")
        var u11 = User("11", "John", "John", "john.john@gmail.com")
        var u12 = User("12", "test", "test", "test@gmail.com")
        userArray.add(u1)
        userArray.add(u2)
        userArray.add(u3)
        userArray.add(u4)
        userArray.add(u5)
        userArray.add(u6)
        userArray.add(u7)
        userArray.add(u8)
        userArray.add(u9)
        userArray.add(u10)
        userArray.add(u11)
        userArray.add(u12)
        var userList = UserList(listInfo, userArray)
        return Resource.success(userList)

    }
    //suspend fun getCharacter(id: Int) : Character? {
    //    return remoteDataSource.getCharacter(id).data
    //}

    //suspend fun getCharacters(): Resource<CharacterList> {
    //    return remoteDataSource.getCharacters()
    //}


//    fun getCharacter(id: Int) = performGetOperation(
//        databaseQuery = { localDataSource.getCharacter(id) },
//        networkCall = { remoteDataSource.getCharacter(id) },
//        saveCallResult = { localDataSource.insert(it) }
//    )
//
//    fun getCharacters() = performGetOperation(
//        databaseQuery = { localDataSource.getAllCharacters() },
//        networkCall = { remoteDataSource.getCharacters() },
//        saveCallResult = { res -> localDataSource.insertAll(res.results) }
//    )
}