package com.lvkang.skin.config

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import java.io.File


/**
 * @name SkinConfig
 * @package com.lvkang.skin.config
 * @author 345 QQ:1831712732
 * @time 2020/11/24 23:31
 * @description
 */
@SuppressLint("StaticFieldLeak")
object SkinPreUtils {

    lateinit var context: Context
    fun init(context: Context) {
        SkinPreUtils.context = context
        saveSkinCacheDir("${context.cacheDir.path}${File.separator}")
    }

    fun saveSkinCacheDir(cacheDir: String) {
        context.getSharedPreferences(SkinConfig.SKIN_INFO_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(SkinConfig.SKIN_DIR, cacheDir)
            .apply()
    }

    fun getSkinCacheDir(): String {
        return context.getSharedPreferences(SkinConfig.SKIN_INFO_NAME, Context.MODE_PRIVATE)
            .getString(SkinConfig.SKIN_PATH, "${context.cacheDir.path}${File.separator}")!!
    }

    /**
     * @param skinPath 皮肤路径，注意不包括皮肤名称
     * @param skinName 皮肤名称
     * @param skinStrategy 皮肤加载策略
     * 保存当前使用皮肤的状态
     */
    fun saveSkinStatus(skinPath: String, skinName: String, skinStrategy: String?) {
        saveSkinPath(skinPath)
        saveSkinName(skinName)
        saveSkinStrategy(skinStrategy)
    }


    /**
     * 保存当前皮肤路径
     */
    private fun saveSkinPath(skinPath: String?) {
        context.getSharedPreferences(SkinConfig.SKIN_INFO_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(SkinConfig.SKIN_PATH, skinPath)
            .apply()
    }

    /**
     * 返回当前皮肤路径
     */
    fun getSkinPath(): String? {
        return context.getSharedPreferences(SkinConfig.SKIN_INFO_NAME, Context.MODE_PRIVATE)
            .getString(SkinConfig.SKIN_PATH, null)
    }

    /**
     * 保存当前皮肤名称
     */
    private fun saveSkinName(skinName: String?) {
        context.getSharedPreferences(SkinConfig.SKIN_INFO_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(SkinConfig.SKIN_NAME, skinName)
            .apply()
    }

    /**
     * 获取当前皮肤名称
     */
    fun getSkinName(): String? {
        return context.getSharedPreferences(SkinConfig.SKIN_INFO_NAME, Context.MODE_PRIVATE)
            .getString(SkinConfig.SKIN_NAME, null)
    }


    /**
     * 保存当前皮肤加载策略
     */
    private fun saveSkinStrategy(skinStrategy: String?) {
        context.getSharedPreferences(SkinConfig.SKIN_INFO_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(SkinConfig.SKIN_STRATEGY, skinStrategy)
            .apply()
    }

    /**
     * 获取当前皮肤加载策略
     */
    fun getSkinStrategyName(): String? {
        return context.getSharedPreferences(SkinConfig.SKIN_INFO_NAME, Context.MODE_PRIVATE)
            .getString(SkinConfig.SKIN_STRATEGY, null)
    }

    /**
     * 清空皮肤路径
     */
    fun clearSkinInfo() {
        saveSkinPath(null)
        saveSkinName(null)
        saveSkinStrategy(null)
    }

    /**
     * 添加一个标记
     */
    fun setTag(boolean: Boolean) {
        context.getSharedPreferences(SkinConfig.TAG, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(SkinConfig.TAG, boolean)
            .apply()
    }

    /**
     * 获取标记
     */
    fun getTag(): Boolean {
        return context.getSharedPreferences(SkinConfig.TAG, Context.MODE_PRIVATE)
            .getBoolean(SkinConfig.TAG, false)
    }
}