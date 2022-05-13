package dev.alimansour.borutoapp.data.local

import dev.alimansour.borutoapp.data.local.entity.toModel
import dev.alimansour.borutoapp.domain.local.LocalDataSource
import dev.alimansour.borutoapp.domain.model.Hero

class LocalDataSourceImpl(borutoDatabase: BorutoDatabase) : LocalDataSource {

    private val heroDao = borutoDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero =
        heroDao.getSelectedHero(heroId = heroId).toModel()
}