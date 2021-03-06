package com.lvkang.skin.resource.strategy

import com.lvkang.skin.SkinManager
import com.lvkang.skin.config.SkinUtils
import com.lvkang.skin.config.SkinPreUtils
import com.lvkang.skin.resource.AbstractSkinLoadStrategy
import com.lvkang.skin.resource.SkinCompatResources
import com.lvkang.skin.resource.SkinLoadStrategy

/**
 * @name SkinLoadImpl
 * @package ccom.lvkang.skin.resource.strategy
 * @author 345 QQ:1831712732
 * @time 2020/12/07 22:27
 * @description
 */
class AbstractSkinLoadImpl : AbstractSkinLoadStrategy() {

    override fun loadSkin(skinName: String): String? {
        val skinPath = SkinPreUtils.getSkinCacheDir() + skinName
        val resource = SkinManager.getSkinResources(skinPath)
        val packageName = SkinUtils.getSkinPackageName(skinPath)
        if (resource != null && packageName != null) {
            SkinCompatResources.setupSkin(resource, packageName, skinName, this)
            return SkinPreUtils.getSkinCacheDir()
        }
        return null
    }


    override fun getType(): SkinLoadStrategy = SkinLoadStrategy.SKIN_LOADER_STARTEGY
}