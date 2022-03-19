package com.example.jsactivity

import com.example.jsactivity.pluginimpl.qspanel.dagger.SubModule
import dagger.Component

/**
 * @author Henry
 *@Date 2022/3/17  3:42 AM
 *@Email 2427417167@qq.com
 */
@Component(modules = [SubModule::class])
interface Component {
     fun getElement() : SubModuleElement
}