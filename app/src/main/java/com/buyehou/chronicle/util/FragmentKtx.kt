package com.buyehou.chronicle.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.buyehou.chronicle.R
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * 反射创建 ViewBinding 需带泛型信息
 */
@SuppressWarnings("Unchecked")
fun <VBinding : ViewBinding> Any.createBinding(
    inflater: LayoutInflater,
    container: ViewGroup? = null,
    attachRoot: Boolean = false
): VBinding {
    // 查找父类泛型信息
    var superclass: Type? = javaClass.genericSuperclass
    var viewBindingClass: Class<*>? = null
    do {
        if (superclass is ParameterizedType) {
            val typeClass = superclass.actualTypeArguments.firstOrNull { type ->
                val clazz = type as Class<*>
                clazz.interfaces.firstOrNull { f ->
                    f.isAssignableFrom(ViewBinding::class.java)
                } != null
            }
            if (typeClass != null) {
                viewBindingClass = typeClass as? Class<*>
                break
            }
        }
        superclass = (superclass as Class<*>).genericSuperclass
    } while (superclass != null)
    @Suppress("UNCHECKED_CAST")
    return createBinding(viewBindingClass as Class<VBinding>, inflater, container, attachRoot)
}

fun <VBinding : ViewBinding> createBinding(
    viewBindingClass: Class<VBinding>,
    inflater: LayoutInflater,
    container: ViewGroup?,
    attachRoot: Boolean
): VBinding {
    try {
        // 未找到视图绑定对象信息，直接抛出空指针异常,不能混淆该 inflate 方法
        val inflateMethod = viewBindingClass.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.javaPrimitiveType
        )
        @Suppress("UNCHECKED_CAST")
        return inflateMethod.invoke(null, inflater, container, attachRoot) as VBinding
    } catch (e: Throwable) {
        e.printStackTrace()
        throw e
    }
}

inline fun <reified VBinding : ViewBinding> Fragment.createBinding(
    container: ViewGroup? = null,
    inflater: LayoutInflater = layoutInflater,
    attachRoot: Boolean = false
) = createBinding(VBinding::class.java, inflater, container, attachRoot)


/**
 * 从当前页面查找
 */
inline val Fragment.navCtrl: NavController
    get() = findNavController()

/**
 * 从子Fragment查找
 */
inline val Fragment.childNavCtrl: NavController
    get() {
        val navHost = childFragmentManager.findFragmentById(R.id.mainNavHost) as NavHostFragment
        return navHost.navController
    }

/**
 * 从父级页面查找
 */
inline val Fragment.parentNavCtrl
    get() = NavHostFragment.findNavController(requireParentFragment().requireParentFragment())

/**
 * 从 Activity 查找
 */
inline val Fragment.activityNavCtrl
    get() = Navigation.findNavController(requireActivity(), R.id.mainNavHost)