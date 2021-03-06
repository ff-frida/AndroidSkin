package com.lvkang.skin.resource

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import com.lvkang.skin.SkinManager

/**
 * @name SkinResource
 * @package com.lvkang.skin
 * @author 345 QQ:1831712732
 * @time 2020/11/24 23:31
 * @description 皮肤的资源管理器
 */
@SuppressLint("DiscouragedPrivateApi")
object SkinCompatResources {
    const val NOT_ID = 0
    private lateinit var resources: Resources
    private lateinit var packageName: String
    private lateinit var skinName: String
    private lateinit var loadStrategyAbstract: AbstractSkinLoadStrategy
    private val context by lazy { SkinManager.getContext() }
    var isDefaultSkin = true


    fun resetSkin(
        resources: Resources,
        loadStrategyAbstract: AbstractSkinLoadStrategy
    ) {
        isDefaultSkin = true
        this.resources = resources
        this.packageName = ""
        this.skinName = ""
        this.loadStrategyAbstract = loadStrategyAbstract
    }


    fun setupSkin(
        resources: Resources,
        packageName: String,
        skinName: String,
        loadStrategyAbstract: AbstractSkinLoadStrategy
    ) {
        this.resources = resources
        this.packageName = packageName
        this.skinName = skinName
        this.loadStrategyAbstract = loadStrategyAbstract
        isDefaultSkin = false
    }


    /**
     * 获取 String
     */
    fun getString(resId: Int): String? {
        tryCatch {
            val string = loadStrategyAbstract.getString(context, skinName, resId)
            if (string != null) return string
            if (!isDefaultSkin) {
                val skinResId = getSkinResId(resId)
                if (skinResId != NOT_ID)
                    return resources.getString(resId)
            }
            return context.resources.getString(resId)
        }
        return null
    }

    /**
     * 获取 Dimension
     */
    fun getDimension(resId: Int): Float? {
        tryCatch {
            val float = loadStrategyAbstract.getDimension(context, skinName, resId)
            if (float != null) return float
            if (!isDefaultSkin) {
                val skinResId = getSkinResId(resId)
                if (skinResId != NOT_ID)
                    return resources.getDimension(skinResId)
            }
            return context.resources.getDimension(resId)
        }
        return null
    }

    /**
     * 获取 Drawable
     */
    fun getDrawable(resId: Int): Drawable? {
        tryCatch {
            val drawable = loadStrategyAbstract.getDrawable(context, skinName, resId)
            if (drawable != null) return drawable
            if (!isDefaultSkin) {
                val skinResId = getSkinResId(resId)
                if (skinResId != NOT_ID)
                    return ResourcesCompat.getDrawable(resources, skinResId, null)
            }
            return ResourcesCompat.getDrawable(context.resources, resId, null)
        }
        return null
    }

    /** 获取 Color */
    fun getColor(resId: Int): Int? {
        tryCatch {
            val color = loadStrategyAbstract.getColor(context, skinName, resId)
            if (color != NOT_ID) return color
            if (!isDefaultSkin) {
                val skinResId = getSkinResId(resId)
                if (skinResId != NOT_ID)
                    return ResourcesCompat.getColor(resources, skinResId, null)
            }
            return ResourcesCompat.getColor(context.resources, resId, null)
        }
        return null
    }

    private fun getSkinResId(resId: Int): Int {
        val resName = loadStrategyAbstract.getSkinResName() ?: resources.getResourceEntryName(resId)
        val resType = resources.getResourceTypeName(resId)
        return resources.getIdentifier(resName, resType, packageName)
    }

    private inline fun <T> tryCatch(block: () -> T): T? {
        return try {
            block()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        }
    }
}