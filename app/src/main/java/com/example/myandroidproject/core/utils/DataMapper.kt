package com.example.myandroidproject.core.utils

import com.example.myandroidproject.core.data.source.local.entity.DataEntity
import com.example.myandroidproject.core.data.source.remote.response.DataResponse
import com.example.myandroidproject.core.domain.model.Data

object DataMapper {
    fun mapResponsesToEntities(input: List<DataResponse>): List<DataEntity> {
        val dataList = ArrayList<DataEntity>()
        input.map {
            val data = DataEntity(
                id = it.id,
                login = it.login,
                avatarUrl = it.avatarUrl
            )
            dataList.add(data)
        }
        return dataList
    }

    fun mapEntitiesToDomain(input: List<DataEntity>): List<Data> {
        return input.map {
            Data(
                id = it.id,
                login = it.login,
                avatarUrl = it.avatarUrl
            )
        }
    }

    fun mapDomainToEntity(input: Data) {
        DataEntity(
            id = input.id,
            login = input.login,
            avatarUrl = input.avatarUrl
        )
    }
}